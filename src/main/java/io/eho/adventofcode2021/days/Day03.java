package io.eho.adventofcode2021.days;

import io.eho.adventofcode2021.Day;

import java.util.List;

public class Day03 implements Day {
    @Override
    public String part1(List<String> input) {

        StringBuilder gammaBuilder = new StringBuilder();

        for (int h = 0; h < 12; h++) {
            int sumZero = 0;
            int sumOne = 0;

            for (String s : input) {
                if (s.charAt(h) == '1') {
                    sumOne++;
                } else sumZero++;
            }

            if (sumOne > sumZero) {
                gammaBuilder.append("1");
            } else {
                gammaBuilder.append("0");
            }
        }

        int gamma = computeDecimal(gammaBuilder.toString());
        int epsilon = 4095 - gamma;
        int powerConsumption = gamma * epsilon;

        return String.valueOf(powerConsumption);
    }

    @Override
    public String part2(List<String> input) {
        return null;
    }

    private int computeDecimal(String binary) {
        return Integer.parseInt(binary, 2);
    }
}

/*
part 1
- we need to create a construct in which 0's and 1's are summed per 'column'
(index) of the elements in the input list. this means:
- have an outer loop of max length: length of list elements (they all have
the same length: 12)
- have an inner loop of max length input list length
- a new string gamma needs to be build based on outcome of summed 0's / 1's.
- the 2nd string epsilon is the opposite of the binary gamma string:
    gamma:      110001101010    (3178)
    epsilon:    001110010101    (917)
- or, as the length of the binary is 12 digits, and the maximum number
reflected by a 12 digit binary = 4095, if gamma decimal reflects 3178, that
means that epsilon will reflect 4095 - 3178
 */
