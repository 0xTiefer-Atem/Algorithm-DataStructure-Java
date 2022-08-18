package org.wq.leetcode.day30;

import java.util.LinkedList;
import java.util.List;

public class Solution30 {
    // 136. 只出现一次的数字
    public int singleNumber(int[] nums) {
        int res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            res = res ^ nums[i];
        }
        return res;
    }

    // 191. 位1的个数
    public int hammingWeight(int n) {
        String s = Integer.toBinaryString(n);
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                res++;
            }
        }
        return res;
    }

    // 491. 递增子序列
    List<List<Integer>> res491 = new LinkedList<>();

    public List<List<Integer>> findSubsequences(int[] nums) {

        traverse1(nums, 0, new LinkedList<Integer>());

        return res491;
    }

    private void traverse1(int[] nums, int start, LinkedList<Integer> subList) {

        if (subList.size() > 1) {
            res491.add(new LinkedList<>(subList));
        }

        for (int i = start; i < nums.length; i++) {

            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }

            if (subList.size() > 0 && nums[i] < subList.getLast()) {
                continue;
            }

            subList.add(nums[i]);

            traverse1(nums, i + 1, subList);

            subList.removeLast();
        }
    }

    // 17. 电话号码的字母组合
    String[] telephone = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    List<String> res = new LinkedList<>();

    public List<String> letterCombinations(String digits) {

        if(digits == null || digits.length() == 0) {
            return res;
        }

        traverse2(digits, 0, new LinkedList<Character>());

        return res;
    }

    private void traverse2(String digits, int start, LinkedList<Character> subList) {
        if (subList.size() == digits.length()) {
            StringBuilder s = new StringBuilder();
            subList.forEach(s::append);
            res.add(s.toString());
            return;
        }

        for (int i = start; i < digits.length(); i++) {
            int num = digits.charAt(i) - '0';
            String numStr = telephone[num];

            for (int j = 0; j < numStr.length(); j++) {
                subList.add(numStr.charAt(j));

                traverse2(digits, i + 1, subList);

                subList.removeLast();
            }
        }
    }

    public static void main(String[] args) {
        Solution30 s30 = new Solution30();

//        System.out.println(s30.hammingWeight(00000000000000000000000000001011));
//        System.out.println(Integer.toOctalString(521));

//        int[] nums = {4, 4, 3, 2, 1};
//        System.out.println(s30.findSubsequences(nums));

        System.out.println(s30.letterCombinations("2"));
    }
}
