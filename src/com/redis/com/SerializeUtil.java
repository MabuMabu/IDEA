package com.redis.com;

import java.io.*;

public class SerializeUtil {
    /**
     * 将对象序列化成byte[]
     * @param object
     * @return
     */
    public static byte[] serialize(Object object){
        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;
        try {
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            byte[] bytes = baos.toByteArray();
            return bytes;
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                oos.close();
                baos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 将byte[]反序列化成对象
     * @param bytes
     * @return
     */
    public static Object unserialize(byte[] bytes){
        ByteArrayInputStream bais = null;
        ObjectInputStream ois = null;
        try {
            bais = new ByteArrayInputStream(bytes);
            ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            try {
                ois.close();
                bais.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
