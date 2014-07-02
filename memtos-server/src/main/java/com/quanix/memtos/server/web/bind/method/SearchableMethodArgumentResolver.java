package com.quanix.memtos.server.web.bind.method;

import com.quanix.memtos.server.plugin.search.Searchable;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Map;

/**
 * created by lihaoquan
 */
public class SearchableMethodArgumentResolver extends BaseMethodArgumentResolver {

    private static final String DEFAULT_SEARCH_PREFIX = "search";

    private String prefix = DEFAULT_SEARCH_PREFIX;

    /**
     * 参数支持类型
     * @param parameter
     * @return
     */
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return Searchable.class.isAssignableFrom(parameter.getParameterType());
    }


    /**
     * 参数解析对象封装
     * @param parameter
     * @param mavContainer
     * @param webRequest
     * @param binderFactory
     * @return
     * @throws Exception
     */
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
        NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        String searchPrefix = prefix;
        Qualifier qualifier = parameter.getParameterAnnotation(Qualifier.class);
        if(qualifier != null) {
            searchPrefix = new StringBuilder(qualifier.value()).append("_").append(prefix).toString();
        }

        Map<String, String[]> searcheableMap = getPrefixParameterMap(searchPrefix, webRequest, true);

        return null;
    }
}
