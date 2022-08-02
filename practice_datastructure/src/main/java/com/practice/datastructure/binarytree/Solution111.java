package com.practice.datastructure.binarytree;

/**
 * @author MaoYongjie
 * @date 2022/8/2 18:30
 * @Description:
 */
//给定一个二叉树，找出其最小深度。
//
// 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
//
// 说明：叶子节点是指没有子节点的节点。
//
//
//
// 示例 1：
//
//
//输入：root = [3,9,20,null,null,15,7]
//输出：2
//
//
// 示例 2：
//
//
//输入：root = [2,null,3,null,4,null,5,null,6]
//输出：5
//
//
//
//
// 提示：
//
//
// 树中节点数的范围在 [0, 105] 内
// -1000 <= Node.val <= 1000
//
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树
// 👍 810 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
public class Solution111 {

    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        } else if (root.left == null && root.right != null) {
            return minDepth(root.right) + 1;
        } else if (root.right == null && root.left != null) {
            return minDepth(root.left) + 1;
        } else if (root.right == null) {
            return 1;
        } else {
            return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
        }
    }
}
