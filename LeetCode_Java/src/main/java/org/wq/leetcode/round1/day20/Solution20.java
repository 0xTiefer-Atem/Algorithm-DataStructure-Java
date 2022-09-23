package org.wq.leetcode.round1.day20;

import org.wq.leetcode.common.ListNode;
import org.wq.leetcode.common.Node;
import org.wq.leetcode.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Solution20 {
    // 链表反转
    public ListNode reserveList(ListNode head) {
        ListNode curr = head;
        ListNode pre = null;

        while (curr != null) {
            ListNode temp = curr.next;
            curr.next = pre;
            pre = curr;
            curr = temp;
        }

        return pre;
    }

    // 104 二叉树的最大深度
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);

        return Math.max(left, right) + 1;
    }

    // 144 二叉树前序遍历

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        res.add(root.val);
        res.addAll(preorderTraversal(root.left));
        res.addAll(preorderTraversal(root.right));

        return res;
    }

    // 543 二叉树直径

    int maxDiameter = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        maxDiameterFun(root);
        return maxDiameter;
    }

    private int maxDiameterFun(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = maxDiameterFun(root.left);
        int right = maxDiameterFun(root.right);

        maxDiameter = Math.max(maxDiameter, left + right);
        return Math.max(left, right) + 1;
    }

    // 226 翻转二叉树
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);

        root.left = right;
        root.right = left;

        return root;
    }

    // 116 填充每个节点的下一个右侧节点指针

    public Node connect(Node root) {
        if (root == null) return null;
        traverse(root.left, root.right);
        return root;
    }

    private void traverse(Node left, Node right) {
        if (left == null || right == null) {
            return;
        }

        left.next = right;
        traverse(left.left, left.right);
        traverse(right.left, right.right);
        traverse(left.right, right.left);
    }
}
