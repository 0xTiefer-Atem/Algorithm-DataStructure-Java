package org.wq.leetcode.round1.day3;

import org.wq.leetcode.common.ListNode;

import java.util.HashMap;

public class Solution3 {

    /**
     * 两数之和
     * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
     * <p>
     * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
     * <p>
     * 你可以按任意顺序返回答案。
     * 示例 1：
     * 输入：nums = [2,7,11,15], target = 9
     * 输出：[0,1]
     * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
     * <p>
     * 示例 2：
     * 输入：nums = [3,2,4], target = 6
     * 输出：[1,2]
     * <p>
     * 示例 3：
     * 输入：nums = [3,3], target = 6
     * 输出：[0,1]
     */

    public int[] twoSum(int[] nums, int target) {

        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }

        return new int[]{-1, -1};

    }

    /**
     * 两数相加
     * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
     * 请你将两个数相加，并以相同形式返回一个表示和的链表。
     * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     * <p>
     * 示例 1：
     * 输入：l1 = [2,4,3], l2 = [5,6,4]
     * 输出：[7,0,8]
     * 解释：342 + 465 = 807.
     * <p>
     * 示例 2：
     * 输入：l1 = [0], l2 = [0]
     * 输出：[0]
     * <p>
     * 示例 3：
     * 输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
     * 输出：[8,9,9,9,0,0,0,1]
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode preNode = new ListNode(0);
        ListNode tail = preNode;

        int carry = 0;

        while (l1!= null || l2!= null){
            int num1 = l1 == null ? 0 : l1.val;
            int num2 = l2 == null ? 0 : l2.val;

            int sum = num1 + num2 + carry;

            carry = sum / 10;
            tail.next = new ListNode(sum % 10);

            tail = tail.next;


            if (l1 != null) {
                l1 = l1.next;
            }

            if (l2 != null) {
                l2 = l2.next;
            }
        }

        if (carry == 1) {
            tail.next = new ListNode(carry);
        }

        return preNode.next;
    }


    /**
     * 无重复字符的最长子串
     * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
     *
     * 示例 1:
     * 输入: s = "abcabcbb"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
     *
     *
     * 示例 2:
     * 输入: s = "bbbbb"
     * 输出: 1
     * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 .
     *
     *
     * 示例 3:
     * 输入: s = "pwwkew"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
     */

    public int lengthOfLongestSubstring(String s) {
        int length = s.length();
        if (length == 0) {
            return 0;
        }
        HashMap<String, Integer> chartMap = new HashMap<String, Integer>();
        int j = 0;
        int maxlength = 0;
        for (int i = 0; i < length; i++) {
            // 此时队列中不包含重复字符
            while (chartMap.containsKey(s.charAt(i)+"")){
                chartMap.remove(s.charAt(j)+"");
                j++;

            }
            chartMap.put(s.charAt(i)+"", i);

            maxlength = Math.max(maxlength, chartMap.size());
        }
        return maxlength;
    }

    private int getListLong(ListNode l) {
        int count = 0;

        while (l != null){
            count++;
            l = l.next;
        }

        return count;
    }


    /**
     * 寻找两个正序数组的中位数
     * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
     *
     * 算法的时间复杂度应该为 O(log (m+n)) 。
     * 示例 1：
     *
     * 输入：nums1 = [1,3], nums2 = [2]
     * 输出：2.00000
     * 解释：合并数组 = [1,2,3] ，中位数 2
     *
     *
     * 示例 2：
     * 输入：nums1 = [1,2], nums2 = [3,4]
     * 输出：2.50000
     * 解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int length1 = nums1.length;
        int length2 = nums2.length;
        int[] num = new int[length1 + length2];


        int i = 0;
        int j = 0;
        int k  = 0;

        while (k != (length1 + length2)){

            if (i == length1) {
                while (j < length2){
                    num[k++] = nums2[j];
                    j++;
                }
                break;
            }

            if (j == length2) {
                while (i < length1){
                    num[k++] = nums1[i];
                    i++;
                }
                break;
            }


            if (nums1[i] < nums2[j]) {
                num[k++] = nums1[i];
                i++;
            }else {
                num[k++] = nums2[j];
                j++;
            }
        }

        if ((length1 + length2) % 2 == 0) {
            return (num[(length1 + length2) / 2 - 1] + num[(length1 + length2) / 2]) / 2.0;
        }else {
            return  num[(length1 + length2) / 2];
        }

    }

    private double getMidVal(int[] nums) {
        int length = nums.length;
        if (length % 2 != 0){
            return nums[length / 2];
        }else {
            return (double) (nums[length / 2 - 1] + nums[length / 2]) / 2;
        }
    }

    public static void main(String[] args) {

        Solution3 solution3 = new Solution3();

        int[] nums1 = {1, 2};
        int[] nums2 = {3, 4};

        System.out.println(solution3.findMedianSortedArrays(nums1, nums2));

    }

}
