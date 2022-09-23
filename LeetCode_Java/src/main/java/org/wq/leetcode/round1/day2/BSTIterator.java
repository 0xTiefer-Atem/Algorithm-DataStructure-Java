package org.wq.leetcode.round1.day2;

import org.wq.leetcode.common.TreeNode;

import java.util.ArrayList;
import java.util.List;


/**
 * 173. 二叉搜索树迭代器
 * 实现一个二叉搜索树迭代器。你将使用二叉搜索树的根节点初始化迭代器。
 * <p>
 * 调用 next() 将返回二叉搜索树中的下一个最小的数。
 * <p>
 * 示例：
 * <p>
 * BSTIterator iterator = new BSTIterator(root);
 * iterator.next();    // 返回 3
 * iterator.next();    // 返回 7
 * iterator.hasNext(); // 返回 true
 * iterator.next();    // 返回 9
 * iterator.hasNext(); // 返回 true
 * iterator.next();    // 返回 15
 * iterator.hasNext(); // 返回 true
 * iterator.next();    // 返回 20
 * iterator.hasNext(); // 返回 false
 */
public class BSTIterator {
    private List<TreeNode> nodeList = new ArrayList<TreeNode>();
    private int index = 0;

    public BSTIterator(TreeNode root) {
        //中序遍历存进nodeList，然后遍历list
        nodeList = inorderTraversal(root, nodeList);
    }

    public List<TreeNode> inorderTraversal(TreeNode root, List<TreeNode> nodeList) {
        if (root != null) {
            inorderTraversal(root.left, nodeList);
            nodeList.add(root);
            inorderTraversal(root.right, nodeList);
        }
        return nodeList;
    }

    /**
     * @return the next smallest number
     */
    public int next() {
        TreeNode node = nodeList.get(index);
        index++;
        return node.val;
    }

    /**
     * @return whether we have a next smallest number
     */
    public boolean hasNext() {
        if (index < nodeList.size()) {
            return true;
        }
        return false;
    }
}

