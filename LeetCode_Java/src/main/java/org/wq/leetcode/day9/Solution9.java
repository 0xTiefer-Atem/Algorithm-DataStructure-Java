package org.wq.leetcode.day9;

public class Solution9 {
    /**
     * 递归乘法。 写一个递归函数，不使用 * 运算符， 实现两个正整数的相乘。可以使用加号、减号、位移，但要吝啬一些。
     *
     * 示例1:
     *  输入：A = 1, B = 10
     *  输出：10
     *
     * 示例2:
     *  输入：A = 3, B = 4
     *  输出：12
     */

    public static int multiply(int A, int B) {
        if (A == 1) {
            return B;
        }

        return B + multiply(A - 1, B);
    }

    public static void main(String[] args) {
        System.out.println(multiply(3, 4));
    }
}
