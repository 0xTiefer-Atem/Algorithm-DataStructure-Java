package org.wq.leetcode.day27;

import org.wq.leetcode.common.TreeNode;

import java.util.*;

public class Solution27 {
    // 111. 二叉树的最小深度
    // BFS算法
    public int minDepth(TreeNode root) {

        if (root == null) {
            return 0;
        }

        int step = 1;

        Queue<TreeNode> q = new LinkedList<>();

        q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                if (node != null) {
                    if (node.left == null && node.right == null) {
                        return step;
                    }

                    if (node.left != null) {
                        q.offer(node.left);
                    }

                    if (node.right != null) {
                        q.offer(node.right);
                    }
                }
            }
            step++;
        }
        return step;
    }

    // 127. 单词接龙
    // BFS
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        HashMap<Integer, Set<Character>> charMap = new HashMap<>();
        for (String word : wordList) {
            char[] wordChar = word.toCharArray();
            for (int i = 0; i < wordChar.length; i++) {
                if (charMap.get(i) == null) {
                    Set<Character> s = new HashSet<>();
                    s.add(wordChar[i]);
                    charMap.put(i, s);
                } else {
                    charMap.get(i).add(wordChar[i]);
                }
            }
        }

        Set<String> wordSet = new HashSet<>();
        for (String word : wordList) {
            wordSet.add(word);
        }


        // 遍历队列
        Queue<String> q = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        int step = 1;

        q.offer(beginWord);
        visited.add(beginWord);
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                String tempWord = q.poll();
                if (tempWord == null) {
                    continue;
                }
                if (endWord.equals(tempWord)) {
                    return step;
                }

                for (int j = 0; j < tempWord.length(); j++) {
                    Set<Character> cSet = charMap.get(j);
                    for (Character c : cSet) {
                        String nextWord = changeChar(tempWord, j, c);
                        if (!wordSet.contains(nextWord)) {
                            continue;
                        }
                        if (visited.contains(nextWord)) {
                            continue;
                        }

                        q.offer(nextWord);
                        visited.add(nextWord);
                    }
                }
            }

            step++;
        }

        return 0;

    }

    private String changeChar(String tempWord, int j, Character c) {
        char[] charArr = tempWord.toCharArray();
        charArr[j] = c;
        return new String(charArr);
    }

    // 752. 打开转盘锁
    public int openLock(String[] deadends, String target) {
        HashSet<String> deadendSet = new HashSet<>(Arrays.asList(deadends));

        String original = "0000";
        Queue<String> q = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        q.offer(original);
        visited.add(original);
        int step = 0;

        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                String tempWord = q.poll();
                if (deadendSet.contains(tempWord)) {
                    continue;
                }

                if (target.equals(tempWord)) {
                    return step;
                }

                // 进行可能密码尝试
                for (int j = 0; j < 4; j++) {
                    String upWord = changePass(tempWord, j, 1);

                    if (visited.contains(upWord)) {
                        continue;
                    }
                    q.offer(upWord);
                    visited.add(upWord);

                    String downWord = changePass(tempWord, j, -1);

                    if (visited.contains(downWord)) {
                        continue;
                    }

                    q.offer(downWord);
                    visited.add(downWord);

                }
            }

            step++;
        }
        return -1;
    }

    private String changePass(String tempWord, int j, int i) {
        char[] wordChars = tempWord.toCharArray();
        char c = wordChars[j];
        if (i == -1) {
            if (c != '0') {
                c = (char) (c + i);
            } else {
                c = '9';
            }
        } else {
            if (c != '9') {
                c = (char) (c + i);
            } else {
                c = '0';
            }
        }
        wordChars[j] = c;
        return new String(wordChars);
    }

    // 46. 全排列
    // 回溯算法
    List<List<Integer>> res = new LinkedList<>();

    public List<List<Integer>> permute(int[] nums) {

        traverse1(nums, new LinkedList<Integer>());
        return res;
    }

    private void traverse1(int[] nums, LinkedList<Integer> trace) {
        // 判断路径完成条件
        if (trace.size() == nums.length) {
            res.add(new LinkedList<>(trace));
        }

        for (int i = 0; i < nums.length; i++) {
            if (trace.contains(nums[i])) {
                continue;
            }

            // 添加选择
            trace.add(nums[i]);


            traverse1(nums, trace);

            //撤销选择
            trace.removeLast();
        }
    }

    // 51. N 皇后
    List<List<String>> res_51 = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {
        char[][] chessboard = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                chessboard[i][j] = '.';
            }
        }

        traverse2(chessboard, 0, n);

        return res_51;
    }

    private void traverse2(char[][] chessboard, int s, int n) {
        // 最后完成皇后摆放
        if (s == n) {
            List<String> tempRes = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                tempRes.add(new String(chessboard[i]));
            }
            res_51.add(tempRes);
            return;
        }

        for (int i = 0; i < n; i++) {
            if (!isValid(chessboard, s, i, n)) {
                continue;
            }

            // 放置皇后
            chessboard[s][i] = 'Q';
            traverse2(chessboard, s + 1, n);
            chessboard[s][i] = '.';
        }

    }

    private boolean isValid(char[][] chessboard, int r, int c, int n) {

        // 判断上面是否有皇后
        for (int i = 0; i <= r; i++) {
            if ('Q' == chessboard[i][c]) {
                return false;
            }
        }

        // 检查右上方是否有皇后互相冲突
        for (int i = r - 1, j = c + 1;
             i >= 0 && j < n; i--, j++) {
            if ('Q' == chessboard[i][j])
                return false;
        }
        // 检查左上方是否有皇后互相冲突
        for (int i = r - 1, j = c - 1;
             i >= 0 && j >= 0; i--, j--) {
            if ('Q' == chessboard[i][j])
                return false;
        }
        return true;
    }

    // 39. 组合总和
    List<List<Integer>> res_39 = new LinkedList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        traverse3(candidates, 0, target, new LinkedList<>());
        return res_39;
    }

    private void traverse3(int[] candidates, int begin, int target, LinkedList<Integer> list) {

        if (target < 0) {
            return;
        }

        if (target == 0) {
            res_39.add(new LinkedList<>(list));
            return;
        }

        for (int i = begin; i < candidates.length; i++) {
            list.add(candidates[i]);
            traverse3(candidates, i+1, target - candidates[i], list);
            list.removeLast();
        }

    }

    public static void main(String[] args) {

        Solution27 s27 = new Solution27();

        TreeNode tn1 = new TreeNode(3);
        TreeNode tn2 = new TreeNode(9);
        TreeNode tn3 = new TreeNode(20);
        TreeNode tn4 = new TreeNode(15);
        TreeNode tn5 = new TreeNode(7);

        tn1.left = tn2;
        tn1.right = tn3;
        tn3.left = tn4;
        tn3.right = tn5;

//        System.out.println(s27.minDepth(tn1));


        String beginWord = "hit", endWord = "cog";
        List<String> wordList = new ArrayList<>(Arrays.asList("hot", "dot", "dog", "lot", "log", "cog"));

//        String beginWord = "a", endWord = "c";
//        List<String> wordList = new ArrayList<>(Arrays.asList("a","b","c"));
//        System.out.println(s27.ladderLength(beginWord, endWord, wordList));

//        String[] deadends = {"8887", "8889", "8878", "8898", "8788", "8988", "7888", "9888"};
//        String target = "8888";
//        System.out.println(s27.openLock(deadends, target));

        int[] nums = {1, 2, 3};

//        System.out.println(s27.permute(nums));

//        System.out.println(s27.solveNQueens(4));

        int[] candidates = {10,1,2,7,6,1,5};
        int target = 8;
        System.out.println(s27.combinationSum(candidates, target));

    }

}
