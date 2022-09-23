package org.wq.leetcode.round1.day25;

import java.util.*;

public class Solution25 {
    // 76. 最小覆盖子串
    public String minWindow(String s, String t) {
        Map<Character, Integer> need = new HashMap<>();

        for (int i = 0; i < t.length(); i++) {
            need.put(t.charAt(i), need.getOrDefault(t.charAt(i), 0) + 1);
        }

        Map<Character, Integer> window = new HashMap<>();
        int left = 0;
        int right = 0;

        int start = 0;
        int length = Integer.MAX_VALUE;

        // 窗口内有效字符个数
        int valid = 0;


        while (right < s.length()) {
            Character rc = s.charAt(right);
            right++;

            if (need.containsKey(rc)) {
                window.put(rc, window.getOrDefault(rc, 0) + 1);
                if (Objects.equals(window.get(rc), need.get(rc))) {
                    valid++;
                }
            }

            while (valid == need.size()) {
                if (length > right - left) {
                    length = right - left;
                    start = left;
                }
                Character lc = s.charAt(left);
                left++;
                if (need.containsKey(lc)) {
                    if (Objects.equals(window.get(lc), need.get(lc))) {
                        valid--;
                    }
                    window.put(lc, window.getOrDefault(lc, 0) - 1);
                }

            }


        }
        return length == Integer.MAX_VALUE ? "" : s.substring(start, start + length);
    }

    // 567. 字符串的排列
    public boolean checkInclusion(String s1, String s2) {
        Map<Character, Integer> need = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();

        for (int i = 0; i < s1.length(); i++) {
            need.put(s1.charAt(i), need.getOrDefault(s1.charAt(i), 0) + 1);
        }

        int left = 0;
        int right = 0;

        int valid = 0;
        while (right < s2.length()) {
            Character rc = s2.charAt(right);
            right++;

            //窗口增大之后需要做的操作
            if (need.containsKey(rc)) {
                window.put(rc, window.getOrDefault(rc, 0) + 1);
                if (need.get(rc).equals(window.get(rc))) {
                    valid++;
                }
            }

            while (right - left >= s1.length()) {

                // 更新操作
                if (valid == need.size()) {
                    return true;
                }
                Character lc = s2.charAt(left);
                left++;

                // 窗口缩小操作
                if (need.containsKey(lc)) {
                    if (window.get(lc).equals(need.get(lc))) {
                        valid--;
                    }
                    window.put(lc, window.getOrDefault(lc, 0) - 1);
                }
            }
        }
        return false;
    }

    // 438. 找到字符串中所有字母异位词
    public List<Integer> findAnagrams(String s, String p) {
        Map<Character, Integer> need = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();

        for (int i = 0; i < p.length(); i++) {
            need.put(p.charAt(i), need.getOrDefault(p.charAt(i), 0) + 1);
        }

        int left = 0;
        int right = 0;

        int valid = 0;

        List<Integer> res = new ArrayList<>();
        while (right < s.length()) {
            Character rc = s.charAt(right);
            right++;

            if (need.containsKey(rc)) {
                window.put(rc, window.getOrDefault(rc, 0) + 1);
                if (need.get(rc).equals(window.get(rc))) {
                    valid++;
                }
            }


            while (right - left >= p.length()) {

                if (valid == need.size()) {
                    res.add(left);
                }

                Character lc = s.charAt(left);
                left++;

                if (need.containsKey(lc)) {
                    if (window.get(lc).equals(need.get(lc))) {
                        valid--;
                    }
                    window.put(lc, window.getOrDefault(lc, 0) - 1);
                }
            }
        }
        return res;
    }

    // 3. 无重复字符的最长子串
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> window = new HashMap<Character, Integer>();
        int left = 0;
        int right = 0;
        int res = 0;
        while (right < s.length()) {
            Character rc = s.charAt(right);
            right++;
            window.put(rc, window.getOrDefault(rc, 0) + 1);
            while (window.get(rc) > 1) {
                Character lc = s.charAt(left);
                left++;
                window.put(lc, window.getOrDefault(lc, 0) - 1);
            }

            res = Math.max(res, right - left);
        }

        return res;
    }

    // 509. 斐波那契数
    public int fib1(int n) {
        if (n == 0 || n == 1) {
            return n;
        }
        return fib1(n - 1) + fib1(n - 2);
    }

    public int fib2(int n) {
        int[] memory = new int[n + 1];
        Arrays.fill(memory, -1);

        s1(memory, n);

        return memory[n];
    }

    private int s1(int[] memory, int n) {
        if (n == 0 || n == 1) {
            return n;
        }

        if (memory[n] != -1) {
            return memory[n];
        }

        memory[n] = s1(memory, n - 1) + s1(memory, n - 2);
        return memory[n];
    }


    public int fib3(int n) {
        int[] memory = new int[n + 1];
        memory[0] = 0;
        memory[1] = 1;

        for (int i = 2; i <= n; i++) {
            memory[i] = memory[i - 1] + memory[i - 2];
        }

        return memory[n];
    }

    public int fib4(int n) {
        int f1 = 0;
        int f2 = 1;

        int sum = 0;
        for (int i = 2; i <= n; i++) {
            sum = f1 + f2;
            f1 = f2;
            f2 = sum;
        }

        return sum;
    }


    // 322. 零钱兑换
    public int coinChange1(int[] coins, int amount) {
        int[] memory = new int[amount + 1];
        Arrays.fill(memory, -33);
        dp(coins, amount, memory);
        return memory[amount] == -33 ? -1 : memory[amount];
    }

    private int dp(int[] coins, int amount, int[] memory) {

        if (amount < 0) {
            return -1;
        }

        if (amount == 0) {
            return 0;
        }

        if (memory[amount] != -33) {
            return memory[amount];
        }

        int res = Integer.MAX_VALUE;
        for (int coin : coins) {
            int subP = dp(coins, amount - coin, memory);

            if (subP == -1) {
                continue;
            }

            res = Math.min(res, subP + 1);
            memory[amount] = res;
        }

        return memory[amount] == -33 ? -1 : memory[amount];
    }

    public int coinChange2(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 2);
        dp[0] = 0;

        int res = 0;
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (i < coins[j]) {
                    continue;
                }
                dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
            }
        }
        return (dp[amount] == amount + 1) ? -1 : dp[amount];
    }

    // 300. 最长递增子序列
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length + 1];
        Arrays.fill(dp, 1);

        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        int res = 0;
        for (int j : dp) {
            res = Math.max(res, j);
        }
        return res;
    }

    // 354. 俄罗斯套娃信封问题
    public int maxEnvelopes(int[][] envelopes) {
        int n = envelopes.length;
        Arrays.sort(envelopes, (Comparator<int[]>) (o1, o2) -> {
            return o1[0] == o2[0] ? o2[1] - o1[1] : o1[0] - o2[0];
        });

        int[] height = new int[n];
        for (int i = 0; i < n; i++) {
            height[i] = envelopes[i][1];
        }

        return lengthOfLIS(height);
    }


    public static void main(String[] args) {
        Solution25 s25 = new Solution25();

//        String s = "ADOBECODEBBANC", t = "ABBC";
//        System.out.println(s25.minWindow(s, t));

//        String s1 = "abcabcbb", s2 = "abc";
//        System.out.println(s25.lengthOfLongestSubstring(s1));

//        int[] coins = {1, 2, 5};
//        int amount = 11;
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println(s25.lengthOfLIS(nums));
    }
}
