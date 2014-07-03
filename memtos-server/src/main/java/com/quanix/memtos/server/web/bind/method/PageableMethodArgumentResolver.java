package com.quanix.memtos.server.web.bind.method;

import com.quanix.memtos.server.web.bind.PageableDefaults;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.MethodParameter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Map;

/**
 * created by lihaoquan
 *
 * 分页方法参数解析器
 *
 */
public class PageableMethodArgumentResolver extends BaseMethodArgumentResolver {

    private static final Pageable DEFAULT_PAGE_REQUEST = new PageRequest(0, 10);
    private static final String DEFAULT_PAGE_PREFIX = "page";
    private static final String DEFAULT_SORT_PREFIX = "sort";

    private Pageable fallbackPagable = DEFAULT_PAGE_REQUEST;
    private String pagePrefix = DEFAULT_PAGE_PREFIX;
    private String sortPrefix = DEFAULT_SORT_PREFIX;
    private int minPageSize = 1;
    private int maxPageSize = 100;


    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return Pageable.class.isAssignableFrom(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
        NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        //获取pageableDefaults注解
        PageableDefaults pageableDefaults = parameter.getParameterAnnotation(PageableDefaults.class);

        if(pageableDefaults == null) {
            pageableDefaults = parameter.getMethodAnnotation(PageableDefaults.class);
        }

        //处理目标的返回Pageable对象
        Pageable defaultPageRequest = fallbackPagable;
        if(pageableDefaults != null) {
            int pageNumber = pageableDefaults.pageNumber();//通过注解对象获取数值
            int pageSize = pageableDefaults.value();//通过注解对象获取数值
            defaultPageRequest = new PageRequest(pageNumber, pageSize, null);
        }
        String pageableNamePrefix = getPagePrefix(parameter);
        Map<String, String[]> pageableMap = getPrefixParameterMap(pageableNamePrefix, webRequest, true);

        //如果前台没有想过分页查询参数传入,则直接返回构造的分页对象
        if (pageableMap.size() == 0) {
            return new PageRequest(defaultPageRequest.getPageNumber(), defaultPageRequest.getPageSize(),defaultPageRequest.getSort());
        }
        int pn = getPn(pageableMap, defaultPageRequest);
        int pageSize = getPageSize(pageableMap, defaultPageRequest);
        return new PageRequest(pn - 1, pageSize, null);
    }


    private String getPagePrefix(MethodParameter parameter) {

        Qualifier qualifier = parameter.getParameterAnnotation(Qualifier.class);
        if (qualifier != null) {
            return new StringBuilder(qualifier.value()).append("_").append(pagePrefix).toString();
        }
        return pagePrefix;
    }

    private int getPageSize(Map<String, String[]> pageableMap, Pageable defaultPageRequest) {
        int pageSize = 0;
        try {
            String pageSizeStr = pageableMap.get("size")[0];
            if (pageSizeStr != null) {
                pageSize = Integer.valueOf(pageSizeStr);
            } else {
                pageSize = defaultPageRequest.getPageSize();
            }
        } catch (Exception e) {
            pageSize = defaultPageRequest.getPageSize();
        }

        if (pageSize < minPageSize) {
            pageSize = minPageSize;
        }

        if (pageSize > maxPageSize) {
            pageSize = maxPageSize;
        }
        return pageSize;
    }

    private int getPn(Map<String, String[]> pageableMap, Pageable defaultPageRequest) {
        int pn = 1;
        try {
            String pnStr = pageableMap.get("pn")[0];
            if (pnStr != null) {
                pn = Integer.valueOf(pnStr);
            } else {
                pn = defaultPageRequest.getPageNumber();
            }
        } catch (Exception e) {
            pn = defaultPageRequest.getPageNumber();
        }

        if (pn < 1) {
            pn = 1;
        }

        return pn;
    }
}
