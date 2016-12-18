package codility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Created by Mir on 08/11/2016.
 */
public class Prime {
    public static int[] primeNumbers (int fromNum, int toNum) {
        if (toNum < fromNum || toNum == 0 || toNum == 1) {
            return new int[0];
        }

        boolean[] primes = new boolean[toNum - fromNum + 1];
        Arrays.fill(primes, true);
        if (fromNum < 2) {
            primes[1] = false;
        }
        if (fromNum < 1) {
            primes[0] = false;
        }

        ArrayList<Integer> arrList = new ArrayList<Integer>();
        for (int i = fromNum; i <= toNum; ++i) {
            if (primes[i - fromNum] == true) {
                for (int j = 2; i*j <= toNum; ++j) {
                    primes[i*j - fromNum] = false;
                }
            }
            if (primes[i - fromNum] == true) {
                arrList.add(i);
            }
        }
        return arrList.stream().mapToInt(i -> i).toArray();
    }

    public static void main (String[] args) {
        System.out.println(Arrays.toString(primeNumbers(0, 100)));
    }
}
