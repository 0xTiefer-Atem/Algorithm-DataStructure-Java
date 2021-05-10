package org.wq.leetcode.common;

/**
 * @author： WangQian
 * @date： 2020/9/15 下午 4:57
 */
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
