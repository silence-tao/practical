package com.silentao.paractical.pattern;

/**
 * @Description 单例模式
 * @Author chentao10
 * @Date 2022/6/10 17:12
 **/
public class SingletonPattern {

    /**
     * 懒汉式单例
     */
    private static class LazySingleton {

        /**
         * 单例对象
         * 使用volatile关键字可以保证在多线程场景下
         * INSTANCE对象的变动可以立马被所有线程知道
         */
        private static volatile LazySingleton INSTANCE = null;

        /**
         * 构造方法私有化
         * 避免类在外部被实例化
         */
        private LazySingleton() {

        }

        /**
         * 获取单例对象
         * 通过同步代码块保证线程安全
         * 使用同步代码块可以避免每次获取单例对象都加同步锁
         * @return
         */
        public static LazySingleton getInstance() {
            if (INSTANCE == null) {
                synchronized (LazySingleton.class) {
                    // double check保证单例对象只被创建一次
                    if (INSTANCE == null) {
                        INSTANCE = new LazySingleton();
                    }
                }
            }

            return INSTANCE;
        }

        /**
         * 获取单例对象
         * 通过同步方法保证线程安全
         * @return
         */
        public static synchronized LazySingleton getInstanceSyncMethod() {
            if (INSTANCE == null) {
                INSTANCE = new LazySingleton();
            }

            return INSTANCE;
        }
    }

    /**
     * 饿汉式单例
     */
    public static class HungrySingleton {

        /**
         * 单例对象
         * 饿汉式单例在类初始化的时候就创建好了单例对象
         */
        private static HungrySingleton INSTANCE = new HungrySingleton();

        /**
         * 构造方法私有化
         * 避免类在外部被实例化
         */
        private HungrySingleton() {

        }

        /**
         * 因为饿汉式单例在类初始化的时候就创建好了单例对象
         * 所以在多线程场景下也不会出现线程安全问题
         * 故不需要任何方式的同步
         * @return
         */
        public static HungrySingleton getInstance() {
            return INSTANCE;
        }
    }
}
