package com.practice.datastructure.hot100;

public class Solution4 {
    public static void main(String[] args) {
        int[] nums1 = {1, 4,5,6,7,8 };
        int[] nums2 = {2};
        System.out.println((new Solution4()).findMedianSortedArrays(nums1, nums2));
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int p1 = 0;
        int p2 = 0;
        int count = 0;
        int pre = 0;
        int now = 0;
        int len = nums1.length + nums2.length;
        while (count < (nums1.length + nums2.length) / 2) {
            pre = now;
            if (p1 == nums1.length) {
                now=nums2[p2++];
            } else if (p2 == nums2.length) {
                now=nums2[p1++];
            } else {
                if (nums1[p1] < nums2[p2]) {
                    now=nums2[p1++];
                } else {
                    now=nums2[p2++];
                }
            }

            count++;
        }



        System.out.println(p1);
        System.out.println(p2);

        if ((len & 1) == 0)
            return (pre + now) / 2.0;
        else
            return now;



    }
}
