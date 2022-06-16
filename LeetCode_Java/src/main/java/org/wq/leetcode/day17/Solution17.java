package org.wq.leetcode.day17;

import org.wq.leetcode.common.ListNode;

import java.util.HashMap;

public class Solution17 {

    // 128. 最长连续序列
    public int longestConsecutive(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], 1);
        }

        int res = 0;
        for (Integer key : map.keySet()) {
            int length = 1;
            for (int i = 1; map.containsKey(key + i) && map.get(key + i) == 1; i++) {
                length++;
                map.put(key, 0);
            }

            for (int i = 1; map.containsKey(key - i) && map.get(key - i) == 1; i++) {
                length++;
                map.put(key, 0);
            }
            res = Math.max(res, length);
        }

        return res;
    }

    // 148. 排序链表
    public ListNode sortList(ListNode head) {
        return mergeSort(head);
    }

    private ListNode mergeSort(ListNode head) {

        if (head == null || head.next == null) {
            return head;
        }


        ListNode s = head;
        ListNode f = head;
        ListNode pre = null;


        while (s != null && f != null) {
            pre = s;
            s = s.next;
            f = f.next;
            if (f == null) {
                break;
            }
            f = f.next;
        }
        pre.next = null;

        ListNode p = head;
        ListNode q = s;
        ListNode lNode = mergeSort(p);
        ListNode rNode = mergeSort(q);

        ListNode curr = new ListNode();
        pre = curr;
        while (lNode != null && rNode != null) {
            if (lNode.val <= rNode.val) {
                pre.next = lNode;
                pre = pre.next;
                lNode = lNode.next;
            } else {
                pre.next = rNode;
                pre = pre.next;
                rNode = rNode.next;
            }
        }

        while (lNode != null) {
            pre.next = lNode;
            pre = pre.next;
            lNode = lNode.next;
        }

        while (rNode != null) {
            pre.next = rNode;
            pre = pre.next;
            rNode = rNode.next;
        }

        return curr.next;

    }

    // 169. 多数元素
    public int majorityElement(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();

        int res = -1;
        for (int i : nums) {
            map.put(i, map.getOrDefault(i, 0) + 1);
            if (map.get(i) > nums.length >> 1) {
                res = i;
            }
        }
        return res;
    }


    // 152. 乘积最大子数组
    public int maxProduct(int[] nums) {
        int res = Integer.MIN_VALUE;

        int val = 1;
        for (int l = 0, r = 0; l < nums.length; ) {

            if (r == nums.length || nums[r] == 0) {

                while (l < r) {
                    res = Math.max(res, val);
                    val /= nums[l++];
                }

                if (r < nums.length && nums[r] == 0) {
                    res = Math.max(res, 0);
                }
                l++;
                r++;


            } else {
                val *= nums[r++];
                res = Math.max(res, val);
            }
        }
        return res;
    }


    public static void main(String[] args) {
        Solution17 s17 = new Solution17();

//        int[] nums = {0, -1};
//        System.out.println(s17.longestConsecutive(nums));
//        String s = new String();

        int[] nums = {2, 2, 1, 0, 1, 2, -2};
        System.out.println(s17.maxProduct(nums));
    }


}
