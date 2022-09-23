package org.wq.leetcode.round1.day6;

import org.wq.leetcode.common.ListNode;
import org.wq.leetcode.common.Node;

import java.util.HashMap;
import java.util.Stack;

public class Solution6 {
    //反转单向列表
    public static ListNode reserveList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode pre = null;
        ListNode cur = head;

        while (cur != null) {
            ListNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }

        return pre;
    }

    // 判断链表是否为回文链表
    // 使用额外空间进行判断
    public boolean isPalindrome1(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }

        ListNode p = head;
        Stack<ListNode> stack = new Stack<>();

        while (p != null) {
            stack.push(p);
            p = p.next;
        }

        ListNode q = head;
        while (!stack.empty()) {
            ListNode pop = stack.pop();
            if (q.val != pop.val) {
                return false;
            }
            q = q.next;
        }

        return true;

    }

    // 使用快慢指针进行判断
    public boolean isPalindrome2(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }



        return false;
    }

    // 复制带随机指针的链表
    // 使用哈希表实现
    public Node copyRandomList1(Node head) {
        HashMap<Node, Node> map = new HashMap<>();
        Node p = head;

        while (p!= null) {
            map.put(p, new Node(p.val));
            p = p.next;
        }

        Node q= head;
        while (q != null) {
            map.get(q).random = map.get(q.random);
            map.get(q).next = map.get(q.next);
            q =q.next;
        }

        return map.get(head);
    }

    // 复制带随机指针的链表
    // 不借助额外空间实现
    public Node copyRandomList2(Node head) {
        Node p = head;
        while (p != null) {
            Node tempNode = new Node(p.val);
            tempNode.next = p.next;
            p.next = tempNode;
            p = p.next.next;
        }

        p = head;
        Node q = head.next;
        while (q.next != null) {
            q.random = p.random == null ? null: p.random.next;
            q = q.next.next;
            p = p.next.next;
        }

        p = head.next;

        while (p.next != null) {
            Node temp = p.next.next;
            p.next = p.next.next;
            p = temp;
        }


        Node res = head.next;
        head.next = null;

        return res;

    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(4);


        l1.next = l2;
        l2.next = l3;
        l3.next = l4;


        ListNode res = reserveList(l1);

        System.out.println(res);
    }
}
