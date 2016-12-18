package com.mir.streams;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public class Streams {
    public static void main(String args[]){

        Streams java8Tester = new Streams();
        Integer value1 = null;
        Integer value2 = new Integer(10);

        //Optional.ofNullable - allows passed parameter to be null.
        Optional<Integer> a = Optional.ofNullable(value1);

        //Optional.of - throws NullPointerException if passed parameter is null
        Optional<Integer> b = Optional.of(value2);
        System.out.println(java8Tester.sum(a,b));

        //
        List<Integer> list = new ArrayList (Arrays.asList (1, 20, 20,  15, 8, 15, 9, 10, 13, 10));


        // one way of sorting - using collections
        List<Integer> list1 = new ArrayList (list);
        Collections.sort (list1, (i1, i2)->Integer.compare (i2, i1));
        System.out.println("list1: " + list1);

        // Another way of sorting - using stream
        List<Integer> list2 = list.stream().sorted((i1, i2)->Integer.compare (i2, i1)).collect(Collectors.toList());
        System.out.println("list2: " + list2);

        // Yet another way of sorting - using streams
        Comparator<Integer> integetComparator = (i1, i2)->Integer.compare (i2, i1);
        List<Integer> list3 = list.stream().sorted(integetComparator).collect(Collectors.toList());
        System.out.println("list3: " + list3);

        // find maximum
        int max1 = list.stream().reduce(-1, Integer::max);
        System.out.println("max1: " + max1);

        // find any element grreater than 5
        Optional<Integer> number = list.stream().filter(n -> n > 5).findFirst();
        System.out.println("number: " + number.orElse(new Integer(0)));
        long count1 = list.stream().filter(n -> n > 5).count();
        long count2 = list.stream().filter(n -> n > 5).collect(Collectors.counting());
        System.out.println("Count1: " + count1);
        System.out.println("Count2: " + count2);

        Map<Integer, String> map = list.stream()
                .distinct()
                .collect(Collectors.toMap(t -> t, t -> t.toString()));
        System.out.println("Map: " + map);

        // List of distinct integers
        List<Integer> distinct = list.stream().distinct().collect(Collectors.toList());
        // Listo of distinct strings
        List<String> strlist = Arrays.asList ("hello", "world","hello",  "there", "world");
        List<String> distinctStr = strlist.stream().distinct().collect(Collectors.toList());

        Map<Integer, Integer> map2 = list.stream()
                .collect(Collectors.toMap(n -> n, n -> 1, Integer::sum));
        System.out.println("Map2: " + map2);

        Map<Integer, Long> map3 = list.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.<Integer> counting()));
        System.out.println("Map3: " + map3);

        // example of map and limit
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
        List<Integer> twoEvenSquares =
                 numbers.stream()
                        .filter (n -> n % 2 == 0)
                        .map(n -> n * n).filter (n -> n % 2 == 0)
                        .limit(2)
                        .collect(Collectors.toList());
        System.out.println("Fist two squared numbers that are even: " + twoEvenSquares);

        // Example of reduce
        Integer sumOftwoEvenSquares =
                numbers.stream()
                        .filter (n -> n % 2 == 0)
                        .map(n -> n * n).filter (n -> n % 2 == 0)
                        .limit(2)
                        .reduce(0, (n, m) ->  n + m);
        System.out.println("Fist two squared numbers that are even: " + sumOftwoEvenSquares);

        // example of mapToInt
        sumOftwoEvenSquares =
                numbers.stream()
                        .filter (n -> n % 2 == 0)
                        .map(n -> n * n).filter (n -> n % 2 == 0)
                        .limit(2)
                        .mapToInt(n -> n)   // This enables us to use sum() to calculate sum
                        .sum();
        System.out.println("Fist two squared numbers that are even: " + sumOftwoEvenSquares);

        // example of summingInt
        sumOftwoEvenSquares =
                numbers.stream()
                        .filter (n -> n % 2 == 0)
                        .map(n -> n * n).filter (n -> n % 2 == 0)
                        .limit(2)
                        .collect(Collectors.summingInt(n -> n));
        System.out.println("Using summingInt: Fist two squared numbers that are even: " + sumOftwoEvenSquares);

        System.out.println("Fist two squared numbers that are even: " + sumOftwoEvenSquares);
        // Example of forEach
        numbers.stream().filter (n -> n % 2 == 0).forEach(System.out::println);

        // Example of flatmap
        String[] strArray = {"HELLO", "WORLD"};
        List<String> strList = Arrays.asList (strArray);
        List<String> strList2 = strList.stream()
                .map(s -> s.split(""))
                .flatMap (Arrays::stream)
                .collect(Collectors.toList());
        System.out.println("strList2: " + strList2);


    }

    private static Collector<? super Object, Object, Object> counting() {
        return null;
    }

    public void examplesOfCollectors () {

//        // Accumulate names into a List
//        List<String> list = people.stream().map(Person::getName).collect(Collectors.toList());
//
//        // Accumulate names into a TreeSet
//        Set<String> set = people.stream().map(Person::getName).collect(Collectors.toCollection(TreeSet::new));
//
//        // Convert elements to strings and concatenate them, separated by commas
//        String joined = things.stream()
//                .map(Object::toString)
//                .collect(Collectors.joining(", "));
//
//        // Compute sum of salaries of employee
//        int total = employees.stream()
//                .collect(Collectors.summingInt(Employee::getSalary)));
//
//        // Group employees by department
//        Map<Department, List<Employee>> byDept
//                = employees.stream()
//                .collect(Collectors.groupingBy(Employee::getDepartment));
//
//        // Compute sum of salaries by department
//        Map<Department, Integer> totalByDept
//                = employees.stream()
//                .collect(Collectors.groupingBy(Employee::getDepartment,
//                        Collectors.summingInt(Employee::getSalary)));
//
//        // Partition students into passing and failing
//        Map<Boolean, List<Student>> passingFailing =
//                students.stream()
//                        .collect(Collectors.partitioningBy(s -> s.getGrade() >= PASS_THRESHOLD));
    }
    public Integer sum(Optional<Integer> a, Optional<Integer> b){

        //Optional.isPresent - checks the value is present or not
        System.out.println("First parameter is present: " + a.isPresent());
        System.out.println("Second parameter is present: " + b.isPresent());

        //Optional.orElse - returns the value if present otherwise returns
        //the default value passed.
        Integer value1 = a.orElse(new Integer(0));

        //Optional.get - gets the value, value should be present
        Integer value2 = b.get();
        return value1 + value2;
    }
}