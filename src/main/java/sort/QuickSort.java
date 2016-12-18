package sort;

/**
 * Created by Mir on 19/10/2016.
 */
public class QuickSort {

    public static void quickSort (int[] arr, int low, int high) {
        if (arr == null || low == high-1) {
            return;
        }
        int middle = low + (high - low )/2;
        int pivot = arr[middle];
        int i = low;
        int j = high;

        while (i <= j) {
            while (arr[i] < pivot) {
                ++i;
            }
            while (arr[j] > pivot) {
                --j;
            }
            if (i <= j) { // swap values
                int temp = arr[j];
                arr[j--] = arr[i];
                arr[i++] = temp;
            }
        }
        if (low < j) {
            quickSort(arr, low, j);
        }
        if (i < high) {
            quickSort(arr, i, high);
        }
    }
}
