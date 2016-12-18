package sort;

import java.util.Arrays;

import static sort.MergeSort.mergeSort;
import static sort.QuickSort.quickSort;

/**
 * Created by Mir on 19/10/2016.
 */
public class SortMain {
    public static void main (String[] args) {
//        int[] arr = new int[] {23, 12, 98, 45, 32, 65, 200, 100};
        int[] arr = new int[] {23, 12, 98, 45, 32};
        int[] sorted = mergeSort (arr);
        System.out.println ("Merge Sort:" + Arrays.toString(sorted));

        quickSort (arr, 0, arr.length-1);
        System.out.println ("Quick Sort:" + Arrays.toString(arr));
    }
}
