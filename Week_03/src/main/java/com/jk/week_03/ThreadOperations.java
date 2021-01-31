package com.jk.week_03;

import jdk.nashorn.internal.codegen.CompilerConstants;

import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 02nio/nio02 文件夹下，实现以后，代码提交到 GitHub。
 * 1.（必做）整合你上次作业的 httpclient/okhttp；
 * 2.（选做）使用 netty 实现后端 http 访问（代替上一步骤）
 * 周日作业题目：（要求提交必做题目）
 * 1.（必做）实现过滤器。
 * 2.（选做）实现路由。
 * 3.（选做）跑一跑课上的各个例子，加深对多线程的理解
 * 4.（选做），完善网关的例子试着调整其中的线程池参数
 * @author wei.huang
 * @version Id: ThreadOperations.java, v 0.1 2021年01月28日  17:47 wei.huang Exp $
 */
public class ThreadOperations {
    /**
     * 本周作业：（必做）使用多种方法实现在main 函数启动一个新的线程或者线程池
     * 异步运行一个方法拿到这个方法的返回之后，退出主线程。
     */
    public static void main(String[] args) {
         methord1();
    }

    /**
     * 1、通过join让主线程阻塞直到子线程执行结束；通
     * 2、过Future 获得线程返回值。
     */
    public static void methord1() {
        String thread1_result = "";
        try {
            // 开启一个带返回值的线程
            FutureTask<String> task1 = new FutureTask<>(new Thread1());
            Thread a = new Thread(task1);
            a.start();
            a.join();
            thread1_result = task1.get();
            System.out.println(thread1_result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    public static void methord2(){

    }

    static class Thread1 implements Callable<String> {

        @Override public String call() throws Exception {
            return "thread 1";
        }
    }
}


