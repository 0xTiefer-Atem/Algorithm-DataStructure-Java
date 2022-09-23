package org.wq.leetcode.round1.day6;

import java.util.PriorityQueue;

/**
 * 已知一个几乎有序的数据，几乎有序是指，如果把数组排好，每个元素移动的距离可以不超过k，并且k相对于数组来说比较小
 * 请选择俄式的排序算法针对这个数组进行排序。
 */
public class SmallHeapSort {
    public void sortedArrDistanceLessK(int[] arr, int k) {

        // 默认小根堆
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int index = 0;

        for(; index <= Math.min(arr.length, k); index++){
            heap.add(arr[index]);
        }

        int i = 0;
        for(; index < arr.length; i++, index++) {
            arr[i] = heap.poll();
            heap.add(arr[index]);
        }

        while (!heap.isEmpty()){
            arr[i++] = heap.poll();
        }

    }
}
