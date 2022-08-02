package com.practice.datastructure.binarytree;

/**
 * @author MaoYongjie
 * @date 2022/8/1 19:23
 * @Description:
 */
//给定一个单链表的头节点 head ，其中的元素 按升序排序 ，将其转换为高度平衡的二叉搜索树。
//
// 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差不超过 1。
//
//
//
// 示例 1:
//
//
//
//
//输入: head = [-10,-3,0,5,9]
//输出: [0,-3,9,-10,null,5]
//解释: 一个可能的答案是[0，-3,9，-10,null,5]，它表示所示的高度平衡的二叉搜索树。
//
//
// 示例 2:
//
//
//输入: head = []
//输出: []
//
//
//
//
// 提示:
//
//
// head 中的节点数在[0, 2 * 104] 范围内
// -105 <= Node.val <= 105
//
// Related Topics 树 二叉搜索树 链表 分治 二叉树
// 👍 735 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
public class Solution109 {
    public TreeNode sortedListToBST(ListNode head) {
        return build(head,null);
    }

    public TreeNode build(ListNode left, ListNode right) {
        if(left==right){
            return null;
        }
        ListNode mid = getMid(left, right);
        TreeNode node = new TreeNode(mid.val);
        node.left = build(left,mid);
        node.right = build(mid.next,right);
        return node;
    }

    private ListNode getMid(ListNode left, ListNode right){
        ListNode fast = left;
        ListNode slow = left;
        while (fast != right && fast.next != right) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
