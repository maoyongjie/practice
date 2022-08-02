package com.practice.datastructure.binarytree;

/**
 * @author MaoYongjie
 * @date 2022/8/1 19:28
 * @Description:
 */
public class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
