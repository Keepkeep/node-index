package com.example.springboot.Test;

/**
 * @version desc
 * @QUTHOR hdq
 * @date 2021/4/21
 */
public class ThreadTest {

    private static ThreadLocal<String>  threadLocal = new ThreadLocal<String>();
    public static void main(String[] args) {
        Thread thread = Thread.currentThread();
        String name = thread.getName();
        threadLocal.set("test");
    }
}
