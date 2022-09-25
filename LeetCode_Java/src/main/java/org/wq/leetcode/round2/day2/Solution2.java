package org.wq.leetcode.round2.day2;

import org.wq.leetcode.common.TreeNode;

import java.util.Arrays;
import java.util.HashMap;

public class Solution2 {
    // 198. 打家劫舍
    public int rob1(int[] nums) {
        int[] dpArr = new int[nums.length];
        Arrays.fill(dpArr, -1);
        return dp1(nums, 0, dpArr);
    }

    private int dp1(int[] nums, int i, int[] dpArr) {
        if (i >= nums.length) {
            return 0;
        }
        if (dpArr[i] != -1) {
            return dpArr[i];
        }

        int res = Math.max(dp1(nums, i + 1, dpArr), nums[i] + dp1(nums, i + 2, dpArr));

        dpArr[i] = res;
        return dpArr[i];
    }

    // 213. 打家劫舍 II

    public int rob2(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        return Math.max(dp2(nums, 0, nums.length - 2), dp2(nums, 1, nums.length - 1));
    }

    private int dp2(int[] nums, int start, int end) {
        int dp_0 = 0;
        int dp_1 = 0;
        int dp_2 = 0;
        for (int i = start; i <= end; i++) {
            dp_0 = Math.max(dp_1, nums[i] + dp_2);  
            dp_2 = dp_1;
            dp_1 = dp_0;

        }
        return dp_0;
    }

    HashMap<TreeNode, Integer> memoryMap = new HashMap<>();

    public int rob3(TreeNode root) {
        return dp3(root);
    }

    private int dp3(TreeNode root) {
        if (root == null) {
            return 0;
        }

        if (memoryMap.containsKey(root)) {
            return memoryMap.get(root);
        }

        int doRob = root.val;
        doRob += root.left == null ? 0 : dp3(root.left.left) + dp3(root.left.right);
        doRob += root.right == null ? 0 : dp3(root.right.left) + dp3(root.right.right);

        int notRob = 0;
        notRob += dp3(root.left);
        notRob += dp3(root.right);

        int res = Math.max(doRob, notRob);

        memoryMap.put(root, res);
        return res;
    }


    public static void main(String[] args) {
        Solution2 s = new Solution2();
        int[] house = {2, 3, 2};

        System.out.println(s.rob2(house));

    }
}
