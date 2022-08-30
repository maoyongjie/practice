package com._线程池;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author MaoYongjie
 * @date 2022/8/29 13:48
 * @Description:
 */
@SuppressWarnings("all")
public class Test {
    public static void main(String[] args) {
        long begin = System.currentTimeMillis();
        // 自定义一个线程池
        test();
        // 循环创建10个CompletableFuture
//        List<CompletableFuture<Integer>> collect = IntStream.range(1, 10).mapToObj(i -> {
//            CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
//                // 在i=5的时候抛出一个NPE
//
//                try {
//                    // 每个依次睡眠1-9s，模拟线程耗时
//                    TimeUnit.SECONDS.sleep(2);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
////                System.out.println(i);
//                return i;
//            }, executorService)
//                    // 这里处理一下i=5时出现的NPE
//                    // 如果这里不处理异常，那么异常会在所有任务完成后抛出,小伙伴可自行测试
//                    .exceptionally(Error -> {
//                        try {
//                            TimeUnit.SECONDS.sleep(5);
//                            System.out.println(100);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                        return 100;
//                    });
//            return future;
//        }).collect(Collectors.toList());
//        // List列表转成CompletableFuture的Array数组,使其可以作为allOf()的参数
//        // 使用join()方法使得主线程阻塞，并等待所有并行线程完成
//        CompletableFuture.allOf(collect.toArray(new CompletableFuture[]{})).join();
//        System.out.println("最终耗时" + (System.currentTimeMillis() - begin) + "毫秒");
//        executorService.shutdown();
    }

    private static void test(){
        List<CompletableFuture<Integer>> collect = new ArrayList<>();
        ExecutorService executorService = Executors.newFixedThreadPool(16);
        for (int i = 0; i < 8; i++) {

            int finalI = i;
            collect.add(CompletableFuture.supplyAsync(() -> {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(finalI);
                return 0;
            }, executorService));
        }
        System.out.println("=======");
        long t1 = System.currentTimeMillis();
        CompletableFuture.allOf(collect.toArray(new CompletableFuture[]{})).join();
        System.out.println("消耗时间test:" + (System.currentTimeMillis() - t1));
    }
}
