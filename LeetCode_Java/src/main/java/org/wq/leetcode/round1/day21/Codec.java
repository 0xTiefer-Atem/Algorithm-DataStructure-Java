package org.wq.leetcode.round1.day21;

import org.wq.leetcode.common.TreeNode;

import java.util.Arrays;
import java.util.LinkedList;


// 297. 二叉树的序列化与反序列化
public class Codec {
    private static final String NULL = "null";
    private static final String splitSymbol = ",";

    // Encodes a tree to a single string.
    StringBuilder string = new StringBuilder();

    public String serialize(TreeNode root) {
        traverse(root);
        return string.toString();
    }

    private void traverse(TreeNode root) {
        if (root == null) {
            string.append(NULL).append(splitSymbol);
            return;
        }
        string.append(root.val).append(splitSymbol);
        traverse(root.left);
        traverse(root.right);

    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] nodesStr = data.split(",");
        LinkedList<String> nodes = new LinkedList<>(Arrays.asList(nodesStr));
        return buildTree(nodes);
    }

    private TreeNode buildTree(LinkedList<String> nodes) {
        if (nodes.isEmpty()) {
            return null;
        }

        String valStr = nodes.removeFirst();
        if (NULL.equals(valStr)) {
            return null;
        }

        TreeNode root = new TreeNode(Integer.parseInt(valStr));
        root.left = buildTree(nodes);
        root.right = buildTree(nodes);

        return root;

    }
}
