package org.wq.leetcode.day31;

import javax.print.DocFlavor;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Solution31 {


    // 剑指 Offer 38. 字符串的排列
    List<String> res = new LinkedList<>();
    boolean[] used;

    public String[] permutation(String s) {
        if (s == null || s.length() == 0) {
            return new String[]{};
        }

        used = new boolean[s.length()];

        Arrays.fill(used, false);

        traverse1(s, 0, new LinkedList<Character>());

        String[] resArr = new String[res.size()];

        for (int i = 0; i < resArr.length; i++) {
            resArr[i] = res.get(i);
        }

        return resArr;
    }

    private void traverse1(String s, int start, LinkedList<Character> subList) {
        if (subList.size() == s.length()) {
            StringBuilder sb = new StringBuilder();
            subList.forEach(sb::append);
            res.add(sb.toString());
            return;
        }

        for (int i = start; i < s.length(); i++) {

            if (used[i]) {
                continue;
            }

            if (i > 0 && s.charAt(i) == s.charAt(i - 1) && !used[i - 1]) {
                continue;
            }

            subList.add(s.charAt(i));
            used[i] = true;

            traverse1(s, 0, subList);

            subList.removeLast();
            used[i] = false;
        }
    }


    public static void main(String[] args) {
        Solution31 s31 = new Solution31();

        System.out.println(Arrays.toString(s31.permutation("suvyls")));


    }
}
