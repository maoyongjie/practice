package com.practice.datastructure.binarytree;

/**
 * @author MaoYongjie
 * @date 2022/8/4 15:21
 * @Description:
 */
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
