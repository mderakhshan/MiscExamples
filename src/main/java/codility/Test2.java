package codility;

import java.util.Arrays;

/**
 * Created by Mir on 13/10/2016.
 */
public class Test2 {
    static int[] fib;

    public static void setUpFibocacci (int size) {
        fib = new int[size];
        int fibNminus1 = 0;
        int fibNminus2 = 1;
        fib[0] = fibNminus1;
        fib[1] = fibNminus2;

        int fibonacci;
        for (int i=2; i < size; ++i) {
            fibonacci = fibNminus1 + fibNminus2;
            fib[i] = fibonacci;
            fibNminus2 = fibNminus1;
            fibNminus1 = fibonacci;
        }
    }

    public static boolean isPalindorme (char[] arr) {
        if (arr == null || arr.length == 0) {
            return false;
        }
        StringBuilder sb = new StringBuilder (Arrays.toString(arr));
        return sb.toString().equals (sb.reverse().toString());
    }
}
