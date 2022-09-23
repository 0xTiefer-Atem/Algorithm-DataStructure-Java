package org.wq.leetcode.round1.day35;

import org.wq.leetcode.common.ListNode;

import java.util.HashMap;

public class Solution35 {
    // 567. 字符串的排列
    public boolean checkInclusion(String s1, String s2) {
        HashMap<Character, Integer> need = new HashMap<>();
        HashMap<Character, Integer> window = new HashMap<>();
        for (Character c : s1.toCharArray()) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }

        int left = 0;
        int right = 0;
        int valid = 0;
        while (right < s2.length()) {

            Character rc = s2.charAt(right);
            right++;

            if (need.containsKey(rc)) {
                window.put(rc, window.getOrDefault(rc, 0) + 1);
                if (need.get(rc).equals(window.get(rc))) {
                    valid++;
                }
            }

            while (right - left >= need.size()) {
                if (valid == need.size()) {
                    return true;
                }
                Character lc = s2.charAt(left);
                left++;
                if (need.containsKey(lc)) {
                    window.put(lc, window.getOrDefault(lc, 0) - 1);
                    if (need.get(lc).equals(window.get(lc))) {
                        valid--;
                    }
                }
            }

        }
        return false;
    }

    // 5. 最长回文子串
    public String longestPalindrome(String s) {
        String res = "";
        for (int i = 0; i < s.length(); i++) {
            String sub1 = process(s, i, i);
            String sub2 = process(s, i, i + 1);

            res = sub1.length() > res.length() ? sub1 : res;
            res = sub2.length() > res.length() ? sub2 : res;

        }

        return res;
    }

    private String process(String s, int l, int r) {
        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
            l--;
            r++;
        }
        return s.substring(l + 1, r);
    }

    // 26. 删除有序数组中的重复项
    public int removeDuplicates(int[] nums) {
        int slow = 0;
        int fast = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[slow] != nums[fast]) {
                slow++;
                nums[slow] = nums[fast];
            }
            fast++;
        }
        return slow;
    }

    // 83. 删除排序链表中的重复元素
    public ListNode deleteDuplicates(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null) {
            if (slow.val != fast.val) {
                slow.next = fast;
                slow = slow.next;
            }

            fast = fast.next;
        }
        slow.next = null;
        return head;
    }

    public static void main(String[] args) {
        Solution35 solution35 = new Solution35();


//        System.out.println(solution35.checkInclusion("ab", "eidbaooo"));
        System.out.println(solution35.longestPalindrome("babad"));

    }
}
