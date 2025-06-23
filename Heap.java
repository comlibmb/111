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

    public Heap(int size) {
        this.heap = new int[size];
        this.size = size;
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
        if (isFull()) {
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

    public static void main(String[] args) {
        int times = 1000000;
        int value = 1000;
        int limit = 100;
        for (int i = 0; i < times; i++) {
            int curlimit = (int)(Math.random() * limit) + 1;
            Heap heap = new Heap(curlimit);
            RightMaxHeap rih = new RightMaxHeap(curlimit);
            int cupOpTimes = (int)(Math.random() * limit);
            for (int j = 0; j < cupOpTimes; j++) {
                if(heap.isEmpty() != rih.isEmpty()){
                    System.out.println("Oops");
                }
                if(heap.isFull() != rih.isFull()){
                    System.out.println("Oops");
                }
                if(heap.isEmpty()){
                    int curValue = (int)(Math.random() * value);
                    heap.push(curValue);
                    rih.push(curValue);
                }else if(heap.isFull()){
                    if(heap.pop() != rih.pop()){
                        System.out.println("Oops");
                    }
                }else{
                    if(Math.random() < 0.5){ //0.5概率加
                        int curValue = (int)(Math.random() * limit);
                        heap.push(curValue);
                        rih.push(curValue);
                    }else{
                        if(heap.pop() != rih.pop()){
                            System.out.println("Oops");
                        }
                    }
                }

            }
        }
    }
}

