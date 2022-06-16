package com._反射;

import java.io.FileOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;

public class Mybatis {

    public static void save(Object o) throws Exception{
        //解析对象的字段和每个字段的值存储起来
        // 1.先得到class文件
        try (PrintStream ps = new PrintStream(
                new FileOutputStream("D:\\Data.txt",true)))
        {
            Class<?> c = o.getClass();
            ps.println("=============" + c.getSimpleName() + "=============");
            Field[] declaredFields = c.getDeclaredFields();
            for (Field declaredField : declaredFields) {
                String name = declaredField.getName();
                declaredField.setAccessible(true);
                String value = declaredField.get(o) + "";
                ps.println(name + '=' + value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
