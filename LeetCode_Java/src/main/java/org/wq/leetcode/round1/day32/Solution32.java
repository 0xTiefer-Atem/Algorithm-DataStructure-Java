package org.wq.leetcode.round1.day32;

import org.wq.leetcode.common.ListNode;
import org.wq.leetcode.common.TreeNode;

import java.util.*;

public class Solution32 {

    // 剑指 Offer 03. 数组中重复的数字
    public int findRepeatNumber(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                map.put(nums[i], map.get(nums[i]) + 1);
                if (map.get(nums[i]) == 2) {
                    return nums[i];
                }

            }
            map.put(nums[i], 1);
        }

        return -1;
    }

    // 剑指 Offer 04. 二维数组中的查找
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        int rows = matrix.length;
        int cols = matrix[0].length;


        int row = rows - 1;
        int col = 0;

        while (row >= 0 && col < cols) {
            if (target > matrix[row][col]) {
                col++;
            } else if (target < matrix[row][col]) {
                row--;
            } else {
                return true;
            }
        }

        return false;
    }

    // 剑指 Offer 05. 替换空格
    public String replaceSpace(String s) {
        return s.replace(" ", "%20");
    }

    // 剑指 Offer 06. 从尾到头打印链表
    List<Integer> res = new ArrayList<>();

    public int[] reversePrint(ListNode head) {
        if (head == null) {
            return new int[0];
        }
        process1(head);
        int[] resArr = new int[res.size()];
        for (int i = 0; i < resArr.length; i++) {
            resArr[i] = res.get(i);
        }
        return resArr;
    }

    private void process1(ListNode head) {
        if (head == null) {
            return;
        }
        process1(head.next);
        res.add(head.val);
    }

    // 剑指 Offer 07. 重建二叉树
    HashMap<Integer, Integer> nodeIndex = new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        for (int i = 0; i < inorder.length; i++) {
            nodeIndex.put(inorder[i], i);
        }
        return build(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    private TreeNode build(int[] preorder, int pl, int pr, int[] inorder, int il, int ir) {
        if (pl > pr) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[pl]);

        // 获取该值在中序遍历的位置
        int index = nodeIndex.get(preorder[pl]);

        int length = index - il;

        root.left = build(preorder, pl + 1, pl + length, inorder, il, il + length);
        root.right = build(preorder, pl + length + 1, pr, inorder, il + length + 1, ir);

        return root;
    }

    // 剑指 Offer 09. 用两个栈实现队列
    class CQueue {
        private Stack<Integer> s1 = new Stack<Integer>();
        private Stack<Integer> s2 = new Stack<Integer>();

        public CQueue() {

        }

        public void appendTail(int value) {
            s1.push(value);

        }

        public int deleteHead() {

            if (!s2.isEmpty()) {
                return s2.pop();
            }

            while (!s1.isEmpty()) {
                s2.push(s1.pop());
            }

            if (s2.isEmpty()) {
                return -1;
            }

            return s2.pop();
        }
    }

    // 剑指 Offer 10- I. 斐波那契数列
    public int fib(int n) {
        int[] dp = new int[100 + 1];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % 1000000007;
        }

        return dp[n];
    }

    // 剑指 Offer 10- II. 青蛙跳台阶问题
    public int numWays(int n) {
        if (n <= 1) return 1;
        if (n == 2) return 2;
        if (n == 3) return 3;

        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 3;
        for (int i = 4; i <= n; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % 1000000007;
        }

        return dp[n];
    }

    // 剑指 Offer 11. 旋转数组的最小数字
    public int minArray(int[] numbers) {
        Arrays.sort(numbers);
        return numbers[0];
    }

    // 剑指 Offer 12. 矩阵中的路径
    // 深度优先搜索
    boolean[][] used12;

    public boolean exist(char[][] board, String word) {

        if (board == null || board.length == 0 || board[0].length == 0) {
            return false;
        }

        int rows = board.length;
        int cols = board[0].length;
        used12 = new boolean[rows][cols];
        char[] wordArr = word.toCharArray();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (deepSearch(board, wordArr, i, j, 0)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean deepSearch(char[][] board, char[] wordArr, int row, int col, int index) {
        if (row < 0 || row >= board.length) {
            return false;
        }

        if (col < 0 || col > board[0].length) {
            return false;
        }

        if (used12[row][col]) {
            return false;
        }
        if (board[row][col] != wordArr[index]) {
            return false;
        }

        if (index == wordArr.length - 1) {
            return true;
        }
        used12[row][col] = true;

        boolean res = deepSearch(board, wordArr, row, col + 1, index + 1)
                || deepSearch(board, wordArr, row + 1, col, index + 1)
                || deepSearch(board, wordArr, row, col - 1, index + 1)
                || deepSearch(board, wordArr, row - 1, col, index + 1);

        used12[row][col] = false;
        return res;
    }

    public static void main(String[] args) {
        Solution32 s32 = new Solution32();

//        int[] nums = {2, 3, 1, 0, 2, 5, 3};
//        System.out.println(s32.findRepeatNumber(nums));

        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};

//        TreeNode root = s32.buildTree(preorder, inorder);
//        System.out.println(root);

//        s32.fib(48);
        char[][] board = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        String word = "ABCCED";
        System.out.println(s32.exist(board, word));
    }
}
