package org.wq.leetcode.round1.day4;

import java.util.LinkedHashMap;
import java.util.Map;

public class Solution4 {

    class LRULinkHashMap<K, V> extends LinkedHashMap<K ,V>{
        private int threshold;

        public LRULinkHashMap(int threshold) {
            super(16, 0.75f, true);
            this.threshold = threshold;
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
            return size() > threshold;
        }
    }


    class LRUCache {
        private final LRULinkHashMap<Integer, Integer> lru;

        public LRUCache(int capacity) {
            lru = new LRULinkHashMap(capacity);
        }

        public int get(int key) {

            return lru.get(key) == null ? -1 : lru.get(key);
        }

        public void put(int key, int value) {
            lru.put(key, value);
        }
    }
}
