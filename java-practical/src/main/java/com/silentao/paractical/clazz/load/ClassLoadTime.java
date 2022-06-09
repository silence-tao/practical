package com.silentao.paractical.clazz.load;

/**
 * @Description Class对象被加载的时机
 * 1、创建类的实例；
 * 2、访问类的静态变量；
 * 3、访问类的静态方法；
 * 4、反射如(Class.forName("my.xyz.Test"))；
 * 5、当初始化一个类时，发现其父类还未初始化，则先出发父类的初始化；
 * 6、虚拟机启动时，定义了main()方法的那个类先初始化。
 * @Author chentao10
 * @Date 2022/1/4 17:00
 **/
public class ClassLoadTime {

    public static void main(String[] args) {
        System.out.println("start main");

        new Candy();
        System.out.println("after load Candy");

        try {
            Class clazz1 = Class.forName("com.silentao.paractical.clazz.load.Gum");
            System.out.println("Class.forName class = " + clazz1.getName());

            Gum gum = new Gum();
            Class clazz2 = gum.getClass();
            System.out.println("getClass class = " + clazz2.getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("after Class.forName(\"com.silentao.paractical.clazz.load.Gum\")");

        new Cookie();
        System.out.println("after load cookie");
    }
}

class Candy {
    static {
        System.out.println("load Candy");
    }
}

class Gum {
    static {
        System.out.println("load Gum");
    }
}

class Cookie {
    static {
        System.out.println("load Cookie");
    }
}