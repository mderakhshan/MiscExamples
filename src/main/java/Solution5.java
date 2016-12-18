// you can also use imports, for example:
// import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");


import java.util.ArrayList;
import java.util.List;

class Solution5 {
    public boolean solution(String S, String T) {
        // write your code in Java SE 8

        if (S == null || S.length() == 0 || T == null || T.length() == 0) {
            return false;
        }

        List<Character> listForS = convertToListOfChar(S);
        List<Character> listForT = convertToListOfChar(T);
        if (listForS.size() != listForT.size()) {
            return false;
        }

        boolean match = true;
        for (int i=0; i < listForS.size(); ++i) {
            char s = listForS.get(i);
            char t = listForT.get(i);
            if (s != '?' && t != '?' && s != t) {
                match = false;
                break;
            }
        }
        return match;
    }

    static List<Character> convertToListOfChar (String s) {
        List<Character> list = new ArrayList<Character>();
        int i = 0;
        while (i < s.length()) {
            char c = s.charAt(i);
            StringBuilder sb = new StringBuilder();
            while (Character.isDigit(c) && i < s.length()) {
                sb.append(c);
                ++i;
                if (i < s.length()) {
                    c = s.charAt(i);
                }
            }
            int nQ = 0;
            if (sb.length() != 0) {
                nQ = Integer.parseInt(sb.toString());
            }
            if (nQ > 0) {
                for (int j=1; j <= nQ; ++j) {
                    list.add ('?');
                }
            }
            else {
                list.add(c);
            }
            ++i;
        }
        return list;
    }

    public static void main (String[] args) {
        Solution5 s = new Solution5();
        System.out.println ("test1=" + s.solution("A2Le", "2pL1"));
        System.out.println ("test2=" + s.solution("a10", "10a"));
    }
}