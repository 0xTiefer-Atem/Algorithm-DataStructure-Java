package org.wq.leetcode.day14;

import com.sun.javafx.collections.MappingChange;
import org.wq.leetcode.common.TreeNode;

import java.util.*;

public class Solution14 {

    public void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    //72. 编辑距离
    public int minDistance(String word1, String word2) {

        int length1 = word1.length();
        int length2 = word2.length();

        int[][] dp = new int[length1 + 1][length2 + 1];
        for (int i = 1; i <= length1; i++) {
            dp[i][0] = dp[i - 1][0] + 1;
        }

        for (int i = 1; i <= length2; i++) {
            dp[0][i] = dp[0][i - 1] + 1;
        }

        for (int i = 1; i <= length1; i++) {
            for (int j = 1; j <= length2; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j - 1], dp[i][j - 1]), dp[i - 1][j]) + 1;

                }
            }
        }

        return dp[length1][length2];
    }

    // 75. 颜色分类
    public void sortColors1(int[] nums) {
        Arrays.sort(nums);
    }

    // 75. 颜色分类
    // 类似快排分区操作
    public void sortColors(int[] nums) {
        int l = -1;
        int start = 0;
        int end = nums.length;
        while (start < end) {
            if (nums[start] > 1) {
                swap(nums, start, --end);
            } else if (nums[start] < 1) {
                swap(nums, start++, ++l);
            } else {
                start++;
            }
        }
    }

    // 76. 最小覆盖子串
    public String minWindow(String s, String t) {
        Map<Character, Integer> ht = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            ht.put(t.charAt(i), ht.getOrDefault(t.charAt(i), 0) + 1);
        }

        Map<Character, Integer> hsMap = new HashMap<>();
        int left = 0;
        int right = 0;
        int start = 0;
        int valid = 0;
        int length = Integer.MAX_VALUE;

        while (right < s.length()) {
            Character curr = s.charAt(right);
            right++;

            if (ht.containsKey(curr)) {
                hsMap.put(curr, hsMap.getOrDefault(curr, 0) + 1);
                if (Objects.equals(hsMap.get(curr), ht.get(curr))) {
                    valid++;
                }
            }

            while (valid == ht.size()) {
                if (length > right - left) {
                    length = right - left;
                    start = left;
                }
                Character lBounder = s.charAt(left);
                if (ht.containsKey(lBounder)) {
                    if (Objects.equals(hsMap.get(lBounder), ht.get(lBounder))) {
                        valid--;
                    }
                    hsMap.put(lBounder, hsMap.getOrDefault(lBounder, 0) - 1);
                }
                left++;
            }
        }
        return length == Integer.MAX_VALUE ? "" : s.substring(start, start + length);
    }

    // 78. 子集
    List<List<Integer>> res_78 = new LinkedList<>();

    public List<List<Integer>> subsets(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }

        process1(nums, new LinkedList<>(), 0);

        return res_78;
    }

    private void process1(int[] nums, LinkedList<Integer> list, int index) {

        res_78.add(new LinkedList<>(list));


        for (int i = index; i < nums.length; i++) {
            list.add(nums[i]);
            process1(nums, list, i + 1);
            list.removeLast();
        }

    }

    // 79. 单词搜索
    char[][] g;
    int r;
    int c;
    boolean[][] visited;
    String word;
    int[] dx = {-1, 0, 1, 0};
    int[] dy = {0, 1, 0, -1};

    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return false;
        }

        g = board;
        r = board.length;
        c = board[0].length;
        this.word = word;

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (word.charAt(0) == g[i][j]) {
                    visited = new boolean[r][c];
                    //可能是字符串开头
                    if (dfs(i, j, new StringBuffer())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean dfs(int i, int j, StringBuffer sb) {
        if (sb.length() == word.length()) {
            return false;
        }

        sb.append(g[i][j]);
        visited[i][j] = true;

        if (sb.toString().equals(word)) {
            return true;
        }

        for (int k = 0; k < 4; k++) {
            int a = i + dx[k];
            int b = j + dy[k];
            if (a < 0 || a >= r || b < 0 || b >= c || visited[a][b]) {
                continue;
            }
            if (dfs(a, b, sb)) {
                return true;
            }
        }

        sb.deleteCharAt(sb.length() - 1);
        visited[i][j] = false;
        return false;
    }

    //94. 二叉树的中序遍历

    ArrayList<Integer> res_94 = new ArrayList<>();

    public List<Integer> inorderTraversal(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }

        process2(root);
        return res_94;
    }

    private void process2(TreeNode root) {
        if (root == null) {
            return;
        }
        process2(root.left);
        res_94.add(root.val);
        process2(root.right);
    }

    // 96. 不同的二叉搜索树
    Map<Integer, Integer> countMap = new HashMap<>();

    public int numTrees(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }

        if (countMap.containsKey(n)) {
            return countMap.get(n);
        }

        int count = 0;
        for (int i = 1; i <= n; i++) {
            int left = numTrees(i - 1);
            int right = numTrees(n - i);
            count += left * right;

        }
        countMap.put(n, count);
        return count;
    }

    // 98. 验证二叉搜索树
    long preValue = Long.MIN_VALUE;

    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }

        if (!isValidBST(root.left)) {
            return false;
        }

        if (root.val <= preValue) {
            return false;
        }

        preValue = root.val;

        return isValidBST(root.right);
    }

    // 101. 对称二叉树
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }

        return isSame(root.left, root.right);
    }

    private boolean isSame(TreeNode left, TreeNode right) {

        if (left == null && right == null) {
            return true;
        }

        if (left == null || right == null) {
            return false;
        }
        return left.val == right.val && isSame(left.right, right.left) && isSame(left.left, right.right);
    }

    // 102. 二叉树的层序遍历
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        List<List<Integer>> res = new ArrayList<>();

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                list.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }

                if (node.right != null) {
                    queue.offer(node.right);
                }

            }
            res.add(list);
        }

        return res;
    }

    //104. 二叉树的最大深度
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    // 105. 从前序与中序遍历序列构造二叉树
    Map<Integer, Integer> inorderMap = new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }
        return process3(preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1);
    }

    private TreeNode process3(int[] preorder, int[] inorder, int pl, int pr, int il, int ir) {
        if (pl > pr || il > ir) {
            return null;
        }

        int rootVal = preorder[pl];

        TreeNode root = new TreeNode(rootVal);

        int inorderIndex = inorderMap.get(rootVal);

        int num = inorderIndex - il;
        root.left = process3(preorder, inorder, pl + 1, pl + num, il, inorderIndex - 1);
        root.right = process3(preorder, inorder, pl + 1 + num, pr, inorderIndex + 1, ir);

        return root;
    }


    public static void main(String[] args) {
        Solution14 s14 = new Solution14();

//        System.out.println(s14.minDistance("horse", "ros"));


//        int[] nums = {2, 0, 2, 1, 1, 0};
//        s14.sortColors(nums);
//        System.out.println(Arrays.toString(nums));

        int[] nums = {1, 2, 3};
        System.out.println(s14.subsets(nums));
    }
}
