package com.mir.java8lambdafeatures;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by Mir on 12/09/2016.
 */
public class LambdaFeatures {
    private static Predicate<Integer> gt10lt30 = i -> i>10 && i<30;
    private static Comparator<Integer> intComparator = (i1, i2) -> Integer.compare (i1, i2);

    public static void main (String[] args) {
        example1();
    }

    private static void example1 () {
        List<Integer> list = new ArrayList<>();
        list.add (10);
        list.add (20);
        list.add (25);
        list.add (30);

       // list.stream().filter(i-> i>10 && i<30).forEach(i -> System.out.println (i));
        list.stream().filter(gt10lt30).forEach(i -> System.out.println (i));

        List<Integer> list2 =
                list.stream()
                .filter(gt10lt30)
                .sorted((i1, i2) -> Integer.compare(i2, i1))
                .collect(Collectors.toList());
         System.out.println ("list2 =" + list2);

        List<Integer> list3 =
                list.stream()
                        .filter(gt10lt30)
                        .sorted(intComparator)
                        .collect(Collectors.toList());
        System.out.println ("list3 =" + list2);

        List<Integer> list4 = new ArrayList (list);
        Collections.sort (list4, (i1, i2)->Integer.compare (i2, i1)); // or Collections.sort (list, intComparator);
        System.out.println ("list4 =" + list4);
        System.out.println ("list =" + list);

        Optional<Integer> reduce = list.stream()
                .filter(gt10lt30)
                .sorted((i1, i2) -> Integer.compare(i2, i1))
                .reduce((i1, i2) -> Integer.compare(i1, i2));
        System.out.println ("Reduce =" + reduce);

        Map <Integer, Integer> map =
                list.stream()
                        .filter(gt10lt30)
                        .sorted((i1, i2) -> Integer.compare(i2, i1))
                        .collect(Collectors.toMap(i1 -> i1, i2 -> i2));
        System.out.println ("Map =" + map);

    }
}
