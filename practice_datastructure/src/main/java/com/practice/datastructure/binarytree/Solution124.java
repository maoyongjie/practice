package com.practice.datastructure.binarytree;

/**
 * @author MaoYongjie
 * @date 2022/8/8 19:00
 * @Description:
 */
//路径 被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列。同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个 节点，且不
//一定经过根节点。
//
// 路径和 是路径中各节点值的总和。
//
// 给你一个二叉树的根节点 root ，返回其 最大路径和 。
//
//
//
// 示例 1：
//
//
//输入：root = [1,2,3]
//输出：6
//解释：最优路径是 2 -> 1 -> 3 ，路径和为 2 + 1 + 3 = 6
//
// 示例 2：
//
//
//输入：root = [-10,9,20,null,null,15,7]
//输出：42
//解释：最优路径是 15 -> 20 -> 7 ，路径和为 15 + 20 + 7 = 42
//
//
//
//
// 提示：
//
//
// 树中节点数目范围是 [1, 3 * 104]
// -1000 <= Node.val <= 1000
//
// Related Topics 树 深度优先搜索 动态规划 二叉树
// 👍 1674 👎 0

//        根+左+右 x
//        根+左
//        根+右
//        根
//        左       x
//        右        x

//leetcode submit region begin(Prohibit modification and deletion)
public class Solution124 {
    public static void main(String[] args) {
        System.out.println(Integer.MIN_VALUE);
        Solution124 solution124 = new Solution124();
    }

    public static int max = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        int scan = scan(root);
        return Math.max(max, scan);
    }

    private int scan(TreeNode root) {

        if (root == null) {
            return Integer.MIN_VALUE;
        }
        int left = scan(root.left);
        int right = scan(root.right);
        max = Math.max(max, left);
        max = Math.max(max, right);
        max = Math.max(max, (left + right + root.val));
        return Math.max(left,right)+root.val;
    }
}
