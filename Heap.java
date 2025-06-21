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

    public boolean isFull(){
        return size == heapSize;
    }

    public void push(int val){
        if(size == heapSize){
            throw new HeapOverException();
        }
        heap[heapSize] = val;
        heapInsert(heapSize);
    }

    private void heapInsert(int index){
        while(heap[index] > heap[(index - 1) >> 1]){
            swap(heap, index, (index - 1) >> 1);
            index = (index - 1) >> 1;
        }
    }

    private static void swap(int[] heap, int i ,int j){
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
