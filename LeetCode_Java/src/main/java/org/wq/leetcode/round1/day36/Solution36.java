package org.wq.leetcode.round1.day36;

import java.util.Arrays;
import java.util.HashMap;

public class Solution36 {
    // 1. 两数之和
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }

        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{i, map.get(target - nums[i])};
            }
        }

        return new int[]{-1, -1};
    }

    // 80. 删除有序数组中的重复项 II
    public int removeDuplicates(int[] nums) {
        int slow = 0;
        int fast = 0;
        int count = 0;
        while (fast < nums.length) {
            if (nums[slow] == nums[fast]) {
                count++;
                if (count <= 2) {
                    slow++;
                }
            }

            if (count > 2 && nums[slow] != nums[fast]) {
                nums[++slow] = nums[fast];
            }
        }

        return slow;
    }


    public static void main(String[] args) {
        Solution36 s36 = new Solution36();

        int[] nums = {3, 2, 4};
        System.out.println(Arrays.toString(s36.twoSum(nums, 6)));
    }
}
