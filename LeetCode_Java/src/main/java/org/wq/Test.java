package org.wq;

import org.wq.leetcode.common.ListNode;

import java.util.ArrayList;
import java.util.LinkedList;

public class Test {
    public static void main(String[] args) {

    }

    private static void doSomething(Integer v2) {
        v2 = new Integer(2);
    }

    public ListNode addInList(ListNode head1, ListNode head2) {
        head1 = reserveList(head1);
        head2 = reserveList(head2);

        ListNode p = head1;
        ListNode q = head2;
        ListNode temp = new ListNode(0);
        ListNode curr = temp;
        int carry = 0;
        while (p != null || q != null) {
            int x = p == null ? 0 : p.val;
            int y = q == null ? 0 : q.val;

            int sum = carry + x + y;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;

            if (p != null) {
                p = p.next;
            }

            if (q != null) {
                q = q.next;
            }

        }

        if (carry > 0) {
            curr.next = new ListNode(carry);
        }
        return reserveList(temp.next);
    }

    private ListNode reserveList(ListNode head) {
        ListNode last = head;
        ListNode cur = head.next;
        ListNode temp = null;

        while (cur != null) {
            temp = cur.next;
            cur = cur.next;
            temp.next = head;
            head = temp;
        }
        last.next = null;
        return head;
    }

    public ArrayList<Integer> maxInWindows(int[] num, int size) {
        int[] result = new int[num.length];
        LinkedList<Integer> maxList = new LinkedList<>();
        int rIndex = 0;
        for (int i = 0; i < num.length; i++) {
            rIndex = i - size + 1;
            if (rIndex > 0 && maxList.getFirst() < rIndex) {
                maxList.removeFirst();
            }

            int j = maxList.size() - 1;
            while (j >= 0 && num[maxList.get(j)] < num[i]) {
                maxList.remove(j);
                j--;
            }
            maxList.add(i);
            if (rIndex > 0) {
                result[rIndex] = num[maxList.getFirst()];
            }
        }

        ArrayList<Integer> res = new ArrayList<>();

        for (int i : result) {
            res.add(i);
        }
        return res;
    }

    public static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (i == 2) {
                break;
            }
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        System.out.println("经过两轮冒泡排序的结果:");
        for (int j : arr) {
            System.out.print(j + " ");
        }
    }
}


