/**
 * Created by Mir on 10/10/2016.
 */
public class Solution1 {

    public String solution (String S) {
        if (S.length() < 2) {
            return S;
        }
        StringBuilder sb = new StringBuilder();
        int counter = 0;
        char c;
        for (int i=0; i < S.length(); ++i) {
            if (counter == 3) {
                sb.append('-');
                counter = 0;
            }
            c = S.charAt(i);
            if (c != ' ' && c != '-') {
                sb.append(c);
                ++counter;
            }
        }
        return sb.toString();
    }

    public static void main (String args[]) {
        Solution1 soln = new Solution1();
        String s1 = "00-44 48 6666 8361";
        String s2 = soln.solution (s1);
        System.out.println ("String=" + s2);

        s1 = "0 - 22 1985--324";
        s2 = soln.solution (s1);
        System.out.println ("String=" + s2);

        s1 = "555726726276";
        s2 = soln.solution (s1);
        System.out.println ("String=" + s2);

        s1 = "555-726-726-276";
        s2 = soln.solution (s1);
        System.out.println ("String=" + s2);
    }
}
