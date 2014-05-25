package com.quanix.memtos.server.util;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.session.Session;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author : lihaoquan
 */
public class ShiroSerializableUtils {


    /**
     * 序列化
     * @param session
     * @return
     */
    public static String serialize(Session session) {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(session);
            return Base64.encodeToString(outputStream.toByteArray());
        }catch (Exception e) {
            throw new RuntimeException("serialize session error");
        }
    }


    /**
     * 反序列化
     * @param sessionStr
     * @return
     */
    public static Session deserialize(String sessionStr) {
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(Base64.decode(sessionStr));
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            return (Session) objectInputStream.readObject();
        }catch (Exception e) {
            throw new RuntimeException("deserialze session error");
        }
    }
}
