package org.wq.leetcode;

import org.openjdk.jol.info.ClassLayout;
import org.wq.leetcode.common.ListNode;
import org.wq.leetcode.common.TreeNode;
import org.wq.leetcode.day1.Solution1;
import org.wq.leetcode.day2.BSTIterator;
import org.wq.leetcode.day2.Solution2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * @author： WangQian
 * @date： 2020/9/15 下午 4:56
 */
public class Test {

    public boolean containsNearbyDuplic(int[] nums, int k) {
        HashMap<Integer, List<Integer>> numsMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (numsMap.containsKey(nums[i])) {
                numsMap.get(nums[i]).add(i);
            } else {
                List<Integer> l = new ArrayList<>();
                l.add(i);
                numsMap.put(nums[i], l);
            }
        }

        for (List<Integer> l : numsMap.values()) {
            if (l.size() > 1) {
                Collections.sort(l);

                Integer i1 = l.get(0);
                Integer i2 = l.get(l.size() - 1);
                if (Math.abs(i1 - i2) == k) {
                    return true;
                }
            }
        }

        return false;
    }

    public int longestWPI(int[] hours) {
        int count = 0;
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < hours.length; i++) {
            if (hours[i] > 8) {
                count++;
            } else {
                count--;
            }
            res = Math.max(count + 1, res);
        }

        return res;
    }

    public static void main(String[] args) {
        Test t = new Test();

        int[] nums = {1, 2, 3, 1};
        int k = 3;
//        System.out.println(t.containsNearbyDuplic(nums, k));

        int[] hours = {9, 9, 6, 0, 6, 6, 9};
//        System.out.println(t.longestWPI());
    }
}
