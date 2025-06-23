package class01;

/**
 * @author pacai
 * @version 1.0
 * 暴力堆
 */
public class RightMaxHeap {
    private int[] heap;
    private int size;
    private int heapSize;

    public RightMaxHeap(int size) {
        this.size = size;
        heap = new int[size];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == heapSize;
    }

    public void push(int value) {
        if (isFull()) {
            throw new RuntimeException("Heap is full");
        }
        heap[heapSize++] = value;
    }

    public int pop() {
        int maxIndex = 0;
        for (int i = 0; i < heapSize; i++) {
            if (heap[i] > heap[maxIndex]) {
                maxIndex = i;
            }
        }
        int ans = heap[maxIndex];
        heap[maxIndex] = heap[heapSize--];
        return ans;
    }
}
