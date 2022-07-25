package com.practice.datastructure.binarytree;


import java.util.*;

/**
 * @author MaoYongjie
 * @date 2022/7/19 16:49
 * @Description:
 */
//给你二叉树的根节点 root ，返回其节点值的 层序遍历 。 （即逐层地，从左到右访问所有节点）。
//
//
//
// 示例 1：
//
//
//输入：root = [3,9,20,null,null,15,7]
//输出：[[3],[9,20],[15,7]]
//
//
// 示例 2：
//
//
//输入：root = [1]
//输出：[[1]]
//
//
// 示例 3：
//
//
//输入：root = []
//输出：[]
//
//
//
//
// 提示：
//
//
// 树中节点数目在范围 [0, 2000] 内
// -1000 <= Node.val <= 1000
//
// Related Topics 树 广度优先搜索 二叉树
// 👍 1391 👎 0
public class Solution102 {


    public List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> lists = new ArrayList<>();
        if (root == null) {
            return lists;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>(size);
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                list.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            lists.add(list);
        }
        return lists;
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        List<List<Integer>> lists = new ArrayList<>();
        int level = 0;
        inOrder(root, map, level);
        for (int i = 0; i < 2000; i++) {
            if (map.containsKey(i)) {
                lists.add(map.get(i));
            } else {
                break;
            }
        }
        return lists;
    }

    private void inOrder(TreeNode root, Map<Integer, List<Integer>> map, int level) {

        if (root == null) {
            return;
        }
        inOrder(root.left, map, level + 1);
        if (map.containsKey(level)) {
            map.get(level).add(root.val);
        } else {
            List<Integer> list = new ArrayList<>();
            list.add(root.val);
            map.put(level, list);
        }
        inOrder(root.right, map, level + 1);
    }
}
