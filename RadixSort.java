package class01;

import java.util.Arrays;

/**
 * @author pacai
 * @version 1.0
 */
@SuppressWarnings("all")
public class RadixSort {
    public static void radixSort(int[] arr){
        if(arr == null || arr.length < 2){
            return;
        }
        radixSort(arr, 0, arr.length - 1, maxbits(arr));
    }

    private static int maxbits(int[] arr){ //求位数
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }
        int res = 0;
        while(max != 0){
            max /= 10;
            res++;
        }
        return res;
    }

    /**
     *
     * @param arr
     * @param l
     * @param r
     * @param digit 最大位数
     */
    private static void radixSort(int[] arr, int l, int r, int digit){
        final int radix = 10;
        int i = 0, j = 0;
        int[] help = new int[r - l + 1];
        int[] backet = new int[radix];
        int res = 1;
        while(digit-- > 0){
            Arrays.fill(backet, 0);
            for (i = 0; i < l; i++) {
                j = getDigit(arr[i],res);
                backet[j]++;
            }
            for (i = 1; i < radix; i++) { //计算前缀和
                backet[i] = backet[i - 1] + backet[j];
            }
            for (i = r; i >= l ; i--) { //根据前缀和个数判断放入位置
                j = getDigit(arr[i], res);
                help[backet[j] - 1] = arr[i]; //backet[j] - 1因为数组从0开始计算位置，而个数从1开始计算
                backet[j]--;
            }
            for (i = 0; i < r; i++) {
                arr[i] = help[i];
            }
            res++;
        }
    }

    private static int getDigit(int num, int res){
        while(--res > 0){
            num /= 10;
        }
        return num % 10;
    }
}
