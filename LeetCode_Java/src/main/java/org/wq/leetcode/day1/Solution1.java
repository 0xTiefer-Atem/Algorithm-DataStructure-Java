package org.wq.leetcode.day1;


import org.wq.leetcode.common.ListNode;
import org.wq.leetcode.common.TreeNode;

import java.util.*;

/**
 * @author： WangQian
 * @date： 2020/9/15 下午 4:57
 */
public class Solution1 {


    /**
     * 2. 两数相加
     * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
     * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
     * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     * 示例：
     * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
     * 输出：7 -> 0 -> 8
     * 原因：342 + 465 = 807
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int count1 = countNodeNum(l1);
        int count2 = countNodeNum(l2);
        ListNode p = l1;
        ListNode q = l2;
        ListNode tempListNode;
        if (count1 > count2) {
            //说明l1更长
            while (q != null) {
                p.val = p.val + q.val;
                q = q.next;
                p = p.next;
            }
            tempListNode = l1;
        } else {
            //l2或者两者一样长
            while (p != null) {
                q.val = p.val + q.val;
                p = p.next;
                q = q.next;
            }
            tempListNode = l2;
        }
        ListNode h = tempListNode;
        while (h.next != null) {
            if (h.val > 9) {
                h.next.val = h.next.val + h.val / 10;
                h.val = h.val % 10;
            }
            h = h.next;
        }
        if (h.val > 9) {
            ListNode tempNode = new ListNode(1);
            h.val = h.val % 10;
            h.next = tempNode;
        }
        return tempListNode;
    }

    private int countNodeNum(ListNode listNode) {
        int count = 0;
        ListNode listNode1 = listNode;
        while (listNode1 != null) {
            count++;
            listNode1 = listNode1.next;
        }
        return count;
    }


    /**
     * 58. 最后一个单词的长度
     * 给定一个仅包含大小写字母和空格 ' ' 的字符串 s，返回其最后一个单词的长度。如果字符串从左向右滚动显示，
     * 那么最后一个单词就是最后出现的单词。
     * 如果不存在最后一个单词，请返回 0 。
     * 说明：一个单词是指仅由字母组成、不包含任何空格字符的 最大子字符串。
     * 示例:
     * 输入: "Hello World"
     * 输出: 5
     */

    public int lengthOfLastWord(String s) {
        String[] strA = s.split(" ");
        if (strA.length == 0) {
            return 0;
        }
        String s1 = strA[strA.length - 1];
        if (" ".equals(s1)) {
            return 0;
        }
        return s1.toCharArray().length;
    }

    /**
     * 66. 加一
     * 给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。
     * <p>
     * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
     * <p>
     * 你可以假设除了整数 0 之外，这个整数不会以零开头。
     * <p>
     * 示例 1:
     * 输入: [1,2,3]
     * 输出: [1,2,4]
     * 解释: 输入数组表示数字 123。
     * <p>
     * 示例 2:
     * 输入: [4,3,2,1]
     * 输出: [4,3,2,2]
     * 解释: 输入数组表示数字 4321。
     */

    public int[] plusOne(int[] digits) {
        int dlength = digits.length;
        if (dlength == 0) {
            return digits;
        }
        int[] resDigits = new int[dlength + 1];
        for (int i = 1; i < resDigits.length; i++) {
            resDigits[i] = digits[i - 1];
        }
        int length = resDigits.length;
        resDigits[length - 1] = resDigits[length - 1] + 1;
        for (int i = resDigits.length - 1; i >= 1; i--) {
            int tempNum = resDigits[i];
            if (tempNum > 9) {
                resDigits[i] = tempNum % 10;
                int j = i - 1;
                resDigits[j] = resDigits[j] + 1;
            }
        }
        if (resDigits[0] == 0) {
            for (int i = 1; i < resDigits.length; i++) {
                digits[i - 1] = resDigits[i];
            }
            resDigits = digits;
        }
        return resDigits;
    }


    /**
     * 7. 整数反转
     * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
     * <p>
     * 示例 1:
     * <p>
     * 输入: 123
     * 输出: 321
     *  示例 2:
     * <p>
     * 输入: -123
     * 输出: -321
     * 示例 3:
     * <p>
     * 输入: 120
     * 输出: 21
     */

    public int reverse(int x) {
        if (0 < x && x < 9) {
            return x;
        }
        boolean isNegative = false;
        if (x < 0) {
            isNegative = true;
            x = Math.abs(x);
        }
        String strX = x + "";
        char[] chartX = strX.toCharArray();
        int[] res = new int[chartX.length];
        for (int i = chartX.length - 1; i >= 0; i--) {
            res[chartX.length - i - 1] = chartX[i] - '0';
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < res.length; i++) {
            sb.append(res[i]);
        }
        int sum = 0;
        try {
            sum = Integer.parseInt(sb.toString());
        } catch (Exception e) {
            return 0;
        }
        if (isNegative) {
            return -sum;
        }
//        try {
//            Integer.parseInt()
//        }
        return sum;
    }

    /**
     * 61. 旋转链表
     * 给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数。
     * <p>
     * 示例 1:
     * <p>
     * 输入: 1->2->3->4->5->NULL, k = 2
     * 输出: 4->5->1->2->3->NULL
     * 解释:
     * 向右旋转 1 步: 5->1->2->3->4->NULL
     * 向右旋转 2 步: 4->5->1->2->3->NULL
     * 示例 2:
     * <p>
     * 输入: 0->1->2->NULL, k = 4
     * 输出: 2->0->1->NULL
     * 解释:
     * 向右旋转 1 步: 2->0->1->NULL
     * 向右旋转 2 步: 1->2->0->NULL
     * 向右旋转 3 步: 0->1->2->NULL
     * 向右旋转 4 步: 2->0->1->NULL
     */

    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        //转成int数组
        ListNode p = head;
        int length = 0;
        while (p != null) {
            length++;
            p = p.next;
        }
        if (length == 1) {
            return head;
        }
        int[] valArray = new int[length];
        int index = 0;
        ListNode n1 = head;
        while (n1 != null) {
            valArray[index] = n1.val;
            n1 = n1.next;
            index++;
        }
        int[] tempValArray = new int[length];
        for (int i = 0; i < length; i++) {
            int indexVal = (i + k) % length;
            int temp = valArray[i];
            tempValArray[indexVal] = temp;
        }
        ListNode resHead = new ListNode();
        ListNode tail = resHead;
        for (int i = 0; i < length; i++) {
            ListNode tempNode = new ListNode(tempValArray[i]);
            tail.next = tempNode;
            tail = tempNode;
        }
        return resHead.next;
    }

    /**
     * 144. 二叉树的前序遍历
     * 给定一个二叉树，返回它的 前序 遍历。
     *
     * 示例:
     *
     * 输入: [1,null,2,3]
     *    1
     *     \
     *      2
     *     /
     *    3
     *
     * 输出: [1,2,3]
     * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
     * */
    //递归
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> nodeList = new ArrayList<Integer>();
        nodeList = preorderTraversalNode1(root, nodeList);
        return nodeList;
    }

    //递归方法
    public List<Integer> preorderTraversalNode1(TreeNode treeNode, List<Integer> list) {
        if(treeNode != null) {
            //根
            list.add(treeNode.val);
            //左
            preorderTraversalNode1(treeNode.left, list);
            //右
            preorderTraversalNode1(treeNode.right, list);
        }
        return list;
    }

    //非递归方法，利用栈方法
    public List<Integer> preorderTraversalNode2(TreeNode root) {
        if (root == null) {
            return new ArrayList<Integer>();
        }
        List<Integer> list = new ArrayList<Integer>();
        Stack<TreeNode> treeNodeStack = new Stack<TreeNode>();
        treeNodeStack.push(root);
        while (!treeNodeStack.isEmpty()){
            TreeNode node = treeNodeStack.pop();
            list.add(node.val);
            if (node.right != null){
                treeNodeStack.push(root.right);
            }
            if (node.left != null){
                treeNodeStack.push(root.left);
            }
        }
        return list;
    }

    /**
     * 94. 二叉树的中序遍历
     * 示例:
     *
     * 输入: [1,null,2,3]
     *    1
     *     \
     *      2
     *     /
     *    3
     *
     * 输出: [1,3,2]
     * */

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        res = inorderTraversal1(root, res);
        return res;
    }

    public List<Integer> inorderTraversal1(TreeNode root, List<Integer> resList) {
        if(root != null) {
            inorderTraversal1(root.left, resList);
            resList.add(root.val);
            inorderTraversal1(root.right, resList);
        }
        return resList;
    }
}
