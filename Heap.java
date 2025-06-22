package class01;

/**
 * @author pacai
 * @version 1.0
 */
@SuppressWarnings("all")
public class Heap {
    private int heapSize;
    private int[] heap;
    private int size;

    public Heap(int heapSize, int size) {
        this.heapSize = heapSize;
        this.heap = new int[size];
        size = size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return heapSize;
    }

    public boolean isFull() {
        return size == heapSize;
    }

    public int pop() {
        int val = heap[0];
        heap[0] = heap[--heapSize];
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
        if (size == heapSize) {
            throw new HeapOverException();
        }
        heap[heapSize] = val;
        heapInsert(heapSize);
    }

    private void heapInsert(int index) {
        while (heap[index] > heap[(index - 1) >> 1]) {
            swap(heap, index, (index - 1) >> 1);
            index = (index - 1) >> 1;
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
}
