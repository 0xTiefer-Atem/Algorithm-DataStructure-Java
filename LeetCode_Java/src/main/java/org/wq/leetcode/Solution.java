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

    public static void main(String[] args) {
        Item a1 = new Item("A", 1);
        Item b1 = new Item("B", 3);
        Item a2 = new Item("A", 2);
        ArrayList<Item> list = new ArrayList<Item>();
        list.add(a1);
        list.add(b1);
        list.add(a2);

        Item a3 = new Item("A", 8);
        Item b2 = new Item("B", 4);
        ArrayList<Item> list1 = new ArrayList<Item>();
        list1.add(a3);
        list1.add(b2);

        HashMap<String, Integer> map = new HashMap<>();
        map.put("A", 10);
        map.put("B", 10);

        Solution s = new Solution();

        System.out.println(s.tryAddAndPack(list, list1, map, 3));

    }

}
