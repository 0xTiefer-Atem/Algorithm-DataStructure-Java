package org.wq.leetcode.round1.day29;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Solution29 {
    // 40. 组合总和 II
    List<List<Integer>> res40 = new ArrayList<>();

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        if (candidates.length == 0) {
            return res40;
        }
        Arrays.sort(candidates);

        traverse1(candidates, 0, new LinkedList<>(), target);

        return res40;
    }

    private void traverse1(int[] candidates, int start, LinkedList<Integer> subList, int target) {
        if (target < 0) {
            return;
        }

        if (target == 0) {
            res40.add(new LinkedList<>(subList));
        }

        for (int i = start; i < candidates.length; i++) {

            if (i > start && candidates[i] == candidates[i - 1]) {
                continue;
            }
            subList.add(candidates[i]);

            traverse1(candidates, i + 1, subList, target - candidates[i]);

            subList.removeLast();
        }
    }

    // 216. 组合总和 III
    List<List<Integer>> res216 = new LinkedList<>();

    public List<List<Integer>> combinationSum3(int k, int n) {
        int[] candidates = new int[9];
        for (int i = 0; i < 9; i++) {
            candidates[i] = i + 1;
        }

        traverse2(candidates, 0, k, n, new LinkedList<>());
        return res216;
    }

    private void traverse2(int[] candidates, int start, int length, int target, LinkedList<Integer> subList) {
        if (target < 0) {
            return;
        }
        if (target == 0 && subList.size() == length) {
            res216.add(new LinkedList<>(subList));
        }
        for (int k = start; k < candidates.length; k++) {
            subList.add(candidates[k]);
            traverse2(candidates, k + 1, length, target - candidates[k], subList);
            subList.removeLast();
        }
    }

    // 46. 全排列
    List<List<Integer>> res46 = new LinkedList<>();
    boolean[] used;

    public List<List<Integer>> permute(int[] nums) {
        used = new boolean[nums.length];
        Arrays.fill(used, false);
        traverse1(nums, 0, new LinkedList<Integer>());
        return res46;
    }

    private void traverse1(int[] nums, int start, LinkedList<Integer> trace) {
        // 判断路径完成条件
        if (trace.size() == nums.length) {
            res46.add(new LinkedList<>(trace));
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }

            // 添加选择
            trace.add(nums[i]);
            used[i] = true;


            traverse1(nums, i + 1, trace);

            //撤销选择
            trace.removeLast();
            used[i] = false;
        }
    }


    // 47. 全排列 II
    List<List<Integer>> res47 = new LinkedList<>();
    boolean[] used47;

    public List<List<Integer>> permuteUnique(int[] nums) {
        used47 = new boolean[nums.length];
        Arrays.fill(used47, false);
        Arrays.sort(nums);
        traverse3(nums, new LinkedList<Integer>());
        return res47;
    }

    private void traverse3(int[] nums, LinkedList<Integer> trace) {
        // 判断路径完成条件
        if (trace.size() == nums.length) {
            res47.add(new LinkedList<>(trace));
        }

        for (int i = 0; i < nums.length; i++) {

            if (used47[i]) {
                continue;
            }

            if (i > 0 && nums[i] == nums[i - 1] && !used47[i - 1]) {
                continue;
            }

            // 添加选择
            trace.add(nums[i]);
            used47[i] = true;


            traverse3(nums, trace);

            //撤销选择
            trace.removeLast();
            used47[i] = false;
        }
    }

    // 77. 组合
    List<List<Integer>> res77 = new LinkedList<List<Integer>>();

    public List<List<Integer>> combine(int n, int k) {
        traverse4(n, 1, k, new LinkedList<>());
        return res77;
    }

    private void traverse4(int n, int start, int k, LinkedList<Integer> subList) {
        if (subList.size() == k) {
            res77.add(new LinkedList<>(subList));
        }

        for (int i = start; i <= n; i++) {
            subList.add(i);

            traverse4(n, i + 1, k, subList);

            subList.removeLast();
        }
    }

    // 78. 子集
    List<List<Integer>> res78 = new LinkedList<>();

    public List<List<Integer>> subsets(int[] nums) {
        traverse5(nums, 0, new LinkedList<>());

        return res78;
    }

    private void traverse5(int[] nums, int start, LinkedList<Integer> subList) {
        res78.add(new LinkedList<>(subList));

        for (int i = start; i < nums.length; i++) {
            subList.add(nums[i]);

            traverse5(nums, i + 1, subList);

            subList.removeLast();
        }
    }

    // 90. 子集 II
    List<List<Integer>> res90 = new LinkedList<>();

    public List<List<Integer>> subsetsWithDup(int[] nums) {

        Arrays.sort(nums);

        traverse6(nums, 0, new LinkedList<>());

        return res90;
    }

    private void traverse6(int[] nums, int start, LinkedList<Integer> subList) {
        res90.add(new LinkedList<>(subList));

        for (int i = start; i < nums.length; i++) {

            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            subList.add(nums[i]);

            traverse6(nums, i + 1, subList);

            subList.removeLast();
        }
    }

    public static void main(String[] args) {
        Solution29 s29 = new Solution29();


        int[] candidates = {10, 1, 2, 7, 6, 1, 5};
        int target = 8;
//        System.out.println(s29.combinationSum2(candidates, target));
//        System.out.println(s29.combinationSum3(3, 9));
        int[] nums = {1, 2, 2};
        System.out.println(s29.subsetsWithDup(nums));
    }
}
