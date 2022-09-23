package org.wq.leetcode.round1.day2;

import org.wq.leetcode.common.TreeNode;

import java.util.*;

public class Solution2 {
    /**
     * 151. 翻转字符串里的单词
     * <p>
     * 示例 1：
     * <p>
     * 输入: "the sky is blue"
     * 输出: "blue is sky the"
     * 示例 2：
     * <p>
     * 输入: " hello world! "
     * 输出:"world! hello"
     * 解释: 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
     * 示例 3：
     * <p>
     * 输入: "a good   example"
     * 输出: "example good a"
     * 解释: 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/reverse-words-in-a-string
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */

    public String reverseWords(String s) {
        if (s.length() == 0) {
            return " ";
        }
        String[] strings = s.split(" ");
        StringBuffer sb = new StringBuffer();
        for (int i = strings.length - 1; i >= 0; i--) {
            if (!"".equals(strings[i])) {
                if (i != 0) {
                    sb.append(strings[i]).append(" ");
                } else {
                    sb.append(strings[i]);
                }
            }
        }
        return sb.toString().trim();
    }

    /**
     * 153. 寻找旋转排序数组中的最小值
     * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
     * <p>
     * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
     * <p>
     * 请找出其中最小的元素。
     * <p>
     * 你可以假设数组中不存在重复元素。
     * <p>
     * 示例 1:
     * <p>
     * 输入: [3,4,5,1,2]
     * 输出: 1
     * 示例 2:
     * <p>
     * 输入: [4,5,6,7,0,1,2]
     * 输出: 0
     */

    public int findMin1(int[] nums) {
        int length = nums.length;
        if (length == 0) {
            return -1;
        }
        int min = 1000;
        for (int i = 0; i < length; i++) {
            if (min > nums[i]) {
                min = nums[i];
            }
        }
        return min;
    }

    /**
     * 154. 寻找旋转排序数组中的最小值 II
     * <p>
     * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
     * <p>
     * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
     * <p>
     * 请找出其中最小的元素。
     * <p>
     * 注意数组中可能存在重复的元素。
     * <p>
     * 示例 1：
     * <p>
     * 输入: [1,3,5]
     * 输出: 1
     * 示例 2：
     * <p>
     * 输入: [2,2,2,0,1]
     * 输出: 0
     */

    public int findMin2(int[] nums) {
        int length = nums.length;
        if (length == 0) {
            return -1;
        }
        int min = 1000;
        for (int i = 0; i < length; i++) {
            if (min > nums[i]) {
                min = nums[i];
            }
        }
        return min;
    }

    /**
     * 162. 寻找峰值
     * 峰值元素是指其值大于左右相邻值的元素。
     * <p>
     * 给定一个输入数组 nums，其中 nums[i] ≠ nums[i+1]，找到峰值元素并返回其索引。
     * <p>
     * 数组可能包含多个峰值，在这种情况下，返回任何一个峰值所在位置即可。
     * <p>
     * 你可以假设 nums[-1] = nums[n] = -∞。
     * <p>
     * 示例 1:
     * <p>
     * 输入: nums = [1,2,3,1]
     * 输出: 2
     * 解释: 3 是峰值元素，你的函数应该返回其索引 2。
     * 示例 2:
     * <p>
     * 输入: nums = [1,2,1,3,5,6,4]
     * 输出: 1 或 5
     * 解释: 你的函数可以返回索引 1，其峰值元素为 2；
     *      或者返回索引 5， 其峰值元素为 6。
     */

    public int findPeakElement(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] < nums[i + 1]) {
                return i;
            }
        }
        return nums.length - 1;
    }

    /**
     * 189. 旋转数组
     * 给定一个数组，将数组中的元素向右移动k个位置，其中k是非负数。
     * <p>
     * 示例 1:
     * <p>
     * 输入: [1,2,3,4,5,6,7] 和 k = 3
     * 输出: [5,6,7,1,2,3,4]
     * 解释:
     * 向右旋转 1 步: [7,1,2,3,4,5,6]
     * 向右旋转 2 步: [6,7,1,2,3,4,5]
     * 向右旋转 3 步: [5,6,7,1,2,3,4]
     * 示例2:
     * <p>
     * 输入: [-1,-100,3,99] 和 k = 2
     * 输出: [3,99,-1,-100]
     * 解释:
     * 向右旋转 1 步: [99,-1,-100,3]
     * 向右旋转 2 步: [3,99,-1,-100]
     */

    public void rotate(int[] nums, int k) {
        int[] res = new int[nums.length];
        for (int j = 0; j < res.length; j++) {
            int rotate_index = (j + k + 1) % nums.length;
            res[j] = nums[rotate_index];
        }
        nums = res;
        for (int i = 0; i < res.length; i++) {
            nums[i] = res[i];
        }
    }

    /**
     * 199. 二叉树的右视图
     * 给定一棵二叉树，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
     *
     * 示例:
     *
     * 输入: [1,2,3,null,5,null,4]
     * 输出: [1, 3, 4]
     * 解释:
     *
     *    1            <---
     *  /   \
     * 2     3         <---
     *  \     \
     *   5     4       <---
     * */

    //思路: 广度优先搜索,层序遍历,保存每层最后一个节点
    public List<Integer> rightSideView(TreeNode root) {
        if(root == null) {
            return  new ArrayList<Integer>();
        }
        Queue<TreeNode> queue = new ArrayDeque<TreeNode>();
        List<Integer> resList = new ArrayList<Integer>();
        queue.add(root);
        while (!queue.isEmpty()) {
            //当前层的个数
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode tempNode = queue.poll();
                if(tempNode.left != null) {
                    queue.add(tempNode.left);
                }
                if(tempNode.right != null) {
                    queue.add(tempNode.right);
                }
                if(i == size-1) {
                    resList.add(tempNode.val);
                }
            }
        }
        return resList;
    }
}
