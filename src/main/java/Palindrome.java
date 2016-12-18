import java.util.Arrays;

/**
 * Created by Mir on 24/10/2016.
 */
public class Palindrome {

    public static void main (String[] args) {
        String s = "hello there this is a pallap isnt it?";
        String palindrome = findMaxLengthPalindrome (s);

        boolean b = isAnagram ("abcde", "edbcA");
        System.out.print("Angaram=" + b);

        s = "welcometojava";
        System.out.print("Max so far = " + findMaxSubstring (s, 3));
    }

    public static String findMaxLengthPalindrome (String s) {
        if (s == null || s.length() == 0) {
            return null;
        }

        String str = null;
        int k = s.length()-1;
        boolean searching = true;
        while (k > 0 && searching) {
            for (int i = 0; i+k < s.length(); ++i) {
                str = s.substring(i, i + k + 1);
                if (isPalindrome(str)) {
                    searching = false;
                    break;
                }
            }
            --k;
        }
        return str;
    }

    private static boolean isPalindrome (String s) {
        if (s == null || s.length() == 0) {
            return false;
        }
        return s.equals (new StringBuilder(s).reverse().toString());
    }

    private static boolean isAnagram(String a, String b) {
        if (a == null || b == null) {
            return false;
        }

        char[] arrA = a.toLowerCase().toCharArray();
        Arrays.sort(arrA);

        char[] arrB = b.toLowerCase().toCharArray();
        Arrays.sort(arrB);

        String s = arrB.toString();

        return Arrays.equals(arrA, arrB);
    }

    private static String findMaxSubstring (String s, int k) {
        if (s == null || k > s.length()) {
            return "";
        }
        String maxSofar = s.substring(0, k);
        for (int i=1; i+k < s.length(); ++i) {
            if (maxSofar.compareTo(s.substring(i, i+k)) < 0) {
                maxSofar = s.substring(i, i+k);
            }
        }
        return maxSofar;
    }
}
