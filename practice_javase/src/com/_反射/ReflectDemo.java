package com._反射;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;

/**
 * @author MaoYongjie
 * @date 2022/6/8 19:21
 * @Description: https://blog.csdn.net/qq_44715943/article/details/120587716
 */
public class ReflectDemo {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchFieldException {
        //一、获取Class的三种方式 即字节码

        //1.Class.forName(“完整类名带包名”)
        //如果你只是希望一个类的静态代码块执行，其它代码一律不执行，可以使用Class.forName
        Class<?> aClass = Class.forName("com._反射.User");
        User user = new User();
        //2.对象.getClass()
        Class<? extends User> bClass = user.getClass();
        //3.任何类型.class
        Class<User> cClass = User.class;

        //二、通过反射实例化对象
        //对象.newInstance() newInstance()方法内部实际上调用了无参数构造方法，必须保证无参构造存在才可以。
        //否则会抛出java.lang.InstantiationException异常。
        Object o = aClass.newInstance();
        user = (User) o;
        System.out.println(user);

        //三、反射Filed【反射/反编译一个类的属性】
        /*
        public T newInstance()	创建对象
        public String getName()	返回完整类名带包名
        public String getSimpleName()	返回类名
        public Field[] getFields()	返回类中public修饰的属性
        public Field[] getDeclaredFields()	返回类中所有的属性
        public Field getDeclaredField(String name)	根据属性名name获取指定的属性
        public native int getModifiers()	获取属性的修饰符列表,返回的修饰符是一个数字，每个数字是修饰符的代号【一般配合Modifier类的toString(int x)方法使用】
        public Method[] getDeclaredMethods()	返回类中所有的实例方法
        public Method getDeclaredMethod(String name, Class<?>… parameterTypes)	根据方法名name和方法形参获取指定方法
        public Constructor<?>[] getDeclaredConstructors()	返回类中所有的构造方法
        public Constructor getDeclaredConstructor(Class<?>… parameterTypes)	根据方法形参获取指定的构造方法
        ----	----
        public native Class<? super T> getSuperclass()	返回调用类的父类
        public Class<?>[] getInterfaces()	返回调用类实现的接口集合
         */
        System.out.println(aClass.getName());
        System.out.println(aClass.getSimpleName());
        System.out.println(Arrays.toString(aClass.getFields()));
        System.out.println(Arrays.toString(aClass.getDeclaredFields()));
        Field userName = aClass.getDeclaredField("userName");
        System.out.println(userName);
        System.out.println(Modifier.toString(aClass.getModifiers()));
        System.out.println(Arrays.toString(aClass.getDeclaredMethods()));
    }
}
