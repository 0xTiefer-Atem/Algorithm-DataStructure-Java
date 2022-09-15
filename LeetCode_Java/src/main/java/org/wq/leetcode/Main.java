package org.wq.leetcode;

import java.util.*;

class ListNode<T> {
    public T data;
    public ListNode next;
}

class Node {
    public int data;
    public Node left;
    public Node right;

    public Node(int data) {
        this.data = data;
    }

    public Node() {
    }

    public Node(int data, Node left, Node right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }
}

class Solution1 {

    /* Write Code Here */
    public ListNode<Integer> reverseBetween(ListNode<Integer> head, int left, int right) {
        if (left == 1) {

            return reverseN(head, right);
        }

        head.next = reverseBetween(head.next, left - 1, right - 1);
        return head;

    }

    ListNode<Integer> s = null;

    private ListNode<Integer> reverseN(ListNode<Integer> head, int n) {
        if (n == 1) {
            s = head.next;
            return head;
        }
        ListNode<Integer> last = reverseN(head.next, n - 1);
        head.next.next = head;
        head.next = s;
        return last;
    }

    public Node Convert(Node pRootOfTree) {
        ArrayList<Integer> l = new ArrayList<>();
        inorder(pRootOfTree, l);

        if (l.size() == 0) {
            return new Node();
        }

        if (l.size() == 1) {
            return new Node(l.get(0));
        }

        Node head = new Node(l.get(0));
        Node p = head;
        for (int i = 1; i < l.size(); i++) {
            Node temp = new Node(l.get(i));
            temp.left = p;
            p.right = temp;
            p = temp;
        }

        return head;

    }

    private void inorder(Node pRootOfTree, ArrayList<Integer> l) {
        if (pRootOfTree == null) {
            return;
        }

        inorder(pRootOfTree.left, l);
        l.add(pRootOfTree.data);
        inorder(pRootOfTree.right, l);
    }


}

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ListNode<Integer> res = null;

        int head_size = 0;
        head_size = in.nextInt();
        ListNode<Integer> head = null, head_curr = null;
        for(int head_i = 0; head_i < head_size; head_i++) {
            int head_item = in.nextInt();
            ListNode<Integer> head_temp = new ListNode<Integer>();
            head_temp.data = head_item;
            head_temp.next = null;

            if (head == null) {
                head = head_curr = head_temp;
            } else {
                head_curr.next = head_temp;
                head_curr = head_temp;
            }
        }

        if(in.hasNextLine()) {
            in.nextLine();
        }

        int left;
        left = Integer.parseInt(in.nextLine().trim());

        int right;
        right = Integer.parseInt(in.nextLine().trim());

        res = new Solution1().reverseBetween(head, left, right);
        while (res != null) {
            System.out.print(String.valueOf(res.data) + " ");
            res = res.next;
        }
        System.out.println();
    }

//    private int precess3(int[] nums) {
//        int sum = 0;
//        while (true) {
//            ArrayList<Integer> res = countBlod(nums);
//            for (int i = 0; i < res.size(); i++) {
//                if (res.get(i) >= 6) {
//                    sum = sum + 5;
//                    changeNum(nums, i);
//                    break;
//                } else {
//                    sum = sum + 1;
//                    nums[i] = nums[i] - 1;
//                    break;
//                }
//            }
//
//
//        }
//
//    }

    private ArrayList<Integer> countBlod(int[] nums) {
        ArrayList<Integer> res = new ArrayList<>();
        int i = 0;
        while ((i + 3) < nums.length) {
            int tempRes = nums[i] + nums[i + 1] + nums[i + 3];
            res.add(tempRes);
            i++;
        }
        return res;
    }


    private int precess2(List<String> codeStr) {
        // k为for前有几个空格 v为出现次数
        HashMap<Integer, Integer> countMap = new HashMap<>();
        for (String code : codeStr) {
            int count = countBlank(code);
            if (!countMap.containsKey(count)) {
                countMap.put(count, 1);
            }
        }
        return countMap.size();
    }

    // 统计代码中for关键字之前的空格个数
    private int countBlank(String code) {
        int count = 0;
        for (int i = 0; i < code.length(); i++) {
            if (' ' == code.charAt(i)) {
                count++;
            }
            if ('f' == code.charAt(i)) {
                break;
            }
        }
        return count;
    }

    HashSet<Character> c = new HashSet<>();

    public int process1(String str) {

        if (str == null || str.length() < 5) {
            return 0;
        }
        c.add('a');
        c.add('e');
        c.add('i');
        c.add('o');
        c.add('u');

        int left = 0;
        int right = left + 5;
        int result = 0;

        while (right < str.length()) {
            if (isBaiDuStr(str.substring(left, right).toCharArray())) {
                result++;
            }
            left++;
            right = left + 5;
        }

        return result;
    }

    private boolean isBaiDuStr(char[] subChar) {

        Set<Character> set = new HashSet<>();


        for (Character sc : subChar) {
            set.add(sc);
        }

        if (set.size() != 5) {
            return false;
        }


        boolean c0 = c.contains(subChar[0]);
        boolean c1 = c.contains(subChar[1]);
        boolean c2 = c.contains(subChar[2]);
        boolean c3 = c.contains(subChar[3]);
        boolean c4 = c.contains(subChar[4]);

        if (c1 && c2 && c4) {
            return !(c0 && c3);
        }

        return false;
    }
}
