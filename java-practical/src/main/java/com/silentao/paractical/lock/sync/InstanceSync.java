package com.silentao.paractical.lock.sync;

/**
 * @Description 实例对象锁
 * @Author chentao10
 * @Date 2021/12/31 15:23
 **/
public class InstanceSync implements Runnable {

    /**
     * 临界资源
     */
    private static int i = 0;

    /**
     * 作用于实例方法
     * 锁的对象是当前的实例对象
     */
    public synchronized void increase() {
        i++;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100000; i++) {
            increase();
        }
    }

    public static void main(String[] args) throws Exception {
        goodCase();
        badCase();
    }

    /**
     * 不同的线程获取同一个实例对象锁
     * 才能起到线程安全到作用
     * @throws Exception
     */
    private static void goodCase() throws Exception {
        InstanceSync instanceSync = new InstanceSync();
        Thread t1 = new Thread(instanceSync);
        Thread t2 = new Thread(instanceSync);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(i);
    }

    /**
     * 不同到线程获取不同的实例对象锁
     * 就起不到线程安全到作用了
     * @throws Exception
     */
    private static void badCase() throws Exception {
        Thread t1 = new Thread(new InstanceSync());
        Thread t2 = new Thread(new InstanceSync());

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(i);
    }
}
