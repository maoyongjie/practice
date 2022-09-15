package com.practice.datastructure.sort;

import java.util.Arrays;

/**
 * @author MaoYongjie
 * @date 2022/9/15 18:38
 * @Description:
 */
public class BubbleSort implements Sort {
    public static void main(String[] args) {
        int[] arr = {3,44,48,5,9,6,2,1,85,63,99};
        (new BubbleSort()).doSort(arr);
        System.out.println(Arrays.toString(arr));
    }


    @Override
    public void doSort(int[] arr) {
        int length = arr.length;
        for (int j = 0; j < length-1; j++) {
            System.out.println(j);
            boolean isSwap = false;
            for (int i = 0; i < length - 1; i++) {
                if (arr[i] > arr[i + 1]) {
                    swap(arr, i, i + 1);
                    isSwap = true;
                }
            }
            if(!isSwap){
                break;
            }
        }

    }
}
