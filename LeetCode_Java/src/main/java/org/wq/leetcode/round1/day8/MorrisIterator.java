package org.wq.leetcode.round1.day8;

import org.wq.leetcode.common.TreeNode;

// 二叉树 Morris遍历
// 把空间复杂度优化到O(1)的二叉树遍历算法。
public class MorrisIterator {

    //Morris遍历的算法思想
    //假设当前节点为cur，并且开始时赋值为根节点root。
    //判断cur节点是否为空
    //如果不为空
    //1）如果cur没有左孩子，cur向右更新，即（cur = cur.right）
    //2）如果cur有左孩子，则从左子树找到最右侧节点pre
    //      a.如果pre的右孩子为空，则将右孩子指向cur。pre.right = cur
    //      b.如果pre的右孩子为cur，则将其指向为空。pre.right = null。（还原树结构）
    //cur为空时，停止遍历

    public static void morris(TreeNode root) {

        if (root == null) {
            return;
        }

        TreeNode curr = root;

        while (curr != null) {

            TreeNode mostRight = curr.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != curr) {
                    mostRight = mostRight.right;
                }

                if (mostRight.right == null) {
                    mostRight.right = curr;
                    curr = curr.left;
                    continue;
                } else {
                    mostRight.right = null;
                }

            }
            curr = curr.right;
        }
    }
}
