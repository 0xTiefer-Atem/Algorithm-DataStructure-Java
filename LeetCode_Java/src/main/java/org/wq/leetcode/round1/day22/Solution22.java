package org.wq.leetcode.round1.day22;

import org.wq.leetcode.common.TreeNode;

public class Solution22 {


    // 538. 把二叉搜索树转换为累加树
    int sum = 0;

    public TreeNode convertBST(TreeNode root) {
        traverse1(root);
        return root;
    }

    private void traverse1(TreeNode root) {
        if (root == null) {
            return;
        }

        traverse1(root.right);

        // 中序遍历，操作
        sum = sum + root.val;
        root.val = sum;


        traverse1(root.left);
    }

    // 98. 验证二叉搜索树
    public boolean isValidBST(TreeNode root) {
        return traverse2(root, null, null);
    }

    private boolean traverse2(TreeNode root, TreeNode min, TreeNode max) {
        if (root == null) {
            return true;
        }

        if (min != null && root.val <= min.val) {
            return false;
        }

        if (max != null && root.val >= max.val) {
            return false;
        }

        return traverse2(root.left, min, root) && traverse2(root.right, root, max);
    }

    //700. 二叉搜索树中的搜索
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) {
            return null;
        }

        if (root.val > val) {
            return searchBST(root.left, val);
        }

        if (root.val < val) {
            return searchBST(root.right, val);
        }
        return root;
    }


    // 701. 二叉搜索树中的插入操作
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }

        if (root.val > val) {
            root.left = insertIntoBST(root.left, val);
        }

        if (root.val < val) {
            root.right = insertIntoBST(root.right, val);
        }

        return root;
    }

    // 222. 完全二叉树的节点个数
    public int countNodes(TreeNode root) {
        int tl = 0;
        int tr = 0;
        TreeNode l = root;
        TreeNode r = root;

        while (l != null) {
            l = l.left;
            tl++;
        }

        while (r != null) {
            r = r.right;
            tr++;
        }

        if (tl == tr) {
            return (int) Math.pow(2, tl) - 1;
        }

        return 1 + countNodes(root.left) + countNodes(root.right);
    }
}
