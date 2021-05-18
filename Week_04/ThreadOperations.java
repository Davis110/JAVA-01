package com.jk.week_03;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 02nio/nio02 文件夹下，实现以后，代码提交到 GitHub。
 * 1.（必做）整合你上次作业的 httpclient/okhttp；
 * 2.（选做）使用 netty 实现后端 http 访问（代替上一步骤）
 * 周日作业题目：（要求提交必做题目）
 * 1.（必做）实现过滤器。
 * 2.（选做）实现路由。
 * 3.（选做）跑一跑课上的各个例子，加深对多线程的理解
 * 4.（选做），完善网关的例子试着调整其中的线程池参数
 *
 * @author wei.huang
 * @version Id: ThreadOperations.java, v 0.1 2021年01月28日  17:47 wei.huang Exp $
 */
public class ThreadOperations {
    /**
     * 本周作业：（必做）使用多种方法实现在main 函数启动一个新的线程或者线程池
     * 异步运行一个方法拿到这个方法的返回之后，退出主线程。
     */
    public static void main(String[] args) throws InterruptedException {
        //methord1();
        //methord2();
        //methord3();
        //methord4();
        methord5();
    }

    /**
     * 1、通过Future.get()方法让主线程阻塞。
     */
    public static void methord1() throws ExecutionException, InterruptedException {
        int thread1_result = 0;
        long start = System.currentTimeMillis();
        // 开启一个带返回值的线程
        FutureTask<Integer> task1 = new FutureTask<>(new Thread1());
        Thread a = new Thread(task1);
        a.start();
        thread1_result = task1.get();
        System.out.println("异步执行结果：" + thread1_result);
        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");

    }

    /**
     * 2、通过join方法阻塞主线程
     */
    public static void methord2() throws InterruptedException {
        AtomicInteger sum = new AtomicInteger();
        long start = System.currentTimeMillis();
        Thread a = new Thread(() -> {
            sum.set(sum());
        });
        a.start();
        a.join();
        System.out.println("异步执行结果：" + sum);
        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");
    }

    /**
     * 3、使用CountDownLatch 让子线程在主线程中聚合
     */
    public static void methord3() throws InterruptedException {
        AtomicInteger sum = new AtomicInteger();
        long start = System.currentTimeMillis();
        CountDownLatch latch = new CountDownLatch(1);
        Thread a = new Thread(() -> {
            sum.set(sum());
            latch.countDown();
        });
        a.start();
        latch.await();
        System.out.println("异步执行结果：" + sum);
        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");
    }

    /**
     * 4、使用wait和notify
     */
    public static void methord4() throws InterruptedException {
        AtomicInteger sum = new AtomicInteger();
        Object obj = new Object();
        long start = System.currentTimeMillis();
        Thread a = new Thread(() -> {
            sum.set(sum());
            synchronized (obj) {
                obj.notify();
            }
        });
        a.start();
        synchronized (obj) {
            obj.wait();
        }
        System.out.println("异步执行结果：" + sum);
        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");

    }

    public static void methord5() throws InterruptedException {
        AtomicInteger sum = new AtomicInteger();
        long start = System.currentTimeMillis();
        Thread a = new Thread(() -> {
            sum.set(sum());
        });
        a.start();
        System.out.println("异步执行结果：" + sum);
        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");
    }

    static class Thread1 implements Callable<Integer> {

        @Override public Integer call() throws Exception {
            return sum();
        }
    }

    private static int sum() {
        return fibo(36);
    }

    private static int fibo(int a) {
        if (a < 2)
            return 1;
        return fibo(a - 1) + fibo(a - 2);
    }
}


