package org.wq.leetcode.round2.day3;

import org.wq.leetcode.common.ListNode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class Solution3 {

    // 121. 买卖股票的最佳时机
//    public int maxProfit(int[] prices) {
//        int n = prices.length;
//    }


    public static void main(String[] args) {

//        ListNode list = createList();
//
//        ListNode listNode = reserveList(list);
//
//        StringBuilder res = new StringBuilder();
//        res.append("{");
//        while (listNode != null) {
//            res.append(listNode.val).append(",");
//            listNode = listNode.next;
//        }
//        res.deleteCharAt(res.length() - 1);
//        res.append("}");
//
//        System.out.println(res);

        diffSet();

    }

    private static ListNode createList() {
        Scanner in = new Scanner(System.in);
        String listString = in.nextLine().trim();

        listString = listString.replace("{", "").replace("}", "");

        ListNode head = new ListNode();
        ListNode tail = head;
        for (String s : listString.split(",")) {
            ListNode node = new ListNode(Integer.parseInt(s));

            tail.next = node;
            tail = node;
        }

        return head.next;
    }

    public static void diffSet() {
        Scanner in = new Scanner(System.in);
        String sets = in.nextLine().trim();
        String[] setStrArr = sets.split(";");
        int[] a1 = toIntArr(setStrArr[0]);
        int[] a2 = toIntArr(setStrArr[1]);
        int[] res = diffArr(a1, a2);
        System.out.println(Arrays.toString(res));
    }

    private static int[] diffArr(int[] a1, int[] a2) {
        Set<Integer> s1 = new HashSet<Integer>();

        for (int a : a1) {
            s1.add(a);
        }
        Set<Integer> s2 = new HashSet<Integer>();
        for (int a : a2) {
            s2.add(a);
        }


        Set<Integer> collect = s1.stream().filter(s2::contains).collect(Collectors.toSet());
        s1.removeAll(collect);
        s2.removeAll(collect);
        s1.addAll(s2);

        int[] res = new int[s1.size()];

        int i = 0;
        for (Integer as : s1) {
            res[i] = as;
            i++;
        }
        return res;
    }

    public static int[] toIntArr(String setStr) {
        String numStr = setStr.replace("[", "").replace("]", "");
        if (numStr.length() == 0) {
            return new int[0];
        }
        String[] arr = numStr.split(",");
        int[] numArr = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            numArr[i] = Integer.parseInt(arr[i]);
        }
        return numArr;
    }

    public static ListNode reserveList(ListNode head) {
        ListNode p = head;
        ListNode tail = null;

        while (p != null) {
            ListNode next = p.next;

            p.next = tail;

            tail = p;
            p = next;

        }
        return tail;
    }
}
