package com.quanix.memtos.server.web.controller.base;

import com.quanix.memtos.server.entity.base.AbstractEntity;
import com.quanix.memtos.server.util.ReflectUtils;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.Serializable;

/**
 * created by lihaoquan
 *
 * web 控制器基类
 */
public abstract class BaseController<M extends AbstractEntity, ID extends Serializable> {


    protected final Class<M> entityClass;

    private String viewPrefix;

    protected BaseController() {
        this.entityClass = ReflectUtils.findParameterizedType(getClass(),0);
        setViewPrefix(defaultViewPrefix());
    }

    /**
     * 设置通用数据
     *
     * @param model
     */
    protected void setCommonData(Model model) {
    }

    /**
     * 当前模块 视图的前缀
     * 默认
     * 1、获取当前类头上的@RequestMapping中的value作为前缀
     * 2、如果没有就使用当前模型小写的简单类名
     */
    public void setViewPrefix(String viewPrefix) {
        if (viewPrefix.startsWith("/")) {
            viewPrefix = viewPrefix.substring(1);
        }
        this.viewPrefix = viewPrefix;
    }

    /**
     * 获取RequestMapping的视图地址前缀
     * @return
     */
    protected String defaultViewPrefix() {
        String currentViewPrefix = "";

        RequestMapping requestMapping = AnnotationUtils.findAnnotation(getClass(),RequestMapping.class);
        if(null != requestMapping && null != requestMapping.value() && requestMapping.value().length>0) {
            currentViewPrefix = requestMapping.value()[0];
        }

        if (StringUtils.isEmpty(currentViewPrefix)) {
            currentViewPrefix = this.entityClass.getSimpleName();
        }
        return currentViewPrefix;
    }


    public String getViewPrefix() {
        return viewPrefix;
    }
    /**
     * 获取视图名称：即prefixViewName + "/" + suffixName
     *
     * @return
     */
    public String viewName(String suffixName) {
        if (!suffixName.startsWith("/")) {
            suffixName = "/" + suffixName;
        }
        return getViewPrefix() + suffixName;
    }

    /**
     * 共享的验证规则
     * 验证失败返回true
     *
     * @param m
     * @param result
     * @return
     */
    protected boolean hasError(M m, BindingResult result) {
        Assert.notNull(m);
        return result.hasErrors();
    }


    /**
     * @param backURL null 将重定向到默认getViewPrefix()
     * @return
     */
    protected String redirectToUrl(String backURL) {
        if (StringUtils.isEmpty(backURL)) {
            backURL = getViewPrefix();
        }
        if (!backURL.startsWith("/") && !backURL.startsWith("http")) {
            backURL = "/" + backURL;
        }
        return "redirect:" + backURL;
    }


    /**
     * 创建新的模型
     * @return
     */
    protected M newModel() {
        try {
            return entityClass.newInstance();
        } catch (Exception e) {
            throw new IllegalStateException("can not instantiated model : " + this.entityClass, e);
        }
    }
}
