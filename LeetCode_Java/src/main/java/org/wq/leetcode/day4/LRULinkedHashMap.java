package org.wq.leetcode.day4;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRULinkedHashMap<K, V> extends LinkedHashMap<K, V> {
    private int threshold;

    public LRULinkedHashMap(int threshold) {
        super(16, 0.75f, true);
        this.threshold = threshold;
    }


    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > threshold;
    }


    public static void main(String[] args) {
        LRULinkedHashMap<Integer, String> lruLinkedHashMap = new LRULinkedHashMap<Integer, String>(5);
        lruLinkedHashMap.put(1, "null");
        lruLinkedHashMap.put(2, "null");
        lruLinkedHashMap.put(3, "null");
        lruLinkedHashMap.put(4, "null");
        lruLinkedHashMap.put(5, "null");
        lruLinkedHashMap.put(6, "null");
        lruLinkedHashMap.put(7, "null");

        System.out.println(lruLinkedHashMap);
        lruLinkedHashMap.get(4);
        System.out.println(lruLinkedHashMap);
        lruLinkedHashMap.put(5, "null");
        System.out.println(lruLinkedHashMap);
        System.out.println(lruLinkedHashMap.get(1));
    }
}
