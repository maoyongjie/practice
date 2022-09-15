package com.practice.datastructure.sort;

public interface Sort {
    public void doSort(int[] arr);

    default void swap(int[] arr,int a,int b){
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
