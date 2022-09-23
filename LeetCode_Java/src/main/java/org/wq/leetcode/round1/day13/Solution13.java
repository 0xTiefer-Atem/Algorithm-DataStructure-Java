package org.wq.leetcode.round1.day13;

import org.wq.leetcode.common.Utils;

import java.util.*;

public class Solution13 {

    public void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // 31. 下一个排列
    public void nextPermutation(int[] nums) {
        int n = nums.length;
        int k = n - 2;

        // 找到第一个升序的位置
        while (k >= 0 && nums[k] >= nums[k + 1]) {
            k--;
        }

        if (k < 0) { // 整个序列是降序
            reserve(nums, 0, n - 1);
        } else {
            int l = k + 1;
            int r = n - 1;
            while (l < r) {
                int mid = l + r >> 1;
                if (nums[mid] > nums[k]) {
                    l = mid;
                } else {
                    r = mid - 1;
                }
            }
            Utils.swap(nums, k, l);
            reserve(nums, k + 1, n - 1);
        }

    }


    public void reserve(int[] arr, int i, int j) {

        while (i < j) {
            Utils.swap(arr, i, j);
            i++;
            j--;
        }

    }


    //32. 最长有效括号
    public int longestValidParentheses(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        Stack<Integer> stack = new Stack<Integer>();
        int maxLength = 0;
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            if ('(' == s.charAt(i)) {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    maxLength = Math.max(maxLength, i - stack.peek());
                }
            }
        }
        return maxLength;
    }

    // 33. 搜索旋转排序数组
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        // 使用二分查找法找旋转点
        int l = 0;
        int n = nums.length;
        int r = n - 1;

        while (l < r) {
            int mid = l + r + 1 >> 1;
            if (nums[mid] >= nums[0]) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }

        if (nums[l] == target) {
            return l;
        }

        if (nums[0] > target) {
            l = l + 1;
            r = n - 1;
        } else {
            l = 0;
        }

        while (l < r) {
            int mid = l + r >> 1;

            if (nums[mid] >= target) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }

        if (nums[l] == target) {
            return target;
        }
        return -1;
    }

    // 34. 在排序数组中查找元素的第一个和最后一个位置
    public int[] searchRange(int[] nums, int target) {

        if (nums == null || nums.length == 0) {
            return new int[]{-1, -1};
        }

        int[] res = new int[2];
        Arrays.fill(res, -1);
        int n = nums.length;
        int l = 0;
        int r = n - 1;

        while (l < r) {
            int mid = l + r >> 1;
            if (nums[mid] >= target) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }

        if (nums[l] == target) {
            res[0] = l;
        }

        l = 0;
        r = n - 1;

        while (l < r) {
            int mid = l + r + 1 >> 1;
            if (nums[mid] <= target) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }

        if (nums[l] == target) {
            res[1] = l;
        }

        return res;
    }

    //39. 组合总和
    // 回溯问题
    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {

        if (candidates == null || candidates.length == 0) {
            return new ArrayList<>();
        }

        process1(candidates, 0, target, new LinkedList<>());

        return res;

    }

    private void process1(int[] candidates, int index, int target, LinkedList<Integer> list) {

        if (index == candidates.length) {
            if (target == 0) {
                res.add(new LinkedList<Integer>(list));
                return;
            } else {
                return;
            }
        }


        for (int i = 0; i * candidates[index] <= target; i++) { // 模拟每个元素只能选择一次
            process1(candidates, index + 1, target - i * candidates[index], list);
            list.addLast(candidates[index]);
        }
        for (int i = 0; i * candidates[index] <= target; i++) {
            list.removeLast();
        }

    }

    //42. 接雨水
    public int trap(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }

        int n = height.length;
        int[] left = new int[n];
        int[] right = new int[n];
        int res = 0;
        left[0] = height[0];
        right[n - 1] = height[n - 1];

        for (int i = 1; i < n; i++) {
            left[i] = Math.max(height[i], left[i - 1]);
        }
        for (int i = n - 2; i >= 0; i--) {
            right[i] = Math.max(height[i], right[i + 1]);
        }

        for (int i = 0; i < n; i++) {
            res += Math.min(left[i], right[i]) - height[i];
        }


        return res;
    }

    //46. 全排列
    List<List<Integer>> permuteRes = new LinkedList<>();

    public List<List<Integer>> permute(int[] nums) {
        process2(nums, 0, new LinkedList<>());

        return permuteRes;
    }

    private void process2(int[] nums, int i, LinkedList<Integer> list) {
        if (i == nums.length) {
            permuteRes.add(new LinkedList<>(list));
            return;
        }

        for (int j = i; j < nums.length; j++) {
            swap(nums, i, j);
            list.add(nums[i]);
            process2(nums, i + 1, list);
            list.removeLast();
            swap(nums, i, j);
        }
    }

    // 48. 旋转图像
    public void rotate(int[][] matrix) {

        if (matrix == null || matrix.length == 0) {
            return;
        }

        int a = 0;
        int b = 0;

        int c = matrix.length;
        int d = matrix[0].length;

        int n = matrix.length;

        while (a < c) {
            process3(matrix, a++, b++, c--, d--);

        }
    }

    private void process3(int[][] matrix, int a, int b, int c, int d) {
        for (int i = 0; i < c - d; i++) {
            int temp = matrix[a][b];
            matrix[a][b] = matrix[a + i][b];
            matrix[a + i][b] = matrix[c][d];
            matrix[c][d] = matrix[a][b + i];
            matrix[a][b + i] = temp;
        }
    }

    // 49. 字母异位词分组
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] charArr = str.toCharArray();
            Arrays.sort(charArr);
            String pattern = String.valueOf(charArr);
            if (!map.containsKey(pattern)) {
                map.put(pattern, new ArrayList<>());
            }
            map.get(pattern).add(str);
        }

        List<List<String>> res = new ArrayList<>();
        map.forEach((x, y) -> res.add(y));
        return res;
    }

    // 53. 最大子数组和
    public int maxSubArray(int[] nums) {
        int pre = 0;
        int maxValue = nums[0];
        for (int num : nums) {
            pre = Math.max(pre + num, num);
            maxValue = Math.max(maxValue, pre);
        }
        return maxValue;
    }

    //55. 跳跃游戏
    public boolean canJump(int[] nums) {
        int end = 0;
        for (int i = 0; i < nums.length; i++) {
            if (end < i) {
                return false;
            }
            end = Math.max(end, i + nums[i]);
        }
        return true;
    }

    //56. 合并区间
    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length < 1 || intervals[0].length < 1) {
            return new int[2][2];
        }

        // 按照开始时间进行降序排列
        Arrays.sort(intervals, (x, y) -> x[0] - y[0]);
        int n = intervals.length;
        List<int[]> list = new ArrayList<>();
        int start = intervals[0][0];
        int end = intervals[0][1];
        for (int i = 1; i < n; i++) {
            if (intervals[i][0] > end) {
                list.add(new int[]{start, end});
                start = intervals[i][0];
                end = intervals[i][1];
            } else {
                end = Math.max(end, intervals[i][1]);
            }
        }
        list.add(new int[]{start, end});
        int[][] res = new int[list.size()][2];
        for (int i = 0; i < list.size(); i++) {
            res[i][0] = list.get(i)[0];
            res[i][1] = list.get(i)[1];
        }
        return res;
    }

    //62. 不同路径
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] += dp[i - 1][j];
                dp[i][j] += dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }

    // 64. 最小路径和
    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length < 1 || grid[0].length < 1) {
            return 0;
        }

        int r = grid.length;
        int c = grid[0].length;
        int[][] dp = new int[r][c];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < r; i++) {
            dp[i][0] = grid[i][0] + dp[i - 1][0];
        }

        for (int i = 1; i < c; i++) {
            dp[0][i] = grid[0][i] + dp[0][i - 1];
        }

        for (int i = 1; i < r; i++) {
            for (int j = 1; j < c; j++) {
                dp[i][j] = grid[i][j] + Math.min(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        return dp[r - 1][c - 1];
    }

    public int uniquePaths1(int m, int n) {
        return process4(m, n, 0, 0);
    }

    private int process4(int m, int n, int start, int end) {
        if (start > m || end > n) {
            return 0;
        }
        if (start == m - 1 && end == n - 1) {
            return 1;
        }
        return process4(m, n, start + 1, end) + process4(m, n, start, end + 1);
    }

    // 70. 爬楼梯
    public int climbStairs(int n) {
        if (n < 0) {
            return 0;
        }
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    public int climbStairs1(int n) {
        return process5(n);
    }

    private int process5(int n) {
        if (n < 0) {
            return 0;
        }
        if (n == 0) {
            return 1;
        }
        return process5(n - 1) + process5(n - 2);
    }

    public static void main(String[] args) {
        Solution13 solution13 = new Solution13();

//        int[] arr = {1, 3, 2};
//        solution13.nextPermutation(arr);
//        System.out.println(Arrays.toString(arr));

//        System.out.println(solution13.longestValidParentheses(")(()()()))"));

//        int[] candidates = {2, 3, 6, 7};
//        int target = 7;
//        System.out.println(solution13.combinationSum(candidates, target));


//        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
//        System.out.println(solution13.trap(height));

//        int[] nums = {1, 2, 3};
//        System.out.println(solution13.permute(nums));

//        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
//        System.out.println(solution13.groupAnagrams(strs));

//        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
//        System.out.println(solution13.maxSubArray(nums));


//        int[][] intervals = {{1, 3}, {2, 6}, {5, 10}, {15, 18}};
//        int[][] intervals = {{1, 4}, {4, 5}};
//        System.out.println(Arrays.deepToString(solution13.merge(intervals)));

//        System.out.println(solution13.uniquePaths(23, 12));

//        int[][] grid = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
//        int[][] grid = {{1, 2, 3}, {4, 5, 6}};
//        System.out.println(solution13.minPathSum(grid));

        System.out.println(solution13.climbStairs(3));

    }
}
