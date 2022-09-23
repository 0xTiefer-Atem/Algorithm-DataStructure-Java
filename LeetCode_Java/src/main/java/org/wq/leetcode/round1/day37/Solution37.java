package org.wq.leetcode.round1.day37;

public class Solution37 {

    // 121. 买卖股票的最佳时机
    public int maxProfit(int[] prices) {
        int max = 0;
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < prices.length; i++) {
            max = Math.max(max, prices[i] - min);
            min = Math.min(min, prices[i]);
        }

        return max;
    }

    //338. 比特位计数
    public int[] countBits(int n) {
        int[] result = new int[n + 1];
        int m = 1;
        for (int i = 1; i <= n; i++) {
            if (i == (m << 1)) {
                m <<= 1;
            }
            result[i] = result[i - m] + 1;
        }

        return result;
    }

    // 392. 判断子序列
    public boolean isSubsequence(String s, String t) {
        int i = 0;
        int j = 0;
        while (i < s.length() && j < t.length()) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
            }
        }
        return i == s.length();
    }

    // 746. 使用最小花费爬楼梯
    public int minCostClimbingStairs(int[] cost) {
        int length = cost.length;
        if (cost.length == 0) return 0;
        if (cost.length == 1) return cost[0];

        int[] dp = new int[length + 1];
        dp[0] = cost[0];
        dp[1] = cost[1];


        for (int i = 2; i < length; i++) {
            dp[i] = cost[i] + Math.min(dp[i - 1], dp[i - 2]);
        }

        dp[length] = Math.min(dp[length - 1], dp[length - 2]);


        return dp[length];
    }

    //1137. 第 N 个泰波那契数
    public int tribonacci(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        if (n == 2) return 1;


        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
        }
        return dp[n];
    }

    // 1646. 获取生成数组中的最大值
    public int getMaximumGenerated(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;

        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        int max = 1;
        for (int i = 2; i < dp.length; i++) {
            if (i % 2 == 0) {
                dp[i] = dp[i / 2];
            } else {
                dp[i] = dp[i / 2] + dp[(i / 2) + 1];
            }

            max = Math.max(max, dp[i]);
        }


        return max;
    }


    // LCP 07. 传递信息
    public int numWays(int n, int[][] relation, int k) {
        int[][] dp = new int[k + 1][n];
        dp[0][0] = 1;

        for (int i = 0; i < k; i++) {
            for (int[] r : relation) {
                int s = r[0];
                int d = r[1];
                dp[i + 1][d] += dp[i][s];
            }
        }

        return dp[k][n - 1];
    }

    // LCS 01. 下载插件
    public int leastMinutes(int n) {
        int res = 0;
        for (int i = n; i > 0; i -= 2) {
            res++;
        }

        return res + 1;
    }

    // 剑指 Offer 42. 连续子数组的最大和
    public int maxSubArray(int[] nums) {
        if (nums == null) {
            return 0;
        }
        int length = nums.length;
        if (length == 1) {
            return nums[0];
        }


        int[] dp = new int[length + 1];
        dp[0] = nums[0];
        int max = dp[0];
        for (int i = 1; i < length; i++) {
            dp[i] = Math.max(nums[i], dp[i - 1] + nums[i]);
            max = Math.max(max, dp[i]);
        }
        return max;
    }


    // 剑指 Offer II 003. 前 n 个数字二进制中 1 的个数
    public int[] countBits1(int n) {
        if (n == 0) return new int[]{0};
        if (n == 1) return new int[]{0, 1};
        if (n == 2) return new int[]{0, 1, 1};

        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 1;

        int k = 1;
        for (int i = 3; i <= n; i++) {
            if (i == (k << 1)) {
                k <<= 1;
            }
            dp[i] = dp[i - k] + 1;
        }

        return dp;
    }


    public static void main(String[] args) {
        Solution37 s37 = new Solution37();

        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(s37.maxSubArray(nums));
    }
}
