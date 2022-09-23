package org.wq.leetcode.round1.day23;

import org.wq.leetcode.common.ListNode;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution23 {

    // 21. 合并两个有序链表
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummyNode = new ListNode(-1);
        ListNode p = dummyNode;
        ListNode p1 = list1;
        ListNode p2 = list2;
        while (p1 != null && p2 != null) {
            if (p1.val < p2.val) {
                p.next = p1;
                p1 = p1.next;
            } else {
                p.next = p2;
                p2 = p2.next;
            }

            p = p.next;
        }

        if (p1 != null) {
            p.next = p1;
        }

        if (p2 != null) {
            p.next = p2;
        }

        return dummyNode.next;
    }

    // 86. 分隔链表
    public ListNode partition(ListNode head, int x) {
        // 存放小于x的节点
        ListNode dummyNode1 = new ListNode(-1);

        // 存放大于x的节点
        ListNode dummyNode2 = new ListNode(-1);
        ListNode p1 = dummyNode1;
        ListNode p2 = dummyNode2;
        ListNode q = head;
        while (q != null) {
            if (q.val < x) {
                p1.next = q;
                p1 = p1.next;
            } else {
                p2.next = q;
                p2 = p2.next;
            }
            ListNode tempNode = q.next;
            q.next = null;
            q = tempNode;
        }

        p1.next = dummyNode2.next;
        return dummyNode1.next;
    }

    // 23. 合并K个升序链表
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) return null;
        PriorityQueue<ListNode> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.val));
        for (ListNode listNode : lists) {
            if (listNode != null) {
                queue.offer(listNode);
            }
        }

        ListNode dummyNode = new ListNode(-1);
        ListNode p = dummyNode;
        while (!queue.isEmpty()) {
            ListNode node = queue.poll();
            p.next = node;
            p = p.next;
            if (node.next != null) {
                queue.offer(node.next);
            }
        }

        return dummyNode.next;
    }


    // 剑指 Offer 22. 链表中倒数第k个节点
    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode p1 = head;
        for (int i = 0; i < k; i++) {
            p1 = p1.next;
        }

        ListNode p2 = head;
        while (p1 != null) {
            p1 = p1.next;
            p2 = p2.next;
        }

        return p2;
    }


    // 19. 删除链表的倒数第 N 个结点
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        ListNode p = dummyNode;
        for (int i = 0; i <= n; i++) {
            p = p.next;
        }

        ListNode q = dummyNode;

        while (p != null) {
            p = p.next;
            q = q.next;
        }

        q.next = q.next.next;
        return dummyNode.next;
    }

    // 876. 链表的中间结点
    public ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        return slow;
    }

    // 141. 环形链表
    public boolean hasCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    // 142. 环形链表 II
    public ListNode detectCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (slow == fast) {
                break;
            }
        }

        if (fast == null || fast.next == null) {
            // fast 遇到空指针说明没有环
            return null;
        }

        slow = head;
        while (fast != slow) {
            slow = slow.next;
            fast = fast.next;
        }

        return slow;
    }

    // 160. 相交链表
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode p = headA;

        ListNode q = headB;

        int count = 1;

        while (p.next != null) {
            p = p.next;
            count++;
        }

        while (q.next != null) {
            q = q.next;
            count--;
        }

        if (p != q) {
            return null;
        }

        ListNode lNode = count > 0 ? headA : headB;
        ListNode sNode = lNode == headA ? headB : headA;

        count = Math.abs(count);

        while (count != 0) {
            lNode = lNode.next;
            count--;
        }

        while (lNode != sNode) {
            lNode = lNode.next;
            sNode = sNode.next;
        }

        return sNode;
    }


    // 剑指 Offer II 078. 合并排序链表
    public ListNode mergeKLists1(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }
        PriorityQueue<ListNode> queue = new PriorityQueue<>(Comparator.comparingInt(n -> n.val));

        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null) {
                queue.offer(lists[i]);
            }
        }

        ListNode dummyNode = new ListNode(-1);
        ListNode p = dummyNode;


        while (!queue.isEmpty()) {
            ListNode tempNode = queue.poll();
            p.next = tempNode;
            p = p.next;
            if (tempNode.next != null) {
                queue.offer(tempNode.next);
            }
        }

        return dummyNode.next;

    }

    // 剑指 Offer 25. 合并两个排序的链表
    public ListNode mergeTwoLists1(ListNode l1, ListNode l2) {
        ListNode dummyNode = new ListNode(-1);
        ListNode q = dummyNode;
        ListNode p1 = l1;
        ListNode p2 = l2;

        while (p1 != null && p2 != null) {
            if (p1.val < p2.val) {
                q.next = p1;
                p1 = p1.next;
            } else {
                q.next = p2;
                p2 = p2.next;
            }
            q = q.next;
        }

        if (p1 != null) {
            q.next = p1;
        }

        if (p2 != null) {
            q.next = p2;
        }

        return dummyNode.next;
    }

    // 面试题52. 两个链表的第一个公共节点
    public ListNode getIntersectionNode1(ListNode headA, ListNode headB) {

        if (headA == null || headB == null) {
            return null;
        }

        ListNode l1 = headA;
        ListNode l2 = headB;

        int count = 0;
        while (l1.next != null) {
            l1 = l1.next;
            count++;
        }

        while (l2.next != null) {
            l2 = l2.next;
            count--;
        }

        if (l1 != l2) {
            return null;
        }

        l1 = count > 0 ? headA : headB;
        l2 = l1 == headA ? headB : headA;

        count = Math.abs(count);
        while (count != 0) {
            l1 = l1.next;
            count--;
        }

        while (l1 != l2) {
            l1 = l1.next;
            l2 = l2.next;
        }

        return l1;
    }


    // 剑指 Offer II 021. 删除链表的倒数第 n 个结点
    public ListNode removeNthFromEnd1(ListNode head, int n) {
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        ListNode p1 = dummyNode;
        for (int i = 0; i <= n; i++) {
            p1 = p1.next;
        }

        ListNode p2 = dummyNode;

        while (p1 != null) {
            p2 = p2.next;
            p1 = p1.next;
        }
        p2.next = p2.next.next;

        return dummyNode.next;
    }

    // 剑指 Offer II 022. 链表中环的入口节点
    public ListNode detectCycle1(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (slow == fast) {
                break;
            }
        }

        if (fast == null || fast.next == null) {
            // fast 遇到空指针说明没有环
            return null;
        }

        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    // 剑指 Offer II 023. 两个链表的第一个重合节点
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        ListNode l = headA;
        int count = 0;
        while (l.next != null) {
            l = l.next;
            count++;
        }

        ListNode s = headB;
        while (s.next != null) {
            s = s.next;
            count--;
        }

        if (s != l) {
            return null;
        }

        l = count > 0 ? headA : headB;
        s = l == headA ? headB : headA;
        count = Math.abs(count);

        while (count != 0) {
            l = l.next;
            count--;
        }

        while (l != s) {
            l = l.next;
            s = s.next;
        }

        return s;
    }

    // 206. 反转链表
    // 递归
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode last = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return last;
    }

    // 反转前n个节点
    ListNode successorNode = null;

    public ListNode reverseSubList(ListNode head, int n) {
        if (n == 1) {
            successorNode = head.next;
            return head;
        }
        ListNode last = reverseSubList(head.next, n - 1);
        head.next.next = head;
        head.next = successorNode;
        return last;
    }

    // 92. 反转链表 II
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if(left == 1) {
            return reverseSubList(head, right);
        }
        head.next = reverseBetween(head.next, left-1, right-1);
        return head;
    }


    public static void main(String[] args) {
        Solution23 s23 = new Solution23();
        ListNode n1 = new ListNode(-1);
        ListNode n2 = new ListNode(1);
        ListNode n3 = new ListNode(2);
        ListNode n4 = new ListNode(3);
        ListNode n5 = new ListNode(4);
        ListNode n6 = new ListNode(5);

        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;


        s23.reverseSubList(n1, 9);


        System.out.println(n1.val);
    }
}
