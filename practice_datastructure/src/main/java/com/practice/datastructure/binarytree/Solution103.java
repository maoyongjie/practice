package com.practice.datastructure.binarytree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author MaoYongjie
 * @date 2022/7/25 16:05
 * @Description:
 */
//给你二叉树的根节点 root ，返回其节点值的 锯齿形层序遍历 。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
//
//
//
// 示例 1：
//
//
//输入：root = [3,9,20,null,null,15,7]
//输出：[[3],[20,9],[15,7]]
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
// -100 <= Node.val <= 100
//
// Related Topics 树 广度优先搜索 二叉树
// 👍 672 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
public class Solution103 {
    //解法二：双端队列
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2;
        stack1.push(root);
        int depth = 0;

        do {
            List<Integer> list = new ArrayList<>();
            stack2 = new Stack<>();
            while (!stack1.isEmpty()) {
                TreeNode node = stack1.pop();
                list.add(node.val);
                if (depth % 2 == 0) {
                    if (node.left != null) {
                        stack2.push(node.left);
                    }
                    if (node.right != null) {
                        stack2.push(node.right);
                    }

                } else {
                    if (node.right != null) {
                        stack2.push(node.right);
                    }
                    if (node.left != null) {
                        stack2.push(node.left);
                    }
                }
            }
            depth++;
            stack1 = stack2;
            res.add(list);
        } while (!stack2.isEmpty());
        return res;
    }


}
