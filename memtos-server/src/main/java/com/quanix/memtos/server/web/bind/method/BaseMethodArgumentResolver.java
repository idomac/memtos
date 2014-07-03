package com.quanix.memtos.server.web.bind.method;

import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.HandlerMapping;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * created by lihaoquan
 *
 * 自定义Spring MVC3的参数映射和返回值映射
 *
 * SpringMVC 3.1 提供了HandlerMethodArgumentResolver以支持更灵活的自定义的数据绑定方式。
 *
 * 简单参考:https://github.com/spring-projects/spring-framework/blob/master/spring-webmvc/src/main/java/org/springframework/web/servlet/mvc/method/annotation/PathVariableMapMethodArgumentResolver.java
 *
 */
public abstract class BaseMethodArgumentResolver implements HandlerMethodArgumentResolver {

    /**
     * 获取指定前缀的参数：包括uri varaibles 和 parameters
     *
     * @param namePrefix
     * @param request
     * @return
     * @subPrefix 是否截取掉namePrefix的前缀
     */
    protected Map<String, String[]> getPrefixParameterMap(String namePrefix, NativeWebRequest request, boolean subPrefix) {
        Map<String, String[]> result = new HashMap<String, String[]>();
        Map<String, String> variables = getUriTemplateVariables(request);

        int namePrefixLength = namePrefix.length();
        for (String name : variables.keySet()) {
            if (name.startsWith(namePrefix)) {

            }
        }

        /** 遍历request的参数 **/
        Iterator<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasNext()) {
            String name = parameterNames.next();
            if (name.startsWith(namePrefix)) {
                if(subPrefix) {
                    char ch = name.charAt(namePrefix.length());
                    if(illegalChar(ch)) {
                        continue;
                    }
                    result.put(name.substring(namePrefixLength + 1), request.getParameterValues(name));
                }
            }else {
                result.put(name, request.getParameterValues(name));
            }
        }
        return result;
    }

    private boolean illegalChar(char ch) {
        return ch != '.' && ch != '_' && !(ch >= '0' && ch <= '9');
    }

    @SuppressWarnings("unchecked")
    protected final Map<String, String> getUriTemplateVariables(NativeWebRequest request) {
        Map<String, String> variables =
                (Map<String, String>) request.getAttribute(
                        HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE, RequestAttributes.SCOPE_REQUEST);
        return (variables != null) ? variables : Collections.<String, String>emptyMap();
    }
}
