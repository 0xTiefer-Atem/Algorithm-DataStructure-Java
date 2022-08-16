package org.wq.leetcode.day28;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Solution28 {
    // 151. 颠倒字符串中的单词
    public String reverseWords(String s) {
        s = s.trim();
        String[] s1 = s.split(" ");
        ArrayList<String> list = new ArrayList<>();

        for (String ss : s1) {
            if (!"".equals(ss)) {
                list.add(ss);
            }
        }

        Collections.reverse(list);

        int size = list.size();
        s1 = new String[size];
        for (int i = 0; i < size; i++) {
            s1[i] = list.get(i).trim();
        }


        return String.join(" ", s1);
    }

    // 48. 旋转图像
    public void rotate(int[][] matrix) {
        if (matrix == null) {
            return;
        }
        int r = matrix.length;
        int c = matrix[0].length;

        // 沿对角线旋转

        for (int i = 0; i < r; i++) {
            for (int j = i; j < c; j++) {
                if (i == j) {
                    continue;
                }
                matrix[i][j] = matrix[i][j] ^ matrix[j][i];
                matrix[j][i] = matrix[i][j] ^ matrix[j][i];
                matrix[i][j] = matrix[i][j] ^ matrix[j][i];
            }
        }

        for (int i = 0; i < r; i++) {
            reserveArray(matrix[i]);
        }

    }

    private void reserveArray(int[] arr) {
        int i = 0, j = arr.length - 1;
        while (i < j) {
            arr[i] = arr[i] ^ arr[j];
            arr[j] = arr[i] ^ arr[j];
            arr[i] = arr[i] ^ arr[j];
            i++;
            j--;
        }
    }

    public static void main(String[] args) {
        Solution28 s28 = new Solution28();

        String s = "a good   example";
//        System.out.println(s28.reverseWords(s));

        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        s28.rotate(matrix);
    }
}
