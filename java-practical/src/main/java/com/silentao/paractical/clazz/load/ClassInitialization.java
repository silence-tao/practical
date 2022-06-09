package com.silentao.paractical.clazz.load;

import java.util.Random;

/**
 * @Description 验证：通过字面常量获取Class引用时，只触发了加载阶段
 * @Author chentao10
 * @Date 2022/1/4 17:26
 **/
public class ClassInitialization {
    public static Random rand = new Random(100);

    public static void main(String[] args) throws Exception {
        // 字面常量方式获取Class对象
        Class initable1Class = Initable1.class;
        System.out.println("after get Initable1 Class");
        // 不触发类初始化
        System.out.println(Initable1.staticFinal1);
        // 会触发类初始化
        System.out.println(Initable1.staticFinal2);
        // 会触发类初始化
        System.out.println(Initable2.staticNumber);
        // forName方式获取Class对象，会触发类初始化
        Class initable3Class = Class.forName("com.silentao.paractical.clazz.load.Initable3");
        System.out.println("after get Initable3 Class");
        System.out.println(Initable3.staticNumber);
    }
}

class Initable1 {

    /**
     * 编译期静态常量
     */
    static final int staticFinal1 = 1;

    /**
     * 非编译期静态常量
     */
    static final int staticFinal2 = ClassInitialization.rand.nextInt(1000);

    static {
        System.out.println("Initializing Initable1");
    }
}

class Initable2 {

    /**
     * 静态成员变量
     */
    static int staticNumber = 2;

    static {
        System.out.println("Initializing Initable2");
    }
}

class Initable3 {

    /**
     * 静态成员变量
     */
    static int staticNumber = 3;

    static {
        System.out.println("Initializing Initable3");
    }
}