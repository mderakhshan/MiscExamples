package queue;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by Mir on 08/10/2016.
 */
public class QueueExample {

    public static void minMaxMode(int[] array) {
        int min = -1, max = -1, mode = -1, average = 1;

        if (array == null) {
            return;
        }

        List<Integer> list = IntStream.of(array).boxed().collect(Collectors.toList());
        List<Integer> list1 = list.stream().collect(Collectors.toList());
        min = list.stream().min(Integer::compareTo).get();
        max = list.stream().max(Integer::compareTo).get();

        List<Integer> sortedList = list.stream().sorted((i1, i2)->Integer.compare (i1, i2)).collect(Collectors.toList());
        int modeCountSofar=1;
        mode = sortedList .get(0);
        int counter = 1;
        for (int i=1; i < sortedList.size()+1; ++i) {
            if (i !=  sortedList.size() && (sortedList.get(i) == sortedList.get(i-1))) {
                ++counter;
            }
            else {
                if (counter > modeCountSofar) {
                    mode = sortedList.get(i - 1);
                    modeCountSofar = counter;
                }
                counter = 1;
            }
        }
        System.out.print("Min=" + min + " max=" + max + " mode=" + mode);

        List<Integer> list2 = IntStream.of(array).boxed().collect(Collectors.toList());
        min = IntStream.of(array).min().getAsInt();
        max = IntStream.of(array).max().getAsInt();
        OptionalDouble x = IntStream.of(array).average();
        if (x.isPresent()) {
             average = (int) x.getAsDouble();
        }

    }

    public static void main(String[] args) throws Exception {
        //int[] arr = { 1, 2, 3, 10, 4, 5, 6, 7, 8, 9, 10 };
        int[] arr = { 1, 2, 2};

        List<Integer> list = IntStream.of(arr).boxed().collect(Collectors.toList());
        int max = list.stream().max(Integer::compareTo).get();
        IntSummaryStatistics stats = IntStream.of(arr).summaryStatistics();

        stats = list.stream().mapToInt(x->x).summaryStatistics();

        minMaxMode(arr);

        //
        // Some queue stuff

        Class<?> cls = Class.forName("java.lang.String");
        Object instance = cls.newInstance();
        String s = (String) instance;

        String words = "word word2 word3 word4";
        String[] data2 = words.split("\\s+");
    }

    public class priorityQTest {

        public class Staff {
            String name;
            Integer priority;
        }

        Comparator<Staff> PQsort = new Comparator<Staff>() {
            @Override
            public int compare(Staff s1, Staff s2) {
                return s2.priority - s1.priority;
            }
        };

        void test () {
            Queue<priorityQTest.Staff> queue = new PriorityQueue<priorityQTest.Staff> (10, PQsort);
        }
    }
}
