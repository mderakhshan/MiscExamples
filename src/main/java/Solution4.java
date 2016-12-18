// you can also use imports, for example:
// import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");

class Solution4 {
    public int solution(String S) {
        // write your code in Java SE 8

        if (S == null || S.length() == 0) {
            return 0;
        }

        int steps = 0;
        while (steps < S.length() && S.charAt(steps) == '0')
            ++steps;
        int k = steps;
        int i = S.length() - 1;
        while (i > k) {
            char c = S.charAt(i);
            if (c == '0')
                ++steps;

            else  // c == '1'
                steps += 2;
            --i;
        }
        return steps;
    }

    public static void main (String[] args) {
        Solution4 s = new Solution4();
        System.out.println("steps test1=" + s.solution ("011100"));
        System.out.println("steps test2=" + s.solution ("111100"));
        System.out.println("steps test3=" + s.solution ("001100"));
        System.out.println("steps test4=" + s.solution ("0011100"));
        System.out.println("steps test5=" + s.solution ("0111100"));
        System.out.println("steps test6=" + s.solution ("0001100"));
        System.out.println("steps test7=" + s.solution ("0"));
        System.out.println("steps test8=" + s.solution ("1"));

    }
}
