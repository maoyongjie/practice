package com.practice.springboot.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * @author MaoYongjie
 * @date 2022/3/29 15:01
 * @Description:
 */
public class ReadJson {
    public static void main(String[] args) {
        BufferedReader br = null;
        String path = "省市区县三级联动.json";
        try {
            br = new BufferedReader(new InputStreamReader(ReadJson.class.getClassLoader().getResourceAsStream(path)));
            String line = br.readLine();
            StringBuilder sb = new StringBuilder();
            while (line != null) {
                sb.append(line + "\r\n");
                line = br.readLine();
            }
            ArrayList arrayList = JSON.parseObject(sb.toString(), ArrayList.class);
            System.out.println(arrayList.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
