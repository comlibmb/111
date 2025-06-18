package class01;

import java.util.Random;

/**
 * @author pacai
 * @version 1.0
 */
public class MergeSort {
    public static void mergeSort1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }


    private static void process(int[] arr, int L, int R) {
        if (L == R) {
            return;
        }

        int mid = L + ((R - L) >> 1);
        process(arr, L, mid);
        process(arr, mid + 1, R);
        merge(arr, L, mid, R);
    }

    private static void merge(int[] arr, int L, int M, int R) {
        int i = 0;
        int[] help = new int[R - L + 1];
        int p1 = L;
        int p2 = M + 1;
        while (p1 <= M && p2 <= R) {
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= M) {
            help[i++] = arr[p1++];
        }
        while (p2 <= R) {
            help[i++] = arr[p2++];
        }

        for (i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }
    }

    public static void mergeSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        int N = arr.length;
        int mergeSize = 1;
        while (mergeSize < N) {
            int L = 0;
            while (L < N) {
                int M = L + mergeSize - 1;
                if (M >= N) {
                    break;
                }
                int R = Math.min(N - 1, M + mergeSize);
                merge(arr, L, M, R);
                L = R + 1;
            }
            if (mergeSize > N / 2) { //考虑到int最大值，避免越界
                break;
            }
            mergeSize <<= 1;
        }

    }

    //求小和
    public static int smallSum(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }

        return process1(arr, 0, arr.length - 1);
    }

    private static int process1(int[] arr, int L, int R) {
        if (L == R) {
            return 0;
        }

        int mid = L + ((R - L) >> 1);
        // todo 返回左边小和加上右边小和以及整个数组小和
        return process1(arr, L, mid)
                +
                process1(arr, mid + 1, R)
                +
                merge1(arr, L, mid, R);
    }

    @SuppressWarnings("all")
    private static int merge1(int[] arr, int L, int M, int R) {
        int[] help = new int[R - L + 1];
        int p1 = L;
        int p2 = M + 1;
        int ans = 0;
        int i = 0;
        while (p1 <= M && p2 <= R) {
            //todo 利用有序信息一次性把小和求出
            ans += arr[p1] < arr[p2] ? (R - L + 1) * arr[p1] : 0; //加上右边所有比左边大的数产生的小和数
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }

        while (p1 <= M) {
            help[i++] = arr[p1++];
        }
        while (p2 <= R) {
            help[i++] = arr[p2++];
        }
        for (i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }
        return ans;
    }


    //逆序对
    public static void mergeSort3(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        process3(arr, 0, arr.length - 1);
    }

    private static int process3(int[] arr, int L, int R) {
        if (L == R) {
            return 0;
        }
        int mid = L + ((R - L) >> 1);
        return process3(arr, L, mid)
                +
                process3(arr, mid + 1, R)
                +
                merge3(arr, L, mid, R);
    }

    private static int merge3(int[] arr, int L, int M, int R) {
        int[] help = new int[R - L + 1];
        int p1 = L;
        int p2 = M + 1;
        int i = 0;
        int ans = 0;
        while(p1 <= M && p2 <= R){
            ans += arr[p2] < arr[p1] ? R - L + 1 : 0;
            help[i++] = arr[p1] >= arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= M) {
            help[i++] = arr[p1++];
        }
        while (p2 <= R) {
            help[i++] = arr[p2++];
        }
        for (i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }
        return ans;
    }

    //对数器
    public static int cal(int[] arr){
        if(arr == null || arr.length < 2){
            return 0;
        }
        int ans = 0;
        for (int i = 1; i < arr.length; i++) {
            for (int j = i - 1,num = arr[j]; j < arr.length - 1; j++) {
                if(arr[j + 1] < num){
                    ans++;
                }
            }
        }
        return ans;
    }

    //随机数组·
    private static int[] randomArray(int maxValue,int maxSize){
        Random random = new Random();
        int[] arr = new int[random.nextInt(maxSize)];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(maxValue);
        }
        return arr;
    }
}
