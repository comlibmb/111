package class01;

/**
 * @author pacai
 * @version 1.0
 * 计数排序
 */
public class CountSort {
    public static void countSort(int[] arr){
        if(arr == null || arr.length < 2){
            return;
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max,arr[i]);
        }
        int[] bucket = new int[max + 1];
        for (int i = 0; i < arr.length; i++) {
            bucket[arr[i]]++;
        }
        int j = 0;
        for (int i = 0; i < bucket.length; i++) {
            while(bucket[i]-- > 0){
                arr[j++] = i;
            }
        }
    }
}
