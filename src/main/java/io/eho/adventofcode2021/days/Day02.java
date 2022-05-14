package io.eho.adventofcode2021.days;

import io.eho.adventofcode2021.Day;

import java.util.List;

public class Day02 implements Day {

    int horizontal, vertical, aim;

    @Override
    public String part1(List<String> input) {

        for (String s : input) {
            char c = s.charAt(0);

            if (c == 'f') {
                horizontal += Integer.parseInt(s.substring(8));
            } else if (c == 'u') {
                vertical -= Integer.parseInt(s.substring(3));
            } else if (c == 'd') {
                vertical += Integer.parseInt(s.substring(5));
            }
        }

        return String.valueOf(horizontal * vertical);
    }

    @Override
    public String part2(List<String> input) {

        for (String s : input) {
            char c = s.charAt(0);

            if (c == 'f') {
                Integer a = Integer.parseInt(s.substring(8));
                horizontal += a;
                vertical += a * aim;
            } else if (c == 'u') {
                aim -= Integer.parseInt(s.substring(3));
            } else if (c == 'd') {
                aim += Integer.parseInt(s.substring(5));
            }
        }

        return String.valueOf(horizontal * vertical);
    }

    /*
    part 1
    input: ["forward 5", "up 3", "down 2" etc.]
    1. set a vertical and a horizontal int
    2a if list[element] starts with "f" >> horizontal
     1. parse all that comes @index 8 to int
     2. sum to horizontal
    2b if list[element] starts with "u" >> vertical
     1. parse all that comes @index 3 to int
     2. subtract from vertical
    2c if list[element] starts with "d" >> vertical
     1. parse all that comes @index 5 to int
     2. sum to vertical
    3. multiply horizontal with vertical

    part 2
    1. set a vertical, a horizontal and an aim int
    2a if list[element] starts with "f" >> horizontal & vertical
     1. parse all from @index8 to int x
     2. sum int x to horizontal
     3. multiply int x with aim and add total to vertical
    2b if list[element] starts with "u" >> aim
     1. parse all that comes @index 3 to int
     2. subtract from aim
    2c if list[element] starts with "d" >> aim
     1. parse all that comes @index 5 to int
     2. sum to aim
    */
}
