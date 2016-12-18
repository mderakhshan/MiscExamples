package com.mir.exercise1;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Mir on 10/09/2016.
 */
public class FileAnalyzer {

    public static void main (String[] args) {
        Scanner in = new Scanner (System.in);

        String filename = "C:\\Users\\Mir\\Desktop\\test.txt";
        try {
            Map<String, Integer> resultMap = analyzeFile(filename);
            System.out.println (resultMap);
        }
        catch (FileNotFoundException e){
            System.out.println ("Error: file " + filename + " does not exist!");
        }
    }

    public static Map<String, Integer> analyzeFile (String filename) throws FileNotFoundException {
        Map<String, Integer> map = new LinkedHashMap();
        int lineCount = 0;
        int wordCount = 0;
        int charCount = 0;

        if (StringUtils.isEmpty (filename))
            return map;

        Scanner in = new Scanner (new File (filename));

        while (in.hasNext()) {
            String line = in.nextLine();
            ++lineCount;
            charCount += line.length();
            wordCount += countWords(line);
        }

        map.put ("Lines", lineCount);
        map.put ("Words", wordCount);
        map.put ("Chars", charCount);

        return map;
    }

    private static int countWords(String line) {
        if (StringUtils.isEmpty (line)) {
            return 0;
        }
        else {
            String[] strArray = line.split(" ");

            // List<String> list = array.stream().filter(s1 -> !StringUtils.isEmpty(s1)).collect(Collectors.toList());
            long count = Arrays.asList(strArray)
                    .stream()
                    .filter(s1 -> !StringUtils.isEmpty(s1))
                    .collect(Collectors.counting());
            return (int) count;
        }
    }
}
