package com._字节流的使用;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class FileInputStreamDemo01 {
    public static void main(String[] args) {
//        Integer a = Integer.valueOf(128);
//        Integer b = 128;
//        System.out.println(a == b);
//        System.out.println(System.getProperty("user.dir"));
//        String s = "1";
//        System.out.println(append(s));
//        try (FileOutputStream output = new FileOutputStream("output.txt")) {
//            byte[] array = "JavaGuide".getBytes();
//            output.write(array);
//            output.flush();
//            output.write(array);
//            output.flush();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        try (FileReader fileReader = new FileReader("output.txt");) {
            int content;
            long skip = fileReader.skip(3);
            System.out.println("The actual number of bytes skipped:" + skip);
            System.out.print("The content read from file:");
            while ((content = fileReader.read()) != -1) {
                System.out.print((char) content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String append(String s){
        s +="aaa";
        return s;
    }
}
