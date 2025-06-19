package class01;

/**
 * @author pacai
 * @version 1.0
 */
public class QuickSort {
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static int partition(int[] arr, int L, int R) {
        if (L > R) {
            return -1;
        }
        if (L == R) {
            return L;
        }
        int lessEqual = L - 1;
        int index = L;
        while (index < R) {
            if (arr[index] <= arr[R]) {
                swap(arr, index, ++lessEqual);
            }
            index++;
        }
        swap(arr, ++lessEqual, R);
        return lessEqual;
    }

    @SuppressWarnings("all")
    public static int[] netherlandsFlag(int[] arr, int L, int R) {
        if (L > R) {
            return new int[]{-1, -1};
        }
        if (L == R) {
            return new int[]{L, R};
        }
        int less = L - 1;
        int index = L;
        int more = R;
        while (index < more) { //撞上右边界结束
            if (arr[index] == arr[R]) {
                index++;
            } else if (arr[index] > arr[R]) {
                swap(arr, index, --more);
            } else {
                swap(arr, index++, ++less);
            }
        }
        swap(arr, R, more);
        return new int[]{less + 1, more};
    }

}
