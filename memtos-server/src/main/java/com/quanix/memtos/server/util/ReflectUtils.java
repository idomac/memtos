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

        //返回本类的父类,包含泛型参数信息 : ClassA<T>
        Type parameterizedType = clazz.getGenericSuperclass();

        if (!(parameterizedType instanceof ParameterizedType)) {
            parameterizedType = clazz.getSuperclass().getGenericSuperclass();
        }

        if (!(parameterizedType instanceof  ParameterizedType)) {
            return null;
        }

        //获取泛型参数类型数组
        Type[] actualTypeArguments = ((ParameterizedType) parameterizedType).getActualTypeArguments();

        if (actualTypeArguments == null || actualTypeArguments.length == 0) {
            return null;
        }
        //按照本项目的风格,domain排在第0个 , 如 UserDao<User,Long>
        return (Class<T>) actualTypeArguments[index];
    }
}
