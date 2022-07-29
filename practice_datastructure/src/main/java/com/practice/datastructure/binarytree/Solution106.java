package com.practice.datastructure.binarytree;

/**
 * @author MaoYongjie
 * @date 2022/7/28 16:08
 * @Description:
 */
//给定两个整数数组 inorder 和 postorder ，其中 inorder 是二叉树的中序遍历， postorder 是同一棵树的后序遍历，请你构造并
//返回这颗 二叉树 。
//
//
//
// 示例 1:
//
//
//输入：inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
//输出：[3,9,20,null,null,15,7]
//
//
// 示例 2:
//
//
//输入：inorder = [-1], postorder = [-1]
//输出：[-1]
//
//
//
//
// 提示:
//
//
// 1 <= inorder.length <= 3000
// postorder.length == inorder.length
// -3000 <= inorder[i], postorder[i] <= 3000
// inorder 和 postorder 都由 不同 的值组成
// postorder 中每一个值都在 inorder 中
// inorder 保证是树的中序遍历
// postorder 保证是树的后序遍历
//
// Related Topics 树 数组 哈希表 分治 二叉树
// 👍 795 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
public class Solution106 {
    public static void main(String[] args) {
        int[] inorder = {9,3,15,20,7};
        int[] postorder = {9,15,7,20,3};
        Solution106 s = new Solution106();
        TreeNode node = s.buildTree(inorder, postorder);
        System.out.println(node);
    }
    public TreeNode buildTree(int[] inorder, int[] postorder) {

        return build(inorder,postorder,0,inorder.length-1,0,postorder.length-1);
    }

    private TreeNode build(int[] inorder, int[] postorder, int inorder_left, int inorder_right, int post_left, int post_right) {
        if (inorder_left > inorder_right) {
            return null;
        }
        TreeNode node = new TreeNode(postorder[post_right]);

        int index = -1;
        for (int i = inorder_left; i <= inorder_right; i++) {
            if (inorder[i] == node.val) {
                index = i;
                break;
            }
        }
        int size = index - inorder_left;
        node.left = build(inorder, postorder, inorder_left, index - 1, post_left, post_left + size - 1);
        node.right = build(inorder, postorder, index + 1, inorder_right, post_left + size , post_right-1);
        return node;
    }
}
