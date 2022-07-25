package com.practice.datastructure.binarytree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author MaoYongjie
 * @date 2022/7/25 18:43
 * @Description:
 */
//给定两个整数数组 preorder 和 inorder ，其中 preorder 是二叉树的先序遍历， inorder 是同一棵树的中序遍历，请构造二叉树并
//返回其根节点。
//
//
//
// 示例 1:
//
//
//输入: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
//输出: [3,9,20,null,null,15,7]
//
// 示例 2:
//
//输入: preorder = [-1], inorder = [-1]
//输出: [-1]
//
// 提示:
// 1 <= preorder.length <= 3000
// inorder.length == preorder.length
// -3000 <= preorder[i], inorder[i] <= 3000
// preorder 和 inorder 均 无重复 元素
// inorder 均出现在 preorder
// preorder 保证 为二叉树的前序遍历序列
// inorder 保证 为二叉树的中序遍历序列
//
// Related Topics 树 数组 哈希表 分治 二叉树
// 👍 1671 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
public class Solution105 {

    public static void main(String[] args) {
        int[] preorder = {1,2};
        int[] inorder = {2,1};
        Solution105 s = new Solution105();
        s.buildTree(preorder,inorder);
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return build(preorder, inorder, 0, preorder.length-1, 0, inorder.length-1);
    }

    private TreeNode build(int[] preorder, int[] inorder, int preorder_left, int preorder_right, int inorder_left, int inorder_right) {

        if (preorder_left > preorder_right || inorder_left > inorder_right) {
            return null;
        }

        TreeNode node = new TreeNode(preorder[preorder_left]);

        int index = -1;
        for (int i = inorder_left; i <= inorder_right; i++) {
            if (inorder[i] == preorder[preorder_left]) {
                index = i;
                break;
            }
        }
        int left = index - inorder_left;
        node.left = build(preorder, inorder, preorder_left + 1, preorder_left + left, inorder_left, index-1);
        node.right = build(preorder, inorder, preorder_left + left + 1, preorder_right, index + 1, inorder_right);
        return node;
    }
}
