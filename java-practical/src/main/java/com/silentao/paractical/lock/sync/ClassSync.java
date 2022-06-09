package com.silentao.paractical.lock.sync;

/**
 * @Description 类对象锁
 * @Author chentao10
 * @Date 2021/12/31 15:36
 **/
public class ClassSync implements Runnable {

    /**
     * 临界资源
     */
    private static int i = 0;

    /**
     * 作用于静态方法
     * 锁的对象是当前类对应的Class对象
     */
    public static synchronized void increase() {
        i++;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100000; i++) {
            increase();
        }
    }

    public static void main(String[] args) throws Exception {
        Thread t1 = new Thread(new ClassSync());
        Thread t2 = new Thread(new ClassSync());

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(i);


    }
}
