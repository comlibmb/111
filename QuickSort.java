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


    public static void quickSort1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        process1(arr, 0, arr.length - 1);
    }


    public static void process1(int[] arr, int L, int R) {
        if (L > R) {
            return;
        }
        int mid = partition(arr, L, R);
        process1(arr, L, mid - 1);
        process1(arr, mid + 1, R);
    }

    public static void quickSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }


    }

    public static void process2(int[] arr, int L, int R) {
        if (L > R) {
            return;
        }

        int[] midArr = netherlandsFlag1(arr, 0, arr.length - 1);
        process2(arr, L, midArr[0] - 1);
        process2(arr, midArr[1] + 1, R);
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
    public static int[] netherlandsFlag1(int[] arr, int L, int R) {
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


    public static int[] netherlandsFlag2(int[] arr, int L, int R) {
        if (L > R) {
            return new int[]{-1, -1};
        }
        if (L == R) {
            return new int[]{L, R};
        }
        int less = L - 1;
        int index = L;
        int more = R + 1;
        int val = arr[R];
        while (index < more) {
            if (arr[index] == val) {
                index++;
            } else if (arr[index] > val) {
                swap(arr, index, --more);
            } else {
                swap(arr, index++, ++less);
            }
        }
        return new int[]{less + 1, more};
    }
}
