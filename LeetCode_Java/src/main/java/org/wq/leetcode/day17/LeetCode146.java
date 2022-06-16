package org.wq.leetcode.day17;

import java.util.LinkedHashMap;
import java.util.Map;

// 146. LRU 缓存
public class LeetCode146 {


    class Cache<K, V> extends LinkedHashMap<K, V> {
        private int size = 0;

        public Cache(int size) {
            super(16, 0.75f, true);
            this.size = size;

        }

        @Override
        protected boolean removeEldestEntry(Map.Entry eldest) {
            return size() > size;
        }
    }

    class LRUCache {
        private Cache<Integer, Integer> cache;

        public LRUCache(int capacity) {
            cache = new Cache(capacity);
        }

        public int get(int key) {
            return cache.get(key) == null ? -1 : cache.get(key);
        }

        public void put(int key, int value) {
            cache.put(key, value);
        }
    }


    public static void main(String[] args) {
    }
}
