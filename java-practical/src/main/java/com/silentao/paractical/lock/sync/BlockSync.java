package com.silentao.paractical.lock.sync;

/**
 * @Description 同步代码块
 * @Author chentao10
 * @Date 2021/12/31 15:54
 **/
public class BlockSync implements Runnable {

    /**
     * 锁对象
     */
    private static BlockSync instance = new BlockSync();

    /**
     * 临界资源
     */
    private static int i = 0;

    @Override
    public void run() {
        blockInstanceSync();
    }

    /**
     * 将锁作用于代码块
     */
    private void blockInstanceSync() {
        // 锁的对象就是instance对象
        synchronized (instance) {
            for (int j = 0; j < 100000; j++) {
                i++;
            }
        }
    }

    /**
     * 将锁作用于代码块
     */
    private void blockThisSync() {
        // 锁对象也可以是当前实例对象
        synchronized (this) {
            for (int j = 0; j < 100000; j++) {
                i++;
            }
        }
    }

    /**
     * 将锁作用于代码块
     */
    private void blockClassSync() {
        // 锁对象还可以是当前类对应的Class对象
        synchronized (BlockSync.class) {
            for (int j = 0; j < 100000; j++) {
                i++;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(i);
    }
}
