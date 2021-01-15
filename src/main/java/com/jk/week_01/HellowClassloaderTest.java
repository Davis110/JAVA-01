package com.onc.op.api.task;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Base64;

/**
 * @author wei.huang
 * @version Id: HellowTest.java, v 0.1 2021年01月13日  19:26 wei.huang Exp $
 */
public class HellowClassloaderTest extends ClassLoader {

    public static void main(String[] args) {
        try {
            Object instance = new HellowClassloaderTest().findClass("D:/JAVA-WORK/gitHub/JAVA-01/Week_01/Hello/Hello.xlass").newInstance();
            instance.getClass().getMethod("hello").invoke(instance);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected Class<?> findClass(String name) {
        byte[] bytes = new byte[1024];
        try {
            FileInputStream fis = new FileInputStream(name);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            int len=-1;int i=0;
            while ((len = fis.read()) != -1){
                bytes[i] = (byte) (255 - len);
                i++;
            }
            fis.close();
            bos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return defineClass("Hello", bytes, 0, bytes.length);
    }

    private static byte[] decode(String str) {
        return Base64.getDecoder().decode(str);
    }

}
