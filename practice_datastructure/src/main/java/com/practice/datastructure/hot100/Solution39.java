package com.practice.datastructure.hot100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution39 {
    public static void main(String[] args) {
        int[] candidates = {2,3,6,7};
        System.out.println((new Solution39()).combinationSum(candidates, 7));
    }

    List<List<Integer>> lists = new ArrayList<>();
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        doPlus(candidates,target,new ArrayList<>(),0);
        return lists;
    }

    public void doPlus(int[] candidates,int target,List<Integer> list,int index){
        if(target==0){
            lists.add(new ArrayList<>(list));
            return;
        }
        if(target<0){
            return;
        }
        for (int i = index; i < candidates.length; i++) {
            if(candidates[i]>target){
                break;
            }
            list.add(candidates[i]);
            doPlus(candidates,target-candidates[i],list,i);
            list.remove(list.size()-1);
        }
    }
}
