package org.wq.leetcode.day16;

import org.wq.leetcode.common.ListNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;


public class Solution16 {
    //136. 只出现一次的数字
    public int singleNumber(int[] nums) {
        int res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            res = res ^ nums[i];
        }
        return res;
    }

    //139. 单词拆分
    public boolean wordBreak(String s, List<String> wordDict) {
        HashSet<String> wordSet = new HashSet<>(wordDict);
        String newS = "#" + s;
        int[] isConstant = new int[newS.length()];
        isConstant[0] = 1;
        for (int i = 1; i < newS.length(); i++) {
            for (int j = i - 1; j >= 0; j--) {
                String substring = newS.substring(j + 1, i + 1);
                if (wordSet.contains(substring) && isConstant[j] == 1) {
                    isConstant[i] = 1;
                    break;
                }
            }
        }
        return isConstant[newS.length() - 1] == 1;
    }

    // 160. 相交链表
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
//        return process1(headA, headB);
        return process2(headA, headB);
    }

    private ListNode process2(ListNode headA, ListNode headB) {
        ListNode p = headA;
        ListNode q = headB;

        int count = 0;
        while (p != null) {
            count++;
            p = p.next;
        }


        while (q != null) {
            count--;
            q = q.next;
        }

        // 说明没有交集
        if (q != p) {
            return null;
        }

        ListNode l = count > 0 ? headA : headB;
        ListNode s = l == headA ? headB : headA;


        count = Math.abs(count);

        while (count != 0) {
            l = l.next;
            count--;
        }

        while (l != s) {
            l = l.next;
            s = s.next;
        }
        return l;
    }

    private ListNode process1(ListNode headA, ListNode headB) {
        HashSet<ListNode> nodeSet = new HashSet<>();
        ListNode p = headA;
        while (p != null) {
            nodeSet.add(p);
            p = p.next;
        }

        ListNode q = headB;
        while (q != null) {
            if (nodeSet.contains(q)) {
                return q;
            }
            q = q.next;
        }

        return null;
    }


    public static void main(String[] args) {
        Solution16 s16 = new Solution16();

//        int[] nums = {1, 2, 2, 3, 3, 4, 4, 5, 5};
//        System.out.println(s16.singleNumber(nums));


        String s = "catsandog";
        List<String> wordDict = new ArrayList<>(Arrays.asList("cats", "dog", "sand", "and", "cat"));
        System.out.println(s16.wordBreak(s, wordDict));
    }
}
