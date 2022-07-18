package com.practice.datastructure.binarytree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author MaoYongjie
 * @date 2022/7/18 17:23
 * @Description:
 */
public class Solution101 {
    public static void main(String[] args) {
        TreeNode node = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(2);
        node.left = node1;
        node.right = node2;
        node1.left = new TreeNode(2);
//        node1.right = new TreeNode(4);
        node2.left = new TreeNode(2);
//        node2.right = new TreeNode(3);

        System.out.println((new Solution101()).isSymmetric(node));
    }


        public boolean isSymmetric(TreeNode root) {
            return check(root, root);
        }

        public boolean check(TreeNode p, TreeNode q) {
            if (p == null && q == null) {
                return true;
            }
            if (p == null || q == null) {
                return false;
            }
            return p.val == q.val && check(p.left, q.right) && check(p.right, q.left);
        }



}
