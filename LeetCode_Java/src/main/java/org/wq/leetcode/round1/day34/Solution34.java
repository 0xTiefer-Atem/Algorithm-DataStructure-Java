package org.wq.leetcode.round1.day34;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Solution34 {

    // 3. 无重复字符的最长子串
    public int lengthOfLongestSubstring(String s) {
        HashMap<Character, Integer> window = new HashMap<>();
        int left = 0;
        int right = 0;
        int res = 0;
        while (right < s.length()) {
            Character c = s.charAt(right);
            window.put(c, window.getOrDefault(c, 0) + 1);
            right++;
            while (window.getOrDefault(c, 0) > 1) {
                Character d = s.charAt(left);

                window.put(d, window.getOrDefault(d, 0) - 1);

                left++;
            }
            res = Math.max(res, right - left);
        }
        return res;
    }

    // 438. 找到字符串中所有字母异位词
    LinkedList<Integer> res438 = new LinkedList<>();

    public List<Integer> findAnagrams(String s, String p) {
        HashMap<Character, Integer> needMap = new HashMap<>();
        HashMap<Character, Integer> window = new HashMap<>();
        for (int i = 0; i < p.length(); i++) {
            needMap.put(p.charAt(i), needMap.getOrDefault(p.charAt(i), 0) + 1);
        }
        int right = 0;
        int left = 0;

        // 窗口有效字符个数
        int valid = 0;
        while (right < s.length()) {
            Character rc = s.charAt(right);
            right++;
            if (needMap.containsKey(rc)) {
                window.put(rc, window.getOrDefault(rc, 0) + 1);
                if (needMap.get(rc).equals(window.get(rc))) {
                    valid++;
                }
            }



            while (right - left >= needMap.size()) {
                if (valid == needMap.size()) {
                    res438.add(left);
                }
                Character lc = s.charAt(left);
                left++;

                if (needMap.containsKey(lc)) {
                    if (Objects.equals(window.get(lc), needMap.get(lc))) {
                        valid--;
                    }
                    window.put(lc, window.getOrDefault(lc, 0) - 1);
                }
            }
        }

        return res438;
    }

    public static void main(String[] args) {
        Solution34 s34 = new Solution34();

//        System.out.println(s34.lengthOfLongestSubstring("bbbbb"));
        System.out.println(s34.findAnagrams("cbaebabacd", "abc"));
    }
}
