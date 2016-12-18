package codility;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mir on 27/10/2016.
 */
public class Factorial {

    public static long factorialI (int n) {
        if (n <=0)
            return 0L;
        long f = 1;
        for (int i = 2; i <= n; ++i) {
            f *= (long) i;
        }
        return f;
    }

    public static long factorialR (int n) {
        if (n <= 0)
            return 0L;
        if (n == 1)
            return 1L;
        long f = n * factorialR (n-1);
        return f;
    }

    public static int calcMaxFactorial () {
        int i = 1;
        while (true) {
            if (factorialR (i) < (long) Integer.MAX_VALUE) {
                ++i;
            }
            else {
                break;
            }
        }
        return i;
    }

    public static void main (String[] args) {
        System.out.println ("factorial I = " + factorialI(14));
        System.out.println ("factorial R = " + factorialR(14));
        System.out.println ("maxFactorial = " + calcMaxFactorial() + " MAX_VALUE = " + Integer.MAX_VALUE);

        Map<Integer, String> map = new HashMap<>(100, 0.75f);

        String s= "hell0";
        Character.isUpperCase (s.charAt(0));

    }
}
