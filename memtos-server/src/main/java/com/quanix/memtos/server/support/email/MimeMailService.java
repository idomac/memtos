package com.quanix.memtos.server.support.email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.MimeMessage;
import java.util.Date;

/**
 * @author : lihaoquan
 *
 * MIME类型的邮件服务发送服务类
 */
public class MimeMailService {

    private static final String DEFAULT_ENCODING = "utf-8";

    private static Logger logger = LoggerFactory.getLogger(MimeMailService.class);

    private JavaMailSender mailSender;//运用上下文的邮件服务发送装置

    private String textTemplate;


    /**
     * 发送MIME格式的消息通知邮件
     */
    public void send(String username) {
        try {

            MimeMessage msg = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(msg,true,DEFAULT_ENCODING);

            helper.setSubject("用户信息变更通知");
            helper.setFrom("idomac@163.com");
            helper.setTo("quanix@163.com");

            // 将用户名与当期日期格式化到邮件内容的字符串模板
            String content = String.format(textTemplate, username, new Date());
            helper.setText(content);

            //File attachment = generateAttachment();
            //helper.addAttachment("附件信息.txt",attachment);

            mailSender.send(msg);
            logger.info("邮件已发送至quanix@163.com");

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void setTextTemplate(String textTemplate) {
        this.textTemplate = textTemplate;
    }
}
