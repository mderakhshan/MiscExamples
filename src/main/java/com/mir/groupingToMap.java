package com.mir;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by Mir on 20/10/2016.
 */
public class groupingToMap {

    public static void main (String[] args) {

        int[] arr = {2,5, 4, 6, 7, 9, 5, 3, 11, 6, 6};

        Map<Integer, List<Integer>> result1 =
                IntStream.of(arr).boxed().collect(Collectors.groupingBy(i->i));

        System.out.println ("Map = " + result1);

        List<Integer> distinct = IntStream.of(arr).boxed().sorted((i, j) -> Integer.compare(j, i)).distinct().collect(Collectors.toList());
        System.out.println ("Distinct and sorted descenfding = " + distinct);

        int max = IntStream.of(arr).max().orElse(0);
        System.out.println ("max " + max);

        Map<Integer, Long> result2 =
                IntStream.of(arr).boxed().collect(Collectors.groupingBy(i->i, Collectors.counting()));
        System.out.println ("Map = " + result2);

        IntSummaryStatistics stats = IntStream.of(arr).summaryStatistics();
        System.out.println ("Stats = " + stats);

        String s = "00010001000011101";
        int steps = countSteps (s);
        System.out.println ("steps = " + steps);
    }

    private static int countSteps(String s) {
        int pos = 0;
        while (pos < s.length()) {
            if (s.charAt(pos) == '0')
                ++pos;
            else
                break;
        }
        String str =s.substring(pos);
        if (str == null || str.length() == 0) {
            return  0;
        }

        Map<Integer, Long> result3 = str.chars().boxed().
                collect(Collectors.groupingBy(c->c, Collectors.counting()));
        long steps = result3.get((int) '0') == null ? 0L :  result3.get((int) '0');
        steps += result3.get((int) '1') ==null ? 0L : result3.get((int) '1') * 2;
        if (str.charAt(0) == '1') {
            --steps;
        }
        return (int) steps;
    }
}
