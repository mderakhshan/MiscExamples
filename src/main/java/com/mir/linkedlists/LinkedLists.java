package com.mir.linkedlists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Created by Mir on 24/09/2016.
 */
public class LinkedLists {

    public static void main (String[]  args) {

        ArrayList<Integer> list = new ArrayList<> (Arrays.asList(20, 10, 30, 90, 15));

        int sum = list.stream().collect(Collectors.summingInt(n->n));
        int sum2 = list.stream().collect(Collectors.summingInt(n->n));
        int sum3 = list.stream().mapToInt(n->n).sum();
        int sum4 = list.stream().reduce(0, Integer::sum);
        int sum5 = list.stream().reduce (0, (a,b) -> a+b);
        int doublesum = list.stream().map(n->2*n).reduce(0, (a,b) -> a+b);

        System.out.println("sum=" + sum + " sum2=" + sum2 +" sum3=" + sum3 +" sum4=" + sum4 +" sum5" + sum5 +" doublesum=" + doublesum);
    }
}
