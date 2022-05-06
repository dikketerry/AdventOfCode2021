package io.eho.adventofcode2021.days;

import io.eho.adventofcode2021.Day;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// count depth increases from scanner log file (see day01.txt in resources)

public class Day01 implements Day {

    @Override
    public String part1(List<String> input) {
        System.out.println("running part1");    // to check we're in part 1

        List<Integer> intList = convertToIntList(input);
        int increases = countIncreases(intList);
    return String.valueOf(increases);
    }

    @Override
    public String part2(List<String> input) {
        System.out.println("running part2");    // to check we're in part 2

        List<Integer> intList = convertToIntList(input);

        List<Integer> listSums = new ArrayList<>();
        for (int i = 0; i < intList.size() - 2; i++) {
            int sum = intList.get(i) + intList.get(i + 1) + intList.get(i + 2);
            listSums.add(sum);
        }

        int increases = countIncreases(listSums);
        return String.valueOf(increases);
    }

    // help method to convert String list to Integer list
    private List<Integer> convertToIntList(List<String> stringList) {
        return stringList.stream().map(Integer::parseInt).collect(Collectors.toList());
    }

    // help method to count # increases in Integer list
    private int countIncreases(List<Integer> intList) {
        int increases = 0;
        for (int i = 0; i < intList.size() - 1; i++) {
            if (intList.get(i + 1) > intList.get(i)) increases++;
        }
        return increases;
    }

}
