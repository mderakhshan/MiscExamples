package com.mir.collections;

import java.util.*;

/**
 * Created by Mir on 23/09/2016.
 */
public class ListsAndCollections {

    public static void  main (String[]  args) {

        // LinkedList<String> list = new LinkedList<>();
        // HashSet<String> list = new HashSet<>();
        // TreeSet<String> list = new TreeSet<>();
        Comparator<String> descendingOrder = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2) * 1;
            }
        };
        PriorityQueue<String> list = new PriorityQueue<>(10, descendingOrder);
        list.add ("Hello");
        list.add ("there");
        list.add ("life");
        list.add ("Albert");

        System.out.println (list);
    }

    public Set<Object> findDuplicates(List<Object> list) {
        Set<Object> items = new HashSet<Object>();
        Set<Object> duplicates = new HashSet<Object>();
        for (Object item : list) {
            if (items.contains(item)) {
                duplicates.add(item);
            } else {
                items.add(item);
            }
        }
        return duplicates;
    }

    public static <T extends Comparable<T>> void getDuplicates(T[] array) {
        Set<T> dupes = new HashSet<T>();
        for (T i : array) {
            if (!dupes.add(i)) {
                continue;
                //System.out.println("Duplicate element in array is : " + i);
            }
        }
        List<T> duplicates = new ArrayList<>();
        duplicates.addAll(dupes);
    }
}
