package org.wq.leetcode.round1.day7;

import org.wq.leetcode.common.TreeNode;

import java.util.*;

public class BinaryTree {
    // 非递归遍历二叉树宽度
    // 使用哈希表
    public static int treeWidth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Queue<TreeNode> queue = new LinkedList<>();

        // 记录每个节点所在层数
        HashMap<TreeNode, Integer> levelMap = new HashMap<>();
        levelMap.put(root, 1);

        // 记录当前层数
        int currLevel = 1;

        // 记录每层几点个数
        int levelNodes = 0;

        int max = Integer.MIN_VALUE;
        queue.add(root);

        // 层序遍历
        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();

            int level = levelMap.get(curr);

            if (level == currLevel) {
                levelNodes++;
            } else {
                max = Math.max(levelNodes, max);
                level++;
                levelNodes = 1;
            }

            if (curr.left != null) {
                levelMap.put(curr.left, currLevel + 1);
                queue.add(root.left);
            }


            if (curr.right != null) {
                levelMap.put(curr.right, currLevel + 1);
                queue.add(curr.right);
            }
        }
        return max;

    }


    public static int preValue = Integer.MIN_VALUE;

    // 判断二叉树为搜索二叉树 递归
    public static boolean isBST(TreeNode root) {
        if (root == null) {
            return true;
        }

        boolean isLeftBST = isBST(root.left);

        if (!isLeftBST) {
            return false;
        }

        if (root.val < preValue) {
            return false;
        } else {
            preValue = root.val;
        }

        return isBST(root.right);
    }

    // 判断二叉树为搜索二叉树 非递归
    public static boolean checkBST(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        int preValue = Integer.MIN_VALUE;
        while (!stack.empty() || root != null) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                root = stack.pop();
                if (root.val <= preValue) {
                    return false;
                } else {
                    preValue = root.val;
                }
                root = root.right;
            }
        }
        return true;
    }

    // 判断二叉树是否为完全二叉树
    public static boolean CBST(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        TreeNode l = null;
        TreeNode r = null;

        // 是否遇到过节点有左孩子但没有右孩子
        boolean leaf = false;

        while (!queue.isEmpty()) {
            root = queue.poll();
            l = root.left;
            r = root.right;
            if ((leaf && (l != null || r != null)) || (l == null && r != null)) {
                return false;
            }

            if (l != null) {
                queue.add(l);
            }

            if (r != null) {
                queue.add(r);
            }

            if (l == null || r == null) {
                leaf = true;
            }
        }
        return true;

    }

    // 判断一个棵树的深度
    public static int treeDeep(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return Math.max(treeDeep(root.left), treeDeep(root.right)) + 1;
    }


    class ReturnData {
        public boolean isBalance;
        public int max;
        public int min;

        public ReturnData(boolean is, int max, int min) {
            this.isBalance = is;
            this.max = max;
            this.min = min;
        }
    }

    // 判断一个二叉树是否为搜索二叉树
    // 树形dp
    public ReturnData isValidBST(TreeNode root) {
        if (root == null) {
            return null;
        }

        ReturnData leftData = isValidBST(root.left);
        ReturnData rightData = isValidBST(root.left);

        boolean balance = true;
        int min = root.val;
        int max = root.val;

        if (leftData != null) {
            min = Math.min(root.val, leftData.min);
            max = Math.max(root.val, leftData.max);
        }

        if (rightData != null) {
            min = Math.min(root.val, rightData.min);
            max = Math.max(root.val, rightData.max);
        }

        if (leftData != null && (!leftData.isBalance || root.val <= leftData.max)) {
            balance = false;
        }

        if (rightData != null && (!rightData.isBalance || root.val >= rightData.max)) {
            balance = false;
        }

        return new ReturnData(balance, max, min);
    }

    // 最低公共父节点
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left != null && right != null) {
            return root;
        }

        return left != null ? left : right;

    }


    // 寻找节点的后继节点
    // 1、x节点有右树，则x后继节点为x右树的最节点
    // 2、x没有右树，x所在的左子树父节点为后继节点
    // 3、x没有右子树，且没有左子树父节点，则为空

    public TreeNode getSuccessorNode(TreeNode root) {
        if (root == null) {
            return root;
        }

        if (root.right != null) {
            return getLeftMost(root.right);
        } else {
            TreeNode parentNode = root.parent;

            while (parentNode != null && parentNode.left != root) {
                root = parentNode;
                parentNode = root.parent;
            }

            return parentNode;
        }

    }

    // 获取最左节点
    public TreeNode getLeftMost(TreeNode root) {
        if (root == null) {
            return root;
        }

        while (root.left != null) {
            root = root.left;
        }

        return root;
    }


    // 序列化二叉树
    public static String serializeTree(TreeNode root) {
        if (root == null) {
            return "#_";
        }

        String res = root.val + "_";
        res += serializeTree(root.left);
        res += serializeTree(root.right);
        return res;
    }

    // 反序列化二叉树
    public static TreeNode deSerializeTree(String treeStr) {
        String[] treeArrays = treeStr.split("_");
        Queue<String> srtQueue = new LinkedList<>();
        for (String treeS : treeArrays) {
            srtQueue.add(treeS);
        }

        TreeNode root = deSerializeBT(srtQueue);

        return root;

    }

    private static TreeNode deSerializeBT(Queue<String> srtQueue) {
        String valStr = srtQueue.poll();
        if ("#".equals(valStr)){
            return null;
        }
        TreeNode root = new TreeNode(Integer.valueOf(valStr));

        root.left = deSerializeBT(srtQueue);
        root.right = deSerializeBT(srtQueue);

        return root;


    }

    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(1);
        TreeNode n3 = new TreeNode(1);

        n1.right = n2;
        n2.left = n3;

//        System.out.println(serializeTree(n1));

        String s = "1_#_1_1_#_#_#_";

        TreeNode treeNode = deSerializeTree(s);
        System.out.println(treeNode);
    }


}
