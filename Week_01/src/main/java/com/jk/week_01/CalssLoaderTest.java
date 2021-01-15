package com.jk.week_01;

import java.net.URL;

/**
 * @author wei.huang
 * @version Id: CalssLoaderTest.java, v 0.1 2021年01月13日  20:43 wei.huang Exp $
 */
public class CalssLoaderTest {

    public static void main(String[] args) {
        URL[] urls = sun.misc.Launcher.getBootstrapClassPath().getURLs();
        System.out.println("启动类加载器。。。");
        for (URL url : urls) {
            System.out.println(">>>>" + url);
        }

        printClassLoader("扩展类加载器", CalssLoaderTest.class.getClassLoader().getParent());

        printClassLoader("应用扩展类加载器", CalssLoaderTest.class.getClassLoader());
    }

    private static void printClassLoader(String name, ClassLoader classLoader) {
        if (classLoader != null) {
            System.out.println(name + classLoader.toString());
        } else {
            System.out.println(name + ">>> null");
        }
    }
}
