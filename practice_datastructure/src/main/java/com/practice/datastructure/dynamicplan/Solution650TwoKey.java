package com.practice.datastructure.dynamicplan;

public class Solution650TwoKey {
    public static void main(String[] args) {
        Solution650TwoKey s = new Solution650TwoKey();
        System.out.println(s.minSteps(6));
    }


    public int minSteps(int n) {
        if (n == 1) {
            return 0;
        }

        int[] arr = new int[n + 1];
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                if (i % j == 0) {
                    arr[i] = arr[j] + i/j;
                    arr[i] = Math.min(arr[i],arr[i/j]+j);
                }

            }
        }

        return arr[n];

    }


}
