package org.wq.leetcode.day7;

import org.wq.leetcode.common.ListNode;

import java.util.HashSet;

public class Solution7 {
    // 环形链表1
    // 只判断链表里是否有环形
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return false;
        }


        ListNode p = head;
        ListNode q = head.next.next;

        while (q != p) {
            if (q.next == null || q.next.next == null) {
                return false;
            }

            p = p.next;
            q = q.next.next;
        }

        return true;
    }

    // 环形链表2
    // 判断链表里是否有环形,并返回第一个循环节点
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }

        ListNode slow = head.next;
        ListNode fast = head.next.next;
        while (slow != fast) {
            if (fast.next == null || fast.next.next == null) {
                return null;
            }
            slow = slow.next;
            fast = fast.next.next;

        }

        fast = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }

        return slow;
    }


    // 判断交叉链表
    // 使用额外空间
    public ListNode getIntersectionNode1(ListNode headA, ListNode headB) {

        if (headA == null || headB == null) {
            return null;
        }

        HashSet<ListNode> set = new HashSet<>();

        ListNode p = headA;
        while (p != null) {
            set.add(p);
            p = p.next;
        }

        ListNode q = headB;

        while (q != null) {
            if (set.contains(q)) {
                return q;
            }
            q = q.next;
        }
        return null;
    }

    // 判断交叉链表
    // 不使用额外空间
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {

        if (headA == null || headB == null) {
            return null;
        }

        // 1、获取两个链表的尾节点。不相同则没有交集
        ListNode end1 = headA;
        ListNode end2 = headB;
        int length = 0;

        while (end1.next != null) {
            end1 = end1.next;
            length++;
        }


        while (end2.next != null) {
            end2 = end2.next;
            length--;
        }

        if (end1 != end2) {
            return null;
        }

        // 2、说明有交集
        end1 = length > 0 ? headA : headB;
        end2 = end1 == headA ? headB : headA;

        length = Math.abs(length);

        while (length != 0){
            end1 = end1.next;
            length --;
        }

        while (end1 != end2) {
            end1 = end1.next;
            end2 = end2.next;
        }

        return end1;
    }

    private int getListLength(ListNode head) {
        ListNode p = head;
        int count = 0;
        while (p != null) {
            count++;
            p = p.next;
        }
        return count;
    }

}
