package org.wq.leetcode;

import java.util.*;

public class Test1 {


    // 输出元素组成数组的排列组合形式
    LinkedList<LinkedList<Integer>> res = new LinkedList<>();
    boolean[] used;

    public int[][] exportAllOrders(int[] array) {
        used = new boolean[array.length];
        process(array, new LinkedList<>());

        int[][] resArr = new int[res.size()][res.get(0).size()];

        for (int i = 0; i < res.size(); i++) {
            LinkedList<Integer> temp = res.get(i);
            for (int j = 0; j < temp.size(); j++) {
                resArr[i][j] = temp.get(j);
            }
        }

        return resArr;
    }

    private void process(int[] array, LinkedList<Integer> subList) {
        if (array.length == subList.size()) {
            res.add(new LinkedList<>(subList));
            return;
        }

        for (int i = 0; i < array.length; i++) {
            if (used[i]) {
                continue;
            }
            subList.add(array[i]);
            used[i] = true;

            process(array, subList);

            subList.removeLast();
            used[i] = false;
        }
    }

    // [编程题]寻找三元组

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * @param arr int整型一维数组
     * @return int整型
     */
    public int NumberOfTriplets(int[] arr) {
        HashSet<Integer> set = new HashSet<Integer>();
        for (int i = 0; i < arr.length; i++) {
            set.add(arr[i]);
        }
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                if (set.contains(arr[i] + arr[j])) {
                    count++;
                }
            }
        }
        return count;
    }


    // 识别山脉数组
    public boolean validMountainArray(int[] arr) {
        int i = 0;
        while (i < arr.length - 1 && arr[i] < arr[i + 1]) i++;

        if (i == 0 || i == arr.length - 1) {
            return false;
        }

        while (i < arr.length - 1 && arr[i] > arr[i + 1]) i++;
        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();

        int[][] o = new int[N + 1][M + 1];

        // 面积
        int area = 0;

        //立方体总个数
        int sumCube = 0;

        // 计算遮挡
        int shadow = 0;

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                o[i][j] = sc.nextInt();
                if (o[i][j] != 0) {
                    // 计算底面积
                    sumCube += o[i][j];
                    // 单列立方体遮挡面积
                    shadow += (o[i][j] - 1) * 2;
                }
            }
        }

        area = sumCube * 6;

        for (int i = 1; i <= M; i++) {
            for (int j = 1; j < N; j++) {
                if (o[j][i] != 0 && o[j + 1][i] != 0) {
                    if (Math.abs(o[j][i] - o[j + 1][i]) == 0) {
                        shadow += 2;
                    } else {
                        shadow += Math.abs(o[j][i] - o[j + 1][i]) * 2;
                    }
                }
            }
        }
        System.out.println(shadow);


        for (int i = 1; i <= N; i++) {
            for (int j = 1; j < M; j++) {
                if (o[i][j] != 0 && o[i][j + 1] != 0) {
                    if (Math.abs(o[j][i] - o[j + 1][i]) == 0) {
                        shadow += 2;
                    } else {
                        shadow += Math.abs(o[j][i] - o[j + 1][i]) * 2;
                    }
                }
            }
        }

        area -= shadow;
        System.out.println(area);
    }

    public void quickSort(int[] arr, int left, int right, int[] selectedMids, int index) {
        if (index < selectedMids.length) {
            partition(arr, left, right, selectedMids[index]);
            quickSort(arr, left, right, selectedMids, index + 1);
        }
    }

    private void partition(int[] arr, int left, int right, int selectedMid) {
        int mid = selectedMid;
        int l = left;
        int r = right;
        while (l < r) {
            while (l < r && arr[l] <= mid) {
                l++;
            }

            while (l < r && arr[r] >= mid) {
                r--;
            }

            if (l < r) {
                int t = arr[l];
                arr[l] = arr[r];
                arr[r] = t;
            }
        }
        arr[left] = arr[l];
        arr[l] = mid;
    }

    public int getMax(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int num : arr) {
            if (num > max) {
                max = num;
            } else {
                break;
            }
        }
        return max;
    }

    int res1 = 0;

    public int getNum() {

        buyFruits(50, 50);

        return res1;
    }

    public void buyFruits(int money, int nums) {
        if (money < 0 || nums < 0) {
            return;
        }

        if (money == 0 && nums == 0) {
            res1++;
            return;
        }

        // 此时购买西瓜
        buyFruits(money - 7, nums - 1);

        // 此时购买苹果
        buyFruits(money - 3, nums - 1);

        // 此时购买荔枝
        buyFruits(money - 1, nums - 3);
    }

}
