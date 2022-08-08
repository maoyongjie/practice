package com.practice.datastructure.binarytree;

/**
 * @author MaoYongjie
 * @date 2022/8/8 16:04
 * @Description:
 */
//给定一个二叉树
//
//
//struct Node {
//  int val;
//  Node *left;
//  Node *right;
//  Node *next;
//}
//
// 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
//
// 初始状态下，所有 next 指针都被设置为 NULL。
//
//
//
// 进阶：
//
//
// 你只能使用常量级额外空间。
// 使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。
//
//
//
//
// 示例：
//
//
//
//
//输入：root = [1,2,3,4,5,null,7]
//输出：[1,#,2,3,#,4,5,7,#]
//解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，如图 B 所示。序列化输出按层序遍历顺序（由 next 指
//针连接），'#' 表示每层的末尾。
//
//
//
// 提示：
//
//
// 树中的节点数小于 6000
// -100 <= node.val <= 100
//
//
//
//
//
//
// Related Topics 树 深度优先搜索 广度优先搜索 链表 二叉树
// 👍 620 👎 0
public class Solution117 {
    public static void main(String[] args) {
        Node root = new Node(2);
        root.left = new Node(1);
        root.right = new Node(3);
        root.left.left = new Node(0);
        root.left.right = new Node(7);
        root.right.left = new Node(9);
        root.right.right = new Node(1);
        root.left.left.left = new Node(2);
        root.left.right.left = new Node(1);
        root.left.right.right = new Node(-1);
        root.right.right.left = new Node(8);
        root.right.right.right = new Node(8);
        Solution117 solution117 = new Solution117();
        solution117.connect(root);
    }

    /**
     * 不能使用递归，因为右边子树有的还没执行
     * @param root
     * @return
     */
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        root.next = null;
        solution2(root);
        return root;

    }

    private void solution2(Node root) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            return;
        }

        if(root.val==7){
            System.out.println(11);
        }

        if (root.left != null && root.right != null) {
            root.left.next = root.right;
        } else if (root.left != null) {
            Node temp = root.next;
            root.left.next = null;
            while (temp != null) {
                if (temp.left != null) {
                    root.left.next = temp.left;
                    break;
                } else if (temp.right != null) {
                    root.left.next = temp.right;
                    break;
                }
                temp = temp.next;
            }
        }

        if (root.right != null) {
            Node temp = root.next;
            root.right.next = null;
            while (temp != null) {
                if (temp.left != null) {
                    root.right.next = temp.left;
                    break;
                } else if (temp.right != null) {
                    root.right.next = temp.right;
                    break;
                }
                temp = temp.next;
            }

        }
        solution2(root.left);
        solution2(root.right);
    }
}
