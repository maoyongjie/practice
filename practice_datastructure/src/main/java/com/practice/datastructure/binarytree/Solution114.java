package com.practice.datastructure.binarytree;

import com.sun.jmx.remote.internal.ArrayQueue;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @author MaoYongjie
 * @date 2022/8/3 19:15
 * @Description:
 */
//给你二叉树的根结点 root ，请你将它展开为一个单链表：
//
//
// 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
// 展开后的单链表应该与二叉树 先序遍历 顺序相同。
//
//
//
//
// 示例 1：
//
//
//输入：root = [1,2,5,3,4,null,6]
//输出：[1,null,2,null,3,null,4,null,5,null,6]
//
//
// 示例 2：
//
//
//输入：root = []
//输出：[]
//
//
// 示例 3：
//
//
//输入：root = [0]
//输出：[0]
//
//
//
//
// 提示：
//
//
// 树中结点数在范围 [0, 2000] 内
// -100 <= Node.val <= 100
//
//
//
//
// 进阶：你可以使用原地算法（O(1) 额外空间）展开这棵树吗？
// Related Topics 栈 树 深度优先搜索 链表 二叉树
// 👍 1259 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
public class Solution114 {
    public void flatten(TreeNode root) {

        if (root == null) {
            return;
        }
        Deque<TreeNode> queue = new ArrayDeque<>();
        TreeNode prev = null;
        queue.push(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (prev != null) {
                prev.left = null;
                prev.right = node;
            }

            if (node.right != null)
                queue.push(node.right);
            if (node.left != null)
                queue.push(node.left);

            prev = node;
        }
    }

}
