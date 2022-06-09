package org.wq.leetcode.day12;

import org.wq.leetcode.common.ListNode;
import org.wq.leetcode.common.TreeNode;

import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

public class Solution12 {

    // 将给定的数字转化为字符串，原则如下：1对应a，2对应b，。。。26对应z，例如，12258可转化为"abbeh"， "aveh", "abhy", "lbeh"
    // "lyh"， 个数为5个，编写一个函数，给出一个数字能转化成字符串的个数

    public static int num2Letter(int num) {

        if (num == 0) {
            return 0;
        }
        char[] numChars = String.valueOf(num).toCharArray();

        return process1(numChars, 0);
    }

    private static int process1(char[] numChars, int index) {
        if (index == numChars.length) {
            return 1;
        }

        if ('0' == numChars[index]) {
            return 0;
        }

        // 仅当前数字进行转换，可能的结果
        int res = process1(numChars, index + 1);
        if (index + 1 == numChars.length) {
            return res;
        }

        // 当前数字加上后一位数字进行转换的结果
        if (((numChars[index] - '0') * 10 + (numChars[index + 1] - '0')) < 27) {
            res += process1(numChars, index + 2);
        }

        return res;
    }

    public static int maxWeight(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return process2(root);
    }

    private static int process2(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int currValue = root.val;
        currValue += Math.max(process2(root.left), process2(root.right));
        return currValue;

    }

    static class TreeNodeInfo {
        public int val;
    }

    // 顺时针旋转矩阵
    public static void roatateMartix(int[][] m) {
        int x1 = 0;
        int y1 = 0;

        int x2 = m.length - 1;
        int y2 = m[0].length - 1;

        while (x1 < x2) {
            process3(m, x1++, y1++, x2--, y2--);
        }
    }

    private static void process3(int[][] m, int x1, int y1, int x2, int y2) {
        for (int j = 0; j < x1 - x2; j++) {
            int temp = m[x1][y1 + j];
            m[x1][y1 + j] = m[x2 - j][y2];
            m[x2 - j][y2] = m[x2][y2 - j];
            m[x2][y2 - j] = m[x1 + j][y1];
            m[x1 + j][y1] = temp;
        }

    }


    // 两数之和
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> sumMap = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (sumMap.containsKey(target - nums[i])) {
                return new int[]{i, sumMap.get(target - nums[i])};
            }
            sumMap.put(nums[i], i);
        }
        return new int[]{};
    }

    //两数相加
    public ListNode addTowNumbers(ListNode l1, ListNode l2) {
        ListNode dumpyNode = new ListNode(-1);
        ListNode curr = dumpyNode;

        // 保存进位的值
        int temp = 0;

        while (l1 != null || l2 != null || temp != 0) {
            int val1 = l1 == null ? 0 : l1.val;
            int val2 = l2 == null ? 0 : l2.val;
            temp += val1;
            temp += val2;
            curr.next = new ListNode(temp % 10);
            temp /= 10;
            curr = curr.next;
            if (l1 != null) {
                l1 = l1.next;
            }

            if (l2 != null) {
                l2 = l2.next;
            }

        }
        return dumpyNode.next;

    }

    // 无重复字符的最长字串
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int left = 0;
        int right = 0;
        int res = 0;
        while (right < s.length()) {
            Character c = s.charAt(right++);
            map.put(c, map.getOrDefault(c, 0) + 1);
            while (map.get(c) > 1) {
                Character l = s.charAt(left++);
                map.put(l, map.getOrDefault(l, 0) - 1);
            }
            res = Math.max(res, right - left);
        }
        return res;
    }


    // 寻找两个正序数组的中位数
//    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
//
//
//    }

    //最长回文子串
    // 回文算法
    // 1.马拉车算法
    // 2.dp
    // 3.枚举
    // 4.字符串哈希
    public String longestPalindrome(String s) {
        String ans = "";
        for (int i = 0; i < s.length(); i++) {
            //奇回文
            int l = i - 1;
            int r = i + 1;
            while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
                l--;
                r++;
            }
            if (ans.length() < (r - l - 1)) {
                ans = s.substring(l + 1, r);
            }

            //偶回文
            l = i;
            r = i + 1;
            while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
                l--;
                r++;
            }
            if (ans.length() < (r - l - 1)) {
                ans = s.substring(l + 1, r);
            }

        }
        return ans;
    }

    // 10. 正则表达式匹配
//    public boolean isMatch(String s, String p) {
//
//    }

    // 11. 乘最多水的容器
    public int maxArea(int[] height) {
        int res = 0;
        for (int left = 0, right = height.length - 1; left < right; ) {
            res = Math.max(res, (right - left) * Math.min(height[left], height[right]));
            if (height[left] > height[right]) {
                right--;
            } else {
                left++;
            }
        }
        return res;
    }

    //12.三数之和
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            for (int j = i + 1, k = nums.length - 1; j < k; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                while (j < k - 1 && nums[i] + nums[j] + nums[k - 1] >= 0) {
                    k--; // 这里相当于一个探测过程
                }
                if (nums[i] + nums[j] + nums[k] == 0) {
                    res.add(Arrays.asList(nums[i], nums[j], nums[k]));
                }
            }
        }

        return res;
    }

    static Map<Character, String> phoneMap = new HashMap<Character, String>() {{
        put('2', "abc");
        put('3', "def");
        put('4', "ghi");
        put('5', "jkl");
        put('6', "mno");
        put('7', "pqrs");
        put('8', "tuv");
        put('9', "wxyz");
    }};

    //17. 电话号码的字母组合
    static List<String> res;

    public List<String> letterCombinations(String digits) {

        res = new ArrayList<>();

        if (digits.length() == 0) {
            return res;
        }

        StringBuilder sb = new StringBuilder();

        process4(digits, sb, 0);

        return res;
    }

    private static void process4(String digits, StringBuilder sb, int index) {
        if (index == digits.length()) {
            res.add(sb.toString());
            return;
        }

        String s = phoneMap.get(digits.charAt(index));
        StringBuilder s1 = new StringBuilder();
        s1.append(sb);
        for (int i = 0; i < s.length(); i++) {
            StringBuilder s2 = new StringBuilder(s1.toString());
            process4(digits, s2.append(s.charAt(i)), index + 1);
        }
    }

    //19. 删除链表的倒数第 N 个结点
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode fast = head;
        ListNode slow = head;
        ListNode pre = null;
        for (int i = 0; i < n - 1; i++) {
            fast = fast.next;
        }

        while (fast != null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next;
        }

        // 边界条件判断
        if (fast == head) {
            return null;
        }
        if (slow == head) {
            return slow.next;
        }

        if (pre != null) {
            pre.next = slow.next;
        }

        return head;
    }

    // 20. 有效的括号
    // 使用栈结果进行操作
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<Character>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if ('(' == s.charAt(i) || '[' == s.charAt(i) || '{' == s.charAt(i)) {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                } else if (stack.peek() == '(' && c == ')') {
                    stack.pop();
                } else if (stack.peek() == '[' && c == ']') {
                    stack.pop();
                } else if (stack.peek() == '{' && c == '}') {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    // 21. 合并两个有序链表
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummyNode = new ListNode(-1);
        ListNode curr = dummyNode;
        ListNode curr1 = list1;
        ListNode curr2 = list2;

        while (curr1 != null && curr2 != null) {
            int val = curr1.val;
            if (val > curr2.val) {
                val = curr2.val;
                curr2 = curr2.next;
            } else {
                curr1 = curr1.next;
            }
            curr.next = new ListNode(val);
            curr = curr.next;

        }

        while (curr1 != null) {
            curr.next = new ListNode(curr1.val);
            curr = curr.next;
            curr1 = curr1.next;
        }

        while (curr2 != null) {
            curr.next = new ListNode(curr2.val);
            curr = curr.next;
            curr2 = curr2.next;
        }


        return dummyNode.next;
    }

    // 22. 括号生成
    List<String> res1 = new ArrayList<>();

    public List<String> generateParenthesis(int n) {

        process5(n, 0, 0, new StringBuilder());

        return res1;
    }

    private void process5(int n, int left, int right, StringBuilder s) {
        if (left == n && right == n) {
            res1.add(new StringBuilder(s).toString());
            return;
        }

        if (left < n) {
            s.append("(");
            process5(n, left + 1, right, s);
            s.deleteCharAt(s.length() - 1);
        }

        if (right < left) {
            s.append(")");
            process5(n, left, right + 1, s);
            s.deleteCharAt(s.length() - 1);
        }

    }


    // 23. 合并K个升序链表

    /**
     * 多路归并
     */

    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> queue = new PriorityQueue<>(Comparator.comparingInt(x -> x.val));
        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null) {
                queue.offer(lists[i]);
            }
        }


        ListNode dummyNode = new ListNode(-1);
        ListNode curr = dummyNode;
        while (!queue.isEmpty()) {
            ListNode top = queue.poll();
            curr.next = new ListNode(top.val);
            curr = curr.next;
            ListNode next = top.next;
            if (next != null) {
                queue.offer(next);
            }
        }
        return dummyNode.next;
    }


    public static void main(String[] args) {
//        System.out.println(num2Letter(12258));

//        TreeNode node1 = new TreeNode(1);
//        TreeNode node2 = new TreeNode(2);
//        TreeNode node3 = new TreeNode(3);
//        TreeNode node4 = new TreeNode(4);
//
//        node1.left = node2;
//        node1.right = node4;
//        node2.right = node3;
//
//        System.out.println(maxWeight(node1));

        String digits = "23";
//        System.out.println(letterCombinations(digits));

        Solution12 s12 = new Solution12();
        System.out.println(s12.generateParenthesis(3));

    }
}
