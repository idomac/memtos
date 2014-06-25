package com.quanix.memtos.server.session.scheduler;

import com.quanix.memtos.server.util.SerializableUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.ReflectUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * @author : lihaoquan
 *
 * Session 的验证调度计划
 */
public class MySqlSessionValidationScheduler implements SessionValidationScheduler,Runnable {


    @Autowired
    private JdbcTemplate jdbcTemplate;


    private static Logger logger = LoggerFactory.getLogger(MySqlSessionValidationScheduler.class);

    ValidatingSessionManager sessionManager;

    private ScheduledExecutorService service;

    private long interval = DefaultSessionManager.DEFAULT_SESSION_VALIDATION_INTERVAL;
    private boolean enabled = false;


    @Override
    public void enableSessionValidation() {

        if (this.interval > 0l) {

            /**
             * Creates an Executor that uses a single worker thread operating off an unbounded queue,
             * and uses the provided ThreadFactory to create a new thread when needed.
             * Unlike the otherwise equivalent newFixedThreadPool(1, threadFactory)
             * the returned executor is guaranteed not to be reconfigurable to use additional threads.
             */
            this.service = Executors.newSingleThreadScheduledExecutor(new ThreadFactory() {
                @Override
                public Thread newThread(Runnable r) {
                    Thread thread = new Thread(r);
                    thread.setDaemon(true);
                    return thread;
                }
            });
            this.service.scheduleAtFixedRate(this, interval, interval, TimeUnit.MILLISECONDS);
            this.enabled = true;
        }
    }



    @Override
    public void run() {
        if (logger.isDebugEnabled()) {
            logger.debug("Executing session validation...");
        }

        long startTime = System.currentTimeMillis();
        //分页获取会话并验证
        String sql = "select session from sessions limit ?,?";
        int start = 0; //起始记录
        int size = 20; //每页大小
        List<String> sessionList = jdbcTemplate.queryForList(sql, String.class, start, size);
        while(sessionList.size() > 0) {
            for(String sessionStr : sessionList) {
                try {
                    Session session = SerializableUtils.deserialize(sessionStr);
                    //想想这里为什么要使用ReflectionUtils进行方法调用: AbstractValidatingSessionManager是抽象类,不能被实例化
                    Method validateMethod =  ReflectionUtils
                            .findMethod(AbstractValidatingSessionManager.class, "validate", Session.class, SessionKey.class);
                    validateMethod.setAccessible(true);
                    //当发现有不合格的Session的时候,后续工作AbstractValidatingSessionManager会去处理的
                    ReflectionUtils.invokeMethod(validateMethod,sessionManager,session,new DefaultSessionKey(session.getId()));
                }catch (Exception e) {
                    //忽略异常处理
                }
            }
            start = start + size;
            sessionList = jdbcTemplate.queryForList(sql, String.class, start, size);
        }
        long stopTime = System.currentTimeMillis();
        if (logger.isDebugEnabled()) {
            logger.debug("Session validation completed successfully in " + (stopTime - startTime) + " milliseconds.");
        }
    }


    @Override
    public void disableSessionValidation() {
        this.service.shutdown();
        this.enabled = false;

    }

    public MySqlSessionValidationScheduler() {
        super();
    }

    public ValidatingSessionManager getSessionManager() {
        return sessionManager;
    }

    public void setSessionManager(ValidatingSessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    public long getInterval() {
        return interval;
    }

    public void setInterval(long interval) {
        this.interval = interval;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }



}
