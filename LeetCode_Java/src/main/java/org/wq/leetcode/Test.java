package org.wq.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Test {


    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     * <p>
     * 运动员可得到的最高分
     *
     * @param energy  int整型 运动员体力值
     * @param actions int整型二维数组 二维数组i为动作号 actions[i][0]为动作i+1消耗体力,actions[i][1]为动作i+1得分
     * @return int整型
     */
    int res = Integer.MIN_VALUE;

    public int maxScore(int energy, int[][] actions) {
        if (actions == null || actions[0].length == 0) {
            return 0;
        }
        process(energy, actions, 0, 0);

        return res;


    }

    private void process(int energy, int[][] actions, int sum, int start) {
        if (energy < 0) {
            return;
        }

        if (energy == 0) {
            res = Math.max(res, sum);
        }

        for (int i = start; i < actions.length; i++) {
            process(energy - actions[i][0], actions, sum + actions[i][1], i + 1);
        }

    }


    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     * <p>
     * 解密
     *
     * @param encryptedNumber int整型 待解密数字
     * @param decryption      int整型 私钥参数D
     * @param number          int整型 私钥参数N
     * @return int整型
     */
    public int Decrypt(int encryptedNumber, int decryption, int number) {
        return (int) Math.pow(encryptedNumber, decryption) % number;
    }


    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     * <p>
     * 翻牌
     *
     * @param inHand string字符串 一组以单空格间隔的手牌(例如：SA HK H9 D8 C5 S5 H4)
     * @return string字符串
     */
    public String showDown(String inHand) {
        String[] cards = inHand.split(" ");
        ArrayList<String> color = new ArrayList<>();
        ArrayList<String> number = new ArrayList<>();

        int i = 0;
        for (String card : cards) {
            if(i == 5) {
                break;
            }
            color.add(card.charAt(0) + "");
            number.add(card.substring(1, card.length()));
            i++;
        }

        String res = "";
        // 判断同花、顺子
        res = tonghua(color, number);
        if (!res.equals("")) {
            return res;
        }

        // 判断对子
        res = duizi(color, number);
        if (!res.equals("")) {
            return res;
        }

        // 判断高牌
        return "GaoPai";

    }

    private String duizi(ArrayList<String> color, ArrayList<String> number) {
        return "";
    }

    // 判断同化和顺子
    private String tonghua(ArrayList<String> color, ArrayList<String> number) {
        // 判断同化
        Set<String> hashSet = new HashSet<>(color);
        boolean ordered = isOrdered(number);
        if (hashSet.size() == 1) {
            // 同花
            // 判断是否有序


            // 判断顺序
            if ("A".equals(number.get(0)) && ordered) {
                return "HuangJiaTongHuaShun";
            }

            if (!"A".equals(number.get(0)) && ordered) {
                return "TongHuaShun";
            }

            if (!ordered) {
                return "TongHua";
            }
        }

        if (ordered) {
            return "ShunZi";
        }

        return "";
    }

    private boolean isOrdered(ArrayList<String> number) {
        ArrayList<Integer> nums = new ArrayList<>();
        HashMap<String, Integer> s = new HashMap<>();
        s.put("K", 13);
        s.put("Q", 12);
        s.put("J", 11);
        for (int i = 0; i < number.size(); i++) {
            String n = number.get(i);
            if ("A".equals(n)) {
                if (i == 0) {
                    nums.add(14);
                } else {
                    nums.add(1);
                }
                continue;
            }
            if (s.containsKey(n)) {
                nums.add(s.get(n));
            } else {
                nums.add(Integer.valueOf(n));
            }
        }
        for (int i = 1; i < nums.size(); i++) {
            if (nums.get(i)  != (nums.get(i - 1) - 1)) {
                return false;
            }
        }

        return true;
    }


    public static void main(String[] args) {
        Test t = new Test();

//        int[][] action = {{1, 1}, {2, 3}, {3, 5}, {5, 10}, {7, 9}, {8, 10}};
//
//        System.out.println(t.maxScore(10, action));

//        System.out.println(t.Decrypt(4296, 1601, 4757));

        System.out.println(t.showDown("SA SK SQ SJ S10 H10 C9"));
    }
}
