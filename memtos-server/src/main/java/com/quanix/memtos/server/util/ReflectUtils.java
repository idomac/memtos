package com.quanix.memtos.server.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * created by lihaoquan
 *
 * 反射工具类
 */
public class ReflectUtils {

    /**
     * 在制度的位置index获取指定类型
     * @param clazz
     * @param index
     * @param <T>
     * @return
     */
    public static <T> Class<T> findParameterizedType(Class<?> clazz, int index) {
        Type parameterizedType = clazz.getGenericSuperclass();
        if (!(parameterizedType instanceof ParameterizedType)) {
            parameterizedType = clazz.getSuperclass().getGenericSuperclass();
        }

        if (!(parameterizedType instanceof  ParameterizedType)) {
            return null;
        }

        Type[] actualTypeArguments = ((ParameterizedType) parameterizedType).getActualTypeArguments();

        if (actualTypeArguments == null || actualTypeArguments.length == 0) {
            return null;
        }
        return (Class<T>) actualTypeArguments[index];
    }
}
