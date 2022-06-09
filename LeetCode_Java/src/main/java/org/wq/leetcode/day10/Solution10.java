package org.wq.leetcode.day10;

import org.wq.leetcode.common.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Solution10 {
    // 打印一个字符串的所有子集
    public static void subStr(String s) {
        if (s == null) {
            return;
        }
        char[] charS = s.toCharArray();
        List<Character> charList = new ArrayList<Character>();
        process(charS, 0, charList);
    }

    public static void process(char[] charS, int index, List<Character> charList) {
        if (index == charS.length) {
            System.out.println(String.valueOf(charList));
            return;
        }

        // 加本次字符
        List<Character> add = new ArrayList<>(charList);
        Collections.copy(add, charList);
        add.add(charS[index]);
        process(charS, index + 1, add);

        // 不加本次字符
        List<Character> notAdd = new ArrayList<>(charList);
        Collections.copy(notAdd, charList);
        process(charS, index + 1, notAdd);
    }

    // 打印一个字符串的全排列
    public static void fullStr(String s) {
        if (s == null) {
            return;
        }

        List<String> res = new ArrayList<>();

        char[] strChar = s.toCharArray();

        process1(strChar, 0, res);

        res.forEach(System.out::println);

    }

    private static void process1(char[] strChar, int i, List<String> res) {
        if (i == strChar.length) {
            res.add(String.valueOf(strChar));
            return;
        }

        boolean[] isCheck = new boolean[26];
        for (int j = i; j < strChar.length; j++) {
            // 注释是不带重复的
//            if (!isCheck[strChar[j] - 'a']) {
//                isCheck[strChar[j] - 'a'] = true;
            Utils.swap(strChar, i, j);
            process1(strChar, i + 1, res);
            Utils.swap(strChar, i, j);
//            }
        }

    }


    // 给定两个长度都为N的数组 Weight 和 values, weight[i] 和 values[i] 分别代表i号物品
    // 的重量和价值，给定一个正数bag，表示一个载重bag的袋子，你装的物品不能超过这个重量
    // 返回你能装下最多的价值是多少
    // 递归方式
    public static int maxValue(int[] weight, int[] values, int bag) {
        if (weight == null || weight.length == 0 || values == null || values.length == 0 || bag == 0) {
            return 0;
        }

        return selectedMaxValue(weight, values, 0, 0,bag);


    }

    private static int selectedMaxValue(int[] weight, int[] values, int i, int maxValue, int bag) {
        if (bag < 0) {
            return 0;
        }

        if (i == weight.length) {
            return 0;
        }

        return Math.max(selectedMaxValue(weight, values, i+1, maxValue + values[i], bag - weight[i]), selectedMaxValue(weight, values, i+1, maxValue, bag));
    }

    public static void main(String[] args) {
        fullStr("abcd");
    }
}
