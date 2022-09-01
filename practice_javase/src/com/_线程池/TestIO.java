package com._线程池;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.text.StrBuilder;
import cn.hutool.core.util.StrUtil;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author MaoYongjie
 * @date 2022/8/30 11:12
 * @Description:
 */
public class TestIO {
    public static void main(String[] args) throws InterruptedException {
        List<String> list  = new ArrayList<>();

        list.add("https://viid-plus-dev.devdolphin.com:21442/home/stand/haiyantest/ceshi/changzhousiheyi3/1(138).jpg?src=10.165.32.5&httptype=http");
        list.add("https://viid-plus-dev.devdolphin.com:21442/home/stand/haiyantest/ceshi/changzhousiheyi3/1(137).jpg?src=10.165.32.5&httptype=http");
        list.add("https://viid-plus-dev.devdolphin.com:21442/home/stand/haiyantest/ceshi/changzhousiheyi3/1(136).jpg?src=10.165.32.5&httptype=http");
        list.add("https://viid-plus-dev.devdolphin.com:21442/home/stand/haiyantest/ceshi/changzhousiheyi3/1(135).jpg?src=10.165.32.5&httptype=http");
        list.add("https://viid-plus-dev.devdolphin.com:21442/home/stand/haiyantest/ceshi/changzhousiheyi3/1(134).jpg?src=10.165.32.5&httptype=http");
        list.add("https://viid-plus-dev.devdolphin.com:21442/home/stand/haiyantest/ceshi/changzhousiheyi3/1(142).jpg?src=10.165.32.5&httptype=http");
        list.add("https://viid-plus-dev.devdolphin.com:21442/motorvehicle_20220830_11_0000000000000_e830d1d7-ad02-4626-9313-bb192e748838.jpeg?mt=/mnt&src=viid-plus-dev.devdolphin.com:21442&httptype=https");
        list.add("https://viid-plus-dev.devdolphin.com:21442/home/stand/haiyantest/ceshi/changzhousiheyi3/1(141).jpg?src=10.165.32.5&httptype=http");
        list.add("https://viid-plus-dev.devdolphin.com:21442/motorvehicle_20220830_11_0000000000000_d8cb3dfa-02ac-40f3-82dd-cd2bd02521d1.jpeg?mt=/mnt&src=viid-plus-dev.devdolphin.com:21442&httptype=https");
        list.add("https://viid-plus-dev.devdolphin.com:21442/home/stand/haiyantest/ceshi/changzhousiheyi3/1(140).jpg?src=10.165.32.5&httptype=http");
        list.add("https://viid-plus-dev.devdolphin.com:21442/home/stand/haiyantest/ceshi/changzhousiheyi3/1(14).jpg?src=10.165.32.5&httptype=http");
        list.add("https://viid-plus-dev.devdolphin.com:21442/home/stand/haiyantest/ceshi/changzhousiheyi3/1(139).jpg?src=10.165.32.5&httptype=http");
        ExecutorService executorService = Executors.newFixedThreadPool(12);
        int size = 6;
        CountDownLatch countDownLatch = new CountDownLatch(size);
        long t = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            int finalI = i;
            executorService.execute(() -> {
                Thread thread = Thread.currentThread();
                long t1 = System.currentTimeMillis();
                image2Base64(list.get(finalI));
                countDownLatch.countDown();
                if(list.get(finalI).equals("https://viid-plus-dev.devdolphin.com:21442/home/stand/haiyantest/ceshi/changzhousiheyi3/1(138).jpg?src=10.165.32.5&httptype=http"))
                System.out.println("單個"+(System.currentTimeMillis() - t1));
            });
        }
        countDownLatch.await();
        System.out.println(System.currentTimeMillis() - t);
    }

    public static String image2Base64(String imgUrl) {
        InputStream is = null;
        HttpURLConnection httpUrl = null;
        try {

            trustAllHttpsCertificates();
            HostnameVerifier hv = new HostnameVerifier() {
                @Override
                public boolean verify(String urlHostName, SSLSession session) {
                    return true;
                }
            };
            HttpsURLConnection.setDefaultHostnameVerifier(hv);

            URL url = new URL(imgUrl);
            Thread thread = Thread.currentThread();
            httpUrl = (HttpURLConnection) url.openConnection();
            httpUrl.setConnectTimeout(1000);
            httpUrl.connect();
            is = httpUrl.getInputStream();
//            Thread.sleep(1000);

            byte[] bytes = readBytes(is);
//            String encode = Base64.encode(bytes);
//            String encode = Base64.encode(is);
            return "";
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (httpUrl != null) {
                httpUrl.disconnect();
            }

//            log.warn(thread.getName()+":"+(System.currentTimeMillis()-t1));
        }
        return StrUtil.EMPTY;
    }

    private static void trustAllHttpsCertificates() throws Exception {
        javax.net.ssl.TrustManager[] trustAllCerts = new javax.net.ssl.TrustManager[1];
        javax.net.ssl.TrustManager tm = new miTM();
        trustAllCerts[0] = tm;
        javax.net.ssl.SSLContext sc = javax.net.ssl.SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, null);
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
    }

    static class miTM implements javax.net.ssl.TrustManager, javax.net.ssl.X509TrustManager {
        @Override
        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
            return null;
        }

        public boolean isServerTrusted(java.security.cert.X509Certificate[] certs) {
            return true;
        }

        public boolean isClientTrusted(java.security.cert.X509Certificate[] certs) {
            return true;
        }

        @Override
        public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType)
                throws java.security.cert.CertificateException {
            return;
        }

        @Override
        public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType)
                throws java.security.cert.CertificateException {
            return;
        }
    }

    public static byte[] readBytes(InputStream in) throws IOException {
        byte[] temp = new byte[in.available()];
        byte[] result = new byte[0];
        int size = 0;
        while ((size = in.read(temp)) != -1) {
            byte[] readBytes = new byte[size];
//            System.arraycopy(temp, 0, readBytes, 0, size);
//            result = mergeArray(result,readBytes);
        }
        return result;
    }

    public static byte[] mergeArray(byte[]... a) {
        // 合并完之后数组的总长度
        int index = 0;
        int sum = 0;
        for (int i = 0; i < a.length; i++) {
            sum = sum + a[i].length;
        }
        byte[] result = new byte[sum];
        for (int i = 0; i < a.length; i++) {
            int lengthOne = a[i].length;
            if(lengthOne==0){
                continue;
            }
            // 拷贝数组
            System.arraycopy(a[i], 0, result, index, lengthOne);
            index = index + lengthOne;
        }
        return result;
    }
}
