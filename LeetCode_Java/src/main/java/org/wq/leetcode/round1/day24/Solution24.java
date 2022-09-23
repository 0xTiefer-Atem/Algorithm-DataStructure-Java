package org.wq.leetcode.round1.day24;

import org.wq.leetcode.common.ListNode;

public class Solution24 {
    // 26. 删除有序数组中的重复项
    // 快慢指针思想
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) return -1;

        int slow = 0;
        int fast = 0;
        while (fast < nums.length) {
            if (nums[slow] != nums[fast]) {
                slow++;
                nums[slow] = nums[fast];
            }
            fast++;
        }

        return slow + 1;
    }

    // 27. 移除元素
    // 快慢指针思想
    public int removeElement(int[] nums, int val) {
        if (nums.length == 0) return -1;

        int slow = 0;
        int fast = 0;
        while (fast < nums.length) {
            if (nums[fast] != val) {
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }

        return slow;
    }

    // 83. 删除排序链表中的重复元素
    // 快慢指针思想
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) return null;

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

    // 283. 移动零
    // 快慢指针思想
    public void moveZeroes(int[] nums) {
        if (nums.length == 0) {
            return;
        }

        int slow = 0;
        int fast = 0;

        while (fast < nums.length) {
            if (nums[fast] != 0) {
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }

        for (int i = slow; i < nums.length; i++) {
            nums[i] = 0;
        }
    }

    // 167. 两数之和 II - 输入有序数组
    // 左右指针
    public int[] twoSum(int[] numbers, int target) {
        if (numbers.length < 2) {
            return new int[]{-1, -1};
        }

        int left = 0;
        int right = numbers.length - 1;
        while (left < right) {
            int val = numbers[left] + numbers[right];
            if (val == target) {
                return new int[]{left + 1, right + 1};
            }
            if (val > target) {
                right--;
            }

            if (val < target) {
                left++;
            }
        }

        return new int[]{-1, -1};
    }

    // 344. 反转字符串
    public void reverseString(char[] s) {
        int left = 0;
        int right = s.length - 1;
        while (left < right) {
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            left++;
            right--;
        }
    }

    // 5. 最长回文子串
    // 左右指针思想
    public String longestPalindrome(String s) {
        String res = "";
        for (int i = 0; i < s.length(); i++) {
            String s1 = palindrome(s, i, i);

            String s2 = palindrome(s, i, i + 1);

            res = res.length() > s1.length() ? res : s1;
            res = res.length() > s2.length() ? res : s2;
        }
        return res;
    }

    private String palindrome(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }

        return s.substring(left + 1, right);
    }


    public static void main(String[] args) {
        Solution24 s24 = new Solution24();

        String babad = s24.longestPalindrome("babad");
        System.out.println(babad);
    }
}
