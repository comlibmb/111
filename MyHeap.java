package class01;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

/**
 * @author pacai
 * @version 1.0
 */
@SuppressWarnings("all")
public class MyHeap<T> {
    private ArrayList<T> heap;
    private HashMap<T, Integer> indexMap;
    private int heapSize;
    private Comparator<? super T> comparator;

    public MyHeap(Comparator<? super T> comparator) {
        heap = new ArrayList<>();
        indexMap = new HashMap<>();
        heapSize = 0;
        this.comparator = comparator;
    }

    public boolean isEmpty() {
        return heapSize == 0;
    }

    public int size() {
        return heapSize;
    }

    public void push(T item) {
        heap.add(item);
        indexMap.put(item, heapSize);
        heapInsert(heapSize++);
    }

    public boolean contains(T item) {
        return indexMap.containsKey(item);
    }

    public T pop() {
        if (isEmpty()) {
            throw new HeapIsEmptyException();
        }
        T ans = heap.get(0);
        int end = heapSize - 1;
        swap(0, end);
        heap.remove(end);
        indexMap.remove(ans);
        heapify(0, --heapSize);
        return ans;
    }


    public void reset(T item){
        Integer i = indexMap.get(item);
        heapInsert(i);
        heapify(i, heapSize);
    }

    private void heapInsert(int index) {
        while (comparator.compare(heap.get(index), heap.get((index - 1) / 2)) < 0) {
            swap(index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    private void heapify(int index, int heapSize) {
        int left = 2 * index + 1;
         while(left < heapSize){
             int largest = left + 1 < heapSize &&
                     comparator.compare(heap.get(left + 1), heap.get(left)) < 0
                     ? left + 1 : left;
             largest = comparator.compare(heap.get(largest), heap.get(index)) < 0 ? index : largest;
             if(largest == index){
                 break;
             }
             swap(index, largest);
             index = largest;
             left = 2 * index + 1;
         }
    }


    private void swap(int i, int j) {
        T o1 = heap.get(i);
        T o2 = heap.get(j);
        heap.set(i, o2);
        heap.set(j, o1);
        indexMap.put(o1, j);
        indexMap.put(o2, i);
    }

    static class HeapIsEmptyException extends RuntimeException {
        public HeapIsEmptyException() {
            super("Heap is empty");
        }
    }
}
