package org.wq.leetcode;

import java.util.*;

public class Solution {

    public static class Item {
        public final String name;
        public int num;

        public Item(String name, int num) {
            this.name = name;
            this.num = num;
        }

        @Override
        public String toString() {
            return "[" + name + "," + num + "]";
        }
    }

    /**
     * @param itemList    用户背包
     * @param itemAddList 要加入的物品
     * @param confMap     每个物品的单个最大值
     * @param capacity    背包最大容量
     * @return
     */
    public List<Item> tryAddAndPack(List<Item> itemList, List<Item> itemAddList, Map<String, Integer> confMap, int capacity) {

        if (itemAddList.size() == 0) {
            return itemList;
        }

        HashMap<String, Item> tempBag = new HashMap<>();
        for (Item item : itemList) {
            if (tempBag.containsKey(item.name)) {
                Item i = tempBag.get(item.name);
                i.num = item.num + i.num;
                tempBag.put(i.name, i);
            } else {
                tempBag.put(item.name, item);
            }
        }

        List<Item> res = new ArrayList<>(tempBag.values());

        res.sort((o1, o2) -> {
            if (o1.name.equals(o2.name)) {
                return o2.num - o1.num;
            }
            return o1.name.compareTo(o1.name);
        });


        List<Item> tempRes = new ArrayList<>();

        for (Item item : itemAddList) {
            int maxValue = confMap.get(item.name);
            if (tempBag.containsKey(item.name)) {
                Item i = tempBag.get(item.name);
                int val = item.num + i.num;

                if (val > maxValue) {
                    Item i1 = new Item(item.name, item.num);
                    Item i2 = new Item(item.name, i.num);
                    tempRes.add(i1);
                    tempRes.add(i2);
                } else {
                    tempRes.add(new Item(i.name, val));
                }
            } else {
                tempRes.add(item);
            }
        }

        if (tempRes.size() > capacity) {
            return res;
        } else {
            tempRes.sort((o1, o2) -> {
                if (o1.name.equals(o2.name)) {
                    return o2.num - o1.num;
                }

                return o1.name.compareTo(o1.name);

            });
            return tempRes;
        }
    }


//    public static Collection<Runnable> makeTasks(List<Runnable> tasks){
//        PriorityQueue<Runnable> q = PriorityQueue<Runnable>();
//        return new LinkedList<Runnable>(tasks);
//    }

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * @param num int整型一维数组 非负整数X的数组形式
     * @param k   int整型 非负整数K
     * @return int整型一维数组
     */
    public int[] AddToArrayForm(int[] num, int k) {
        long sum = 0;
        int length = num.length;
        for (int i = length - 1; i >= 0; i--) {
            sum += num[i] * Math.pow(10, length - i - 1);
        }
        String numS = sum + k + "";

        int[] res = new int[numS.length()];
        char zero = '0';
        for (int i = 0; i < numS.length(); i++) {
            res[i] = numS.charAt(i) - zero;
        }

        return res;
    }

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     * <p>
     * 始终返回 true 即可
     *
     * @param arr int整型一维数组
     * @return int整型一维数组
     */
    public int[] QuickSort(int[] arr) {
        sort(arr, 0, arr.length - 1);
        return arr;
    }

    private void sort(int[] arr, int l, int r) {
        if (l < r) {
            int mid = partition(arr, l, r);
            sort(arr, l, mid - 1);
            sort(arr, mid + 1, r);
        }
    }

    private int partition(int[] arr, int l, int r) {
        int mid = arr[l];
        while (l < r) {
            while (l < r && arr[l] < mid) {
                l++;
            }

            while (l < r && arr[r] >= mid) {
                r--;
            }

            int temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
        }
        int temp = arr[l];
        arr[l] = arr[r];
        arr[r] = temp;

        return l;
    }

    public static void main(String[] args) {
        Integer i1 = new Integer(127);
        Integer i2 = new Integer(127);
        System.out.println(i1 == i2);
        System.out.println(i1.equals(i2));
        Integer i3 = new Integer(128);
        Integer i4 = new Integer(128);
        System.out.println(i3 == i4);
        System.out.println(i3.equals(i4));
        Integer i5 = 128;
        Integer i6 = 128;
        System.out.println(i5 == i6);
        System.out.println(i5.equals(i6));
        Integer i7 = 127;
        Integer i8 = 127;
        System.out.println(i7 == i8);
        System.out.println(i7.equals(i8));
    }

}
