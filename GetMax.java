package class01;

/**
 * @author pacai
 * @version 1.0
 * 求最大值
 */
public class GetMax {
    public static int getMax(int[] arr, int L, int R) {
        if (L == R) {
            return arr[L];
        }

        int mid = L + ((R - L) >> 1);
        int leftMax = getMax(arr, L, mid);
        int rightMax = getMax(arr, mid + 1, R);
        return Math.max(leftMax, rightMax);
    }

    public static int getMax(int[] arr){
        if(arr == null || arr.length == 0){
            throw new IllegalArgumentException();
        }
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            max = Math.max(max,arr[i]);
        }
        return max;
    }
}
