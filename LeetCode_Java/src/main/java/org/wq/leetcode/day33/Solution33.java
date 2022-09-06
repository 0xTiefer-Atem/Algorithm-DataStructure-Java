package org.wq.leetcode.day33;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

public class Solution33 {


    /**
     * 删除重复元素
     *
     * @param array int整型一维数组
     * @return int整型一维数组
     */
    public int[] removeDuplicate(int[] array) {
        LinkedList<Integer> result = new LinkedList<>();
        for (int j : array) {
            if (result.contains(j)) {
                result.removeFirstOccurrence(j);
            }
            result.add(j);
        }

        int[] res = new int[result.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = result.get(i);
        }

        return res;
    }

    /**
     * 对给定的二叉树依次完成前序，中序，后序遍历，并输出遍历结果
     *
     * @param input int整型一维数组 -1表示Nil节点
     * @return int整型ArrayList<ArrayList <>>
     */

    ArrayList<ArrayList<Integer>> res = new ArrayList<>();

    public ArrayList<ArrayList<Integer>> binaryTreeScan(int[] input) {

        if (input == null || input.length == 0) {
            return res;
        }

        // write code here
        ArrayList<Integer> preList = new ArrayList<>();
        preOrder(input, 0, preList);

        ArrayList<Integer> inList = new ArrayList<>();
        inOrder(input, 0, inList);

        ArrayList<Integer> postList = new ArrayList<>();
        postOrder(input, 0, postList);

        res.add(new ArrayList<>(preList));
        res.add(new ArrayList<>(inList));
        res.add(new ArrayList<>(postList));

        return res;

    }

    private void postOrder(int[] input, int nodeIndex, ArrayList<Integer> subList) {
        if (nodeIndex >= input.length) {
            return;
        }
        if(input[nodeIndex] == -1) {
            return;
        }

        preOrder(input, nodeIndex * 2 + 1, subList);
        preOrder(input, nodeIndex * 2 + 2, subList);
        subList.add(input[nodeIndex]);
    }

    private void inOrder(int[] input, int nodeIndex, ArrayList<Integer> subList) {
        if (nodeIndex >= input.length) {
            return;
        }
        if(input[nodeIndex] == -1) {
            return;
        }


        preOrder(input, nodeIndex * 2 + 1, subList);
        subList.add(input[nodeIndex]);
        preOrder(input, nodeIndex * 2 + 2, subList);
    }

    private void preOrder(int[] input, int nodeIndex, ArrayList<Integer> subList) {
        if (nodeIndex >= input.length) {
            return;
        }

        if(input[nodeIndex] == -1) {
            return;
        }
        subList.add(input[nodeIndex]);
        preOrder(input, nodeIndex * 2 + 1, subList);
        preOrder(input, nodeIndex * 2 + 2, subList);

    }


    public static void main(String[] args) {

        Solution33 s33 = new Solution33();

//        int[] arr = {3, 5, 8, 2, 3, 8};
//
//        System.out.println(Arrays.toString(s33.removeDuplicate(arr)));

        int[] tree = {1, 7, 2, 6, -1, 4, 8};
        System.out.println(s33.binaryTreeScan(tree));
    }
}
