package com.quanix.memtos.server.web.bind.method;

import com.quanix.memtos.server.web.bind.PageableDefaults;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.MethodParameter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    private int minPageSize = 5;
    private int maxPageSize = 100;


    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return Pageable.class.isAssignableFrom(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
        NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        PageableDefaults pageableDefaults = getPageableDefaults(parameter);
        //默认的page request
        Pageable defaultPageRequest = getDefaultFromAnnotationOrFallback(pageableDefaults);
        String pageableNamePrefix = getPagePrefix(parameter);
        Map<String, String[]> pageableMap = getPrefixParameterMap(pageableNamePrefix, webRequest, true);
        if (pageableMap.size() == 0) {
            return new PageRequest(defaultPageRequest.getPageNumber(), defaultPageRequest.getPageSize(),defaultPageRequest.getSort());
        }
        int pn = getPn(pageableMap, defaultPageRequest);
        int pageSize = getPageSize(pageableMap, defaultPageRequest);
        return new PageRequest(pn - 1, pageSize, null);
    }


    /**
     * 寻找 @PageableDefaults 对象参数
     * @param parameter
     * @return
     */
    private PageableDefaults getPageableDefaults(MethodParameter parameter) {
        //首先从参数上找
        PageableDefaults pageableDefaults = parameter.getParameterAnnotation(PageableDefaults.class);
        //找不到从方法上找
        if (pageableDefaults == null) {
            pageableDefaults = parameter.getMethodAnnotation(PageableDefaults.class);
        }
        return pageableDefaults;
    }


    private Pageable getDefaultFromAnnotationOrFallback(PageableDefaults pageableDefaults) {
        Pageable defaultPageable = defaultPageable(pageableDefaults);
        if (defaultPageable != null) {
            return defaultPageable;
        }
        return fallbackPagable;
    }

    private Pageable defaultPageable(PageableDefaults pageableDefaults) {
        if (pageableDefaults == null) {
            return null;
        }
        int pageNumber = pageableDefaults.pageNumber();
        int pageSize = pageableDefaults.value();

        String[] sortStrArray = pageableDefaults.sort();
        Sort sort = null;

        for (String sortStr : sortStrArray) {
            String[] sortStrPair = sortStr.split("=");
            Sort newSort = new Sort(Sort.Direction.fromString(sortStrPair[1]), sortStrPair[0]);
            if (sort == null) {
                sort = newSort;
            } else {
                sort = sort.and(newSort);
            }
        }
        return new PageRequest(pageNumber, pageSize, sort);
    }

    private String getPagePrefix(MethodParameter parameter) {

        Qualifier qualifier = parameter.getParameterAnnotation(Qualifier.class);

        if (qualifier != null) {
            return new StringBuilder(((Qualifier) qualifier).value()).append("_").append(pagePrefix).toString();
        }

        return pagePrefix;
    }

    private String getSortPrefix(MethodParameter parameter) {

        Qualifier qualifier = parameter.getParameterAnnotation(Qualifier.class);

        if (qualifier != null) {
            return new StringBuilder(qualifier.value()).append("_").append(sortPrefix).toString();
        }

        return sortPrefix;
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
