package sort;

import java.util.Arrays;

/**
 * Created by Mir on 19/10/2016.
 */
public class MergeSort {

    public static int[] mergeSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return arr;
        }
        if (arr.length == 2) {
            int[] sorted = new int[2];
            if (arr[0] < arr[1]) {
                sorted [0] = arr[0];
                sorted [1] = arr[1];
            }
            else {
                sorted [0] = arr[1];
                sorted [1] = arr[0];
            }
            return sorted;
        }
        int mid = arr.length/2;
        int[] leftArray = Arrays.copyOfRange(arr, 0, mid);
        int[] rightArray = Arrays.copyOfRange(arr, mid, arr.length);

        int[] leftMergedList = mergeSort (leftArray);
        int[] rightMergedList = mergeSort(rightArray);

        int[] mergedList = mergeSortedList (leftMergedList, rightMergedList);
        return mergedList;
    }

    private static int[] mergeSortedList(int[] left, int[] right) {
        int[] mergedList = new int[left.length + right.length];
        int i=0;
        int j = 0;
        int k = 0;
        while (i < left.length && j < right.length) {
            if (left[i] < right[j]) {
                mergedList[k++] = left[i];
                ++i;
            } else {
                mergedList[k++] = right[j];
                ++j;
            }
        }
        if (i < left.length) {
            for (int x=i; x < left.length; ++x) {
                mergedList[k++] = left[x];
            }
        }
        if (j < right.length) {
            for (int x=j; x < right.length; ++x) {
                mergedList[k++] = right[x];
            }
        }
        return mergedList;
    }
}
