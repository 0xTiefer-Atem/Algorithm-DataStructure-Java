package org.wq.leetcode.round2.day4;

import org.wq.leetcode.common.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Solution4 {
    // 121. 买卖股票的最佳时机
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        for (int i = 0; i < n; i++) {
            if (i - 1 == -1) {
                dp[i][0] = 0;
                dp[i][1] = -prices[i];

                continue;
            }
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], -prices[i]);
        }

        return dp[n - 1][0];
    }


    // 10. 正则表达式匹配
    public boolean isMatch(String s, String p) {
        return dp2(s, 0, p, 0);
    }

    private boolean dp2(String s, int i, String p, int j) {
        // base case
        int m = s.length(), n = p.length();
        // base case
        if (j == n) {
            return i == m;
        }
        if (i == m) {
            if ((n - j) % 2 == 1) {
                return false;
            }
            for (; j + 1 < n; j += 2) {
                if (p.charAt(j + 1) != '*') {
                    return false;
                }
            }
            return true;
        }


        // 匹配条件
        if (s.charAt(i) == p.charAt(j)) {
            if (j < s.length() - 1 && p.charAt(j + 1) == '*') {
                // 遇到 * 匹配0次或者 无数次
                return dp2(s, i, p, j + 2) || dp2(s, i + 1, p, j);
            } else {
                // 模式匹配下一次不是 * 正常匹配
                return dp2(s, i + 1, p, j + 1);
            }
        } else {
            if (j < s.length() - 1 && p.charAt(j + 1) == '*') {
                return dp2(s, i, p, j + 2);
            } else {
                // 2.2 无法继续匹配
                return false;
            }
        }
    }

    // 509. 斐波那契数
    public int fib(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }

        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }


    // 322. 零钱兑换
    public int coinChange(int[] coins, int amount) {
        return dp3(coins, amount);
    }

    private int dp1(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }

        if (amount < 0) {
            return -1;
        }

        int res = Integer.MAX_VALUE;

        for (int coin : coins) {
            int sub = dp1(coins, amount - coin);
            if (sub == -1) {
                continue;
            }
            res = Math.min(res, 1 + sub);
        }

        return res;
    }

    int dp3(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);

        dp[0] = 0;

        for (int i = 0; i <= amount; i++) {
            for (int coin : coins) {
                if (i - coin < 0) {
                    continue;
                }

                dp[i] = Math.min(dp[i], 1 + dp[i - coin]);
            }
        }


        return dp[amount];
    }

    // 112. 路径总和
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }

        if (root.left == root.right && root.val == targetSum) {
            return true;
        }

        return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
    }

    // 115. 不同的子序列
    int[][] memory;

    public int numDistinct(String s, String t) {
        memory = new int[s.length()][t.length()];

        for (int[] sub : memory) {
            Arrays.fill(sub, -1);
        }

        return dp4(s, 0, t, 0);
    }

    private int dp4(String s, int i, String t, int j) {

        if (j == t.length()) {
            return 1;
        }

        if (s.length() - i < t.length() - j) {
            return 0;
        }

        if (memory[i][j] != -1) {
            return memory[i][j];
        }

        int res = 0;
        if (s.charAt(i) == t.charAt(j)) {
            res += dp4(s, i + 1, t, j + 1) + dp4(s, i + 1, t, j);
        } else {
            res += dp4(s, i + 1, t, j);
        }
        memory[i][j] = res;
        return res;
    }

    // 139. 单词拆分
    HashSet<String> wordSet = new HashSet<>();
    int[] memory139;

    public boolean wordBreak(String s, List<String> wordDict) {
        wordSet.addAll(wordDict);
        memory139 = new int[s.length()];
        Arrays.fill(memory139, -1);
        return dp5(s, 0);
    }

    private boolean dp5(String s, int i) {
        if (i == s.length()) {
            return true;
        }

        // 防止冗余计算
        if (memory139[i] != -1) {
            return memory139[i] != 0;
        }

        for (int len = 1; len + i <= s.length(); len++) {
            String prefix = s.substring(i, i + len);
            if (wordSet.contains(prefix)) {
                boolean sub = dp5(s, i + len);
                if (sub) {
                    memory139[i] = 1;
                    return true;
                }
            }
        }

        memory139[i] = 0;
        return false;
    }




    public static void main(String[] args) {
        Solution4 s = new Solution4();

        List<String> words = new ArrayList<String>();
        words.add("leet");
        words.add("code");
        System.out.println(s.wordBreak("leetcode", words));
    }


}
