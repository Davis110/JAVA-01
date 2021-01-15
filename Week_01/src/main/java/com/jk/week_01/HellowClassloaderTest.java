package com.jk.week_01;

import java.io.*;
import java.util.Base64;

/**
 * @author wei.huang
 * @version Id: HellowTest.java, v 0.1 2021年01月13日  19:26 wei.huang Exp $
 */
public class HellowClassloaderTest extends ClassLoader {

    public static void main(String[] args) {
        try {
            Object instance = new HellowClassloaderTest().findClass("Hello.xlass").newInstance();
            instance.getClass().getMethod("hello").invoke(instance);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Class<?> findClass(String name) {
        byte[] newByte = new byte[0];
        try {
            // 字节码读取
            InputStream ins = HellowClassloaderTest.class.getClassLoader().getResourceAsStream(name);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            int len = 0;
            while ((len = ins.read()) != -1) {
                bos.write(len);
            }
            newByte  = bos.toByteArray();
        }catch (IOException e) {
            e.printStackTrace();
        }

        // 字节码转换
        for (int j = 0; j < newByte.length; j++) {
            newByte[j] = (byte) (255 - newByte[j]);
        }

        String className = name.substring(0, name.lastIndexOf("."));
        return defineClass(className, newByte, 0, newByte.length);
    }

    private static byte[] decode(String str) {
        return Base64.getDecoder().decode(str);
    }

}
