import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

/**
 * Created by Mir on 27/10/2016.
 */
public class MapReduce {

    public static void countWordOccurrances (String str) {
        if (str == null)
            return;

        Map<String, Long> collect =
                Stream.of(str.split(" ")).map(s->s.trim()).collect(groupingBy(Function.identity(), counting()));

        Comparator <Map.Entry<String, Long>> maxComparator = (e1, e2) -> e1.getValue().compareTo(e2.getValue());
        Optional<Map.Entry<String, Long>> maxElement = collect.entrySet().stream().max(maxComparator);

        long max = collect.entrySet().stream().mapToLong(e -> e.getValue()).max().orElse(0L);
        long max2 = collect.entrySet().stream().mapToLong(e -> e.getValue()).reduce(0L, (m,n)->Long.max(m, n));
        System.out.println("String element with max length = " + maxElement + " max = " + max + " max2 = " + max2);
    }

    public static void letterOccurrances (String str) {
        if (str == null)
            return;

        Map<Character, Long> map =
                str.chars()
                        .mapToObj(c -> (char) c)
                        .collect(groupingBy (Function.identity(), counting()));
        Comparator<Map.Entry<Character, Long>>
                maxCharComparator = (e1, e2) -> e1.getValue().compareTo(e2.getValue());
        Optional <Map.Entry<Character, Long>> maxCharEntry =
                map.entrySet().stream().max(maxCharComparator);
        long max = map.entrySet().stream().mapToLong(e->e.getValue()).max().orElse(0L);
        System.out.println("max entry=" + maxCharEntry + " max=" + max);
    }

    public static void main (String[] args) {
        countWordOccurrances("green yellow yellow green blue green red green red red");
        letterOccurrances("hehhhijshjhklooopxyzzzzzzzzzzzzzzzzzzzzzzzzzzz");
    }
}
