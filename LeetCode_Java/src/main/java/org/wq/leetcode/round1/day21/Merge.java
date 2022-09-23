package org.wq.leetcode.round1.day21;

public class Merge {
    private static int[] temp;

    public int[] sortArray(int[] nums) {
        temp = new int[nums.length];
        sort(nums, 0, nums.length - 1);
        return nums;
    }

    private void sort(int[] nums, int l, int r) {
        if (l == r) {
            return;
        }

        int mid = l + (r - l) / 2;
        sort(nums, l, mid);
        sort(nums, mid + 1, r);

        merge(nums, l, mid, r);
    }

    private void merge(int[] nums, int l, int mid, int r) {
        for (int i = l; i <= r; i++) {
            temp[i] = nums[i];
        }

        int i = l;
        int j = mid + 1;
        for (int p = l; p <= r; p++) {

            if (i == mid + 1) {
                nums[p] = temp[j++];
            } else if (j == r + 1) {
                nums[p] = temp[i++];
            } else if (temp[i] > temp[j]) {
                nums[p] = temp[j++];
            } else {
                nums[p] = temp[i++];
            }
        }
    }
}
