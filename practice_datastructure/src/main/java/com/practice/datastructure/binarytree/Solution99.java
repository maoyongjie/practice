package com.practice.datastructure.binarytree;

import java.util.ArrayList;

/**
 * @author MaoYongjie
 * @date 2022/7/18 16:24
 * @Description:
 */
public class Solution99 {

    public static void main(String[] args) {
        TreeNode node = new TreeNode(1);
        node.left = new TreeNode(2);
        Solution99 solution99 = new Solution99();
        solution99.recoverTree(node);
    }

    private void recoverTree(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();

        inorder(root, list);
        int size = list.size();
        int a = 0;
        int b = 0;
        for (int i = 0; i < size; i++) {

            if (i == 0 && list.get(0) > list.get(1)) {
                a = 0;
            }
            if (i == size-1 && list.get(size-2) > list.get(size-1)){
                b = size-1;
            }

            if (i > 0 && i < size - 1 && list.get(i) > list.get(i + 1) && list.get(i) > list.get(i - 1)) {
                if (list.get(i - 1) < list.get(i + 1)) {
                    a = i;
                }
            }

            if (i > 0 && i < size - 1 && list.get(i) < list.get(i + 1) && list.get(i) < list.get(i - 1)) {
                if (list.get(i - 1) < list.get(i + 1)) {
                    b = i;
                }
            }
        }

        revert(root,list.get(a),list.get(b));

    }

    private void inorder(TreeNode root, ArrayList<Integer> list) {
        if (root == null) {
            return;
        }
        inorder(root.left, list);
        list.add(root.val);
        inorder(root.right, list);
    }

    private void revert(TreeNode root, int v1, int v2) {
        if (root == null) {
            return;
        }
        revert(root.left, v1, v2);
        if (root.val == v1) {
            root.val = v2;
        } else if (root.val == v2) {
            root.val = v1;
        }
        revert(root.right, v1, v2);
    }
}
