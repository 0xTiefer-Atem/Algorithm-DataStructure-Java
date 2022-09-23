package org.wq.leetcode.round1.day19;

public class Solution19 {
    // 238. 除自身以外数组的乘积
    public int[] productExceptSelf(int[] nums) {
        int max = 1;
        for (int i = 0; i < nums.length; i++) {
            max = max * nums[i];
        }

        int[] res = new int[nums.length];

        for (int i = 0; i < res.length; i++) {
            res[i] = max / nums[i];
        }

        return res;
    }
}
