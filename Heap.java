package class01;

import java.util.PriorityQueue;

/**
 * @author pacai
 * @version 1.0
 */
@SuppressWarnings("all")
public class Heap {
    private int heapSize;
    private int[] heap;
    private int size;

    //todo 一个几乎有序的数组，几乎有序是指，如果把数组排好序后，
    // 每个元素移动的距离一定不超过k,并且k相对数组长度是较小的
    public void sortedArrDistanceLessK(int[] arr, int k) {
        if (arr == null || arr.length < 2) {
            return;
        }
        PriorityQueue<Integer> pg = new PriorityQueue<Integer>();
        int index = 0;
        for (; index < Math.min(arr.length - 1, k); index++) {
            pg.add(arr[index]);
        }
        int i = 0;
        for (; index < arr.length;index++, i++){
            pg.add(arr[index]);
            arr[i] = pg.poll();
        }
        while(!pg.isEmpty()){
            arr[i++] = pg.poll();
        }

    }


    public void heapSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        //O(N * logN)
        for (int i = 0; i < arr.length; i++) {
            heapInsert(arr, i); //调整为大根堆
        }

        //O(N)
        for (int i = arr.length - 1; i >= 0; i++) {
            heapify(arr, i, arr.length - 1); //从后到前调整为大根堆
        }

        int heapSize = arr.length;
        swap(arr, 0, --heapSize);
        //O(N * logN)
        while (heapSize > 0) {
            heapify(arr, 0, heapSize);
            swap(arr, 0, --heapSize);//逐步将最大值，次大值...依次放到数组最后
        }
    }


    public Heap(int size) {
        this.heap = new int[size];
        this.size = size;
    }

    public boolean isEmpty() {
        return heapSize == 0;
    }

    public int size() {
        return heapSize;
    }

    public boolean isFull() {
        return size == heapSize;
    }

    public int pop() {
        int val = heap[0];
        swap(heap, 0, --heapSize);
        heapify(heap, 0, heapSize);
        return val;
    }

    private void heapify(int[] heap, int index, int heapSize) {
        int left = index * 2 + 1;
        while (left < heapSize) {
            int largest = left + 1 < heapSize && heap[left + 1] > heap[left] ? left + 1 : left;//筛选出左右子结点中的大值
            largest = heap[largest] > heap[index] ? largest : index;
            if (largest == index) {//已经下沉到该存放的位置
                break;
            }
            swap(heap, largest, index);
            index = largest;
            left = index * 2 + 1;
        }
    }


    public void push(int val) {
        if (isFull()) {
            throw new HeapOverException();
        }
        heap[heapSize] = val;
        heapInsert(heap, heapSize++);
    }

    private void heapInsert(int[] heap, int index) {
        while (heap[index] > heap[(index - 1) / 2]) {
            swap(heap, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    private static void swap(int[] heap, int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }


    private static class HeapOverException extends RuntimeException {
        public HeapOverException() {
            super("Heap is full");
        }
    }

    public static void main(String[] args) {
        int times = 1000000;
        int value = 1000;
        int limit = 100;
        for (int i = 0; i < times; i++) {
            int curlimit = (int) (Math.random() * limit) + 1;
            Heap heap = new Heap(curlimit);
            RightMaxHeap rih = new RightMaxHeap(curlimit);
            int cupOpTimes = (int) (Math.random() * limit);
            for (int j = 0; j < cupOpTimes; j++) {
                if (heap.isEmpty() != rih.isEmpty()) {
                    System.out.println("Oops");
                }
                if (heap.isFull() != rih.isFull()) {
                    System.out.println("Oops");
                }
                if (heap.isEmpty()) {
                    int curValue = (int) (Math.random() * value);
                    heap.push(curValue);
                    rih.push(curValue);
                } else if (heap.isFull()) {
                    if (heap.pop() != rih.pop()) {
                        System.out.println("Oops");
                    }
                } else {
                    if (Math.random() < 0.5) { //0.5概率加
                        int curValue = (int) (Math.random() * limit);
                        heap.push(curValue);
                        rih.push(curValue);
                    } else {
                        if (heap.pop() != rih.pop()) {
                            System.out.println("Oops");
                        }
                    }
                }

            }
        }
    }
}

