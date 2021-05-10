package org.wq.leetcode;

import org.openjdk.jol.info.ClassLayout;
import org.wq.leetcode.common.ListNode;
import org.wq.leetcode.common.TreeNode;
import org.wq.leetcode.day1.Solution1;
import org.wq.leetcode.day2.BSTIterator;
import org.wq.leetcode.day2.Solution2;

import java.util.List;

/**
 * @author： WangQian
 * @date： 2020/9/15 下午 4:56
 */
public class Test {
    public static void main(String[] args) {
        Solution1 solution1 = new Solution1();
        Solution2 solution2 = new Solution2();

//        ListNode l1 = new ListNode(2);
//        ListNode l3 = new ListNode(4);
//        ListNode l5 = new ListNode(8);
//        l1.next = l3;
//        l3.next = l5;
//
//        ListNode l2 = new ListNode(5);
//        ListNode l4 = new ListNode(6);
//        ListNode l6 = new ListNode(4);
//        l2.next = l4;
//        l4.next = l6;
//
//        ListNode res = solution.addTwoNumbers(l1, l2);

//        System.out.println(solution.lengthOfLastWord(" "));
//        int[] digits = {9,8};
//        System.out.println(Arrays.toString(solution.plusOne(digits)));
//        System.out.println(solution.reverse(1534236469));
//        ListNode res = solution.rotateRight(l1,2);
//        ListNode p = res;
//        while (p != null){
//            System.out.print(p.val + " ");
//            p = p.next;
//        }

//        TreeNode t1 = new TreeNode(1);
//        TreeNode t2 = new TreeNode(2);
//        TreeNode t3 = new TreeNode(3);
//        TreeNode t4 = new TreeNode(4);
//        t1.left = t4;
//        t1.right = t3;
//        t4.left = t2;
//        List<Integer> res = solution.preorderTraversalNode2(t1);
//        for (Integer i:res) {
//            System.out.print(i + " ");
//        }

//        String str = "a good   example";
//        System.out.println(solution2.reverseWords(str) + "====");
//        System.out.println("example good a".equals(solution2.reverseWords(str)));
//        int[] nums = {1, 2, 3, 4, 5, 6, 7};
//        System.out.println(solution2.findPeakElement(nums));
//        solution2.rotate(nums, 3);

//        TreeNode t3 = new TreeNode(3);
//        TreeNode t7 = new TreeNode(7);
//        TreeNode t9 = new TreeNode(9);
//        TreeNode t15 = new TreeNode(15);
//        TreeNode t20 = new TreeNode(20);
//        t7.left = t3;
//        t7.right = t15;
//        t15.left = t9;
//        t15.right = t20;
//
//        BSTIterator iterator = new BSTIterator(t7);
//        System.out.println(iterator.next());    // 返回 3
//        System.out.println(iterator.next());    // 返回 7
//        System.out.println(iterator.hasNext()); // 返回 true
//        System.out.println(iterator.next());    // 返回 9
//        System.out.println(iterator.hasNext()); // 返回 true
//        System.out.println(iterator.next());    // 返回 15
//        System.out.println(iterator.hasNext()); // 返回 true
//        System.out.println(iterator.next());    // 返回 20
//        System.out.println(iterator.hasNext()); // 返回 false

        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(4);
        t1.left = t2;
        t1.right = t3;
        t2.right = t5;
        t3.right = t4;
        List<Integer> res = solution2.rightSideView(t1);
        for (Integer i : res) {
            System.out.print(i + " ");
        }
    }
}
