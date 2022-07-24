package org.wq.leetcode.day21;

import org.wq.leetcode.common.ListNode;
import org.wq.leetcode.common.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Solution21 {

    // 654. 最大二叉树
    // 使用二叉树前序遍历，先构造根节点，在进行左右递归
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return traverse1(nums, 0, nums.length - 1);
    }

    public TreeNode traverse1(int[] nums, int l, int r) {
        if (l > r) {
            return null;
        }

        int index = -1;
        int maxValue = Integer.MIN_VALUE;

        for (int i = l; i <= r; i++) {
            if (nums[i] > maxValue) {
                maxValue = nums[i];
                index = i;
            }
        }

        TreeNode root = new TreeNode(maxValue);
        root.left = traverse1(nums, l, index - 1);
        root.right = traverse1(nums, index + 1, r);
        return root;
    }


    // 剑指 Offer 06. 从尾到头打印链表
    // 使用递归的后序遍历方式，先递归遍历链表，等离开节点是在进行操作
    ArrayList<Integer> res = new ArrayList<>();

    public int[] reversePrint(ListNode head) {
        traverse2(head);
        int[] intArr = new int[res.size()];
        for (int i = 0; i < intArr.length; i++) {
            intArr[i] = res.get(i);
        }

        return intArr;
    }

    public void traverse2(ListNode node) {
        if (node == null) {
            return;
        }

        traverse2(node.next);
        res.add(node.val);
    }

    // 105. 从前序与中序遍历序列构造二叉树
//    HashMap<Integer, Integer> indexMap1 = new HashMap<>();
//
//    public TreeNode buildTree(int[] preorder, int[] inorder) {
//        for (int i = 0; i < inorder.length; i++) {
//            indexMap1.put(inorder[i], i);
//        }
//
//        return build(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
//    }
//
//    /**
//     * build 函数的定义：
//     * 若前序遍历数组为 preorder[preStart..preEnd]，
//     * 中序遍历数组为 inorder[inStart..inEnd]，
//     * 构造二叉树，返回该二叉树的根节点
//     *
//     * @param preorder
//     * @param peStart
//     * @param peEnd
//     * @param inorder
//     * @param inStart
//     * @param inEnd
//     * @return
//     */
//    public TreeNode build(int[] preorder, int peStart, int peEnd, int[] inorder, int inStart, int inEnd) {
//        if (peStart > peEnd) {
//            return null;
//        }
//
//        // 建立当前节点
//        TreeNode root = new TreeNode(preorder[peStart]);
//
//        int inOrderIndex = indexMap1.get(preorder[peStart]);
//
//        // 计算出当前左子树长度
//        int length = inOrderIndex - inStart;
//        root.left = build(preorder, peStart + 1, peStart + length, inorder, inStart, inOrderIndex - 1);
//        root.right = build(preorder, peStart + length + 1, peEnd, inorder, inOrderIndex + 1, inEnd);
//
//        return root;
//    }

    // 106. 从中序与后序遍历序列构造二叉树
    HashMap<Integer, Integer> indexMap2 = new HashMap<>();

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        for (int i = 0; i < inorder.length; i++) {
            indexMap2.put(inorder[i], i);
        }

        return build(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
    }


    public TreeNode build(int[] inorder, int inStart, int inEnd, int[] postorder, int poStart, int poEnd) {
        if (poStart > poEnd) {
            return null;
        }

        TreeNode root = new TreeNode(postorder[poEnd]);
        int inorderIndex = indexMap2.get(postorder[poEnd]);

        int leftSize = inorderIndex - inStart;

        root.left = build(inorder, inStart, inorderIndex - 1, postorder, poStart, poStart + leftSize - 1);
        root.right = build(inorder, inorderIndex + 1, inEnd, postorder, poStart + leftSize, poEnd - 1);

        return root;
    }


    //889. 根据前序和后序遍历构造二叉树
    HashMap<Integer, Integer> indexMap3 = new HashMap<>();

    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        for (int i = 0; i < postorder.length; i++) {
            indexMap3.put(postorder[i], i);
        }

        return buildPrePost(preorder, 0, preorder.length - 1, postorder, 0, postorder.length - 1);
    }

    public TreeNode buildPrePost(int[] preorder, int preStart, int preEnd, int[] postorder, int poStart, int poEnd) {
        if (preStart > preEnd) {
            return null;
        }

        if (preStart == preEnd) {
            return new TreeNode(preorder[preStart]);
        }
        TreeNode root = new TreeNode(preorder[preStart]);

        int postIndex = indexMap3.get(preorder[preStart + 1]);
        int leftLength = postIndex - poStart + 1;

        root.left = buildPrePost(preorder, preStart + 1, preStart + leftLength, postorder, poStart, postIndex);
        root.right = buildPrePost(preorder, preStart + leftLength + 1, preEnd, postorder, postIndex + 1, poEnd - 1);

        return root;

    }

    // 652. 寻找重复的子树
    // 后续遍历方式
    HashMap<String, Integer> memo = new HashMap<>();
    List<TreeNode> res_652 = new ArrayList<>();

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {

        traverse1(root);
        return res_652;
    }

    private String traverse1(TreeNode root) {
        if (root == null) {
            return "#";
        }

        String left = traverse1(root.left);
        String right = traverse1(root.right);
        String subTree = left + "," + right + "," + root.val;

        Integer fre = memo.getOrDefault(subTree, 0);
        if (fre == 1) {
            res_652.add(root);
        }

        memo.put(subTree, fre + 1);
        return subTree;
    }

    // 230. 二叉搜索树中第K小的元素
    int rank = 0;
    int res_230 = 0;

    public int kthSmallest(TreeNode root, int k) {

        traverse3(root, k);

        return res_230;
    }

    private void traverse3(TreeNode root, int k) {
        if (root == null) {
            return;
        }

        traverse3(root.left, k);

        rank++;
        if (rank == k) {
            res_230 = root.val;
            return;
        }


        traverse3(root.right, k);
    }


    // 538. 把二叉搜索树转换为累加树
    public TreeNode convertBST(TreeNode root) {

    }
}
