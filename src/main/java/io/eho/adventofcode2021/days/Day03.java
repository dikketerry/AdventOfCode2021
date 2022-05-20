package io.eho.adventofcode2021.days;

import io.eho.adventofcode2021.Day;

import java.util.Arrays;
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

        int liLength = input.size();
        int elLength = input.get(0).length();

        // convert List to Array and sort it
        String[] inputArr = input.toArray(new String[liLength]);
        Arrays.sort(inputArr);

        // println for diagnostics / check if sorting has expected result
        int count = 0;
        for (String s : inputArr) {
            count++;
            System.out.println("line" + count + ": " + s);
        }

        int lowBorder = 0;          // low search range start at 0
        int highBorder = liLength;  // high search range start at liLength

        // more diagnostics, on help function below
        int x = searchFirstOne(inputArr, 0, lowBorder, highBorder);
        System.out.println(x);

        // perform search-loop within elLength AND only if there are more
        // than 1 elements to search in. the help method searchFirstOne is
        // used in each iteration to set the new low- or highBorder to narrow
        // down to 1 remaining binary number.
        for (int i = 0; i < elLength && highBorder - lowBorder > 1; i++) {

            // compute at digit (column) i the 1st binary starting with '1'
            int firstOne = searchFirstOne(inputArr, i, lowBorder, highBorder);

            // if 1s are equal to or more than 0s, this is reflected by...
            if (highBorder - firstOne >= (highBorder - lowBorder + 1) / 2) {
                lowBorder = firstOne; // ...therefore set lowBorder @
                // firstOne in order to set range to 1x.. binaries only
            }
            // if 1s are not equal to or more than 0s...
            else {
                highBorder = firstOne; // ...set highBorder @ firstOne in
                // order to set range to 0x.. binaries only
            }
        }
        int oxygen = Integer.parseInt(inputArr[lowBorder], 2);

        // similar for co2
        lowBorder = 0;
        highBorder = liLength;
        for (int i = 0; i < elLength && highBorder - lowBorder > 1; i++) {
            int firstOne = searchFirstOne(inputArr, i, lowBorder, highBorder);

            if (highBorder - firstOne >= (highBorder - lowBorder + 1) / 2) {
                highBorder = firstOne; // take firstOne now as upper limit of
                // new range in order to search in 0x.. binaries only
            } else {
                lowBorder = firstOne;
            }
        }
        int co2 = Integer.parseInt(inputArr[lowBorder], 2);
        int lifeSupport = oxygen * co2;

        return String.valueOf(lifeSupport);
    }

    private int searchFirstOne(String[] inputArr, int columnIndex,
                               int lowBorder, int highBorder) {

        while (lowBorder < highBorder) {
            int middle = lowBorder + (highBorder - lowBorder) / 2;

            if (inputArr[middle].charAt(columnIndex) == '1') {
                highBorder = middle;
            } else lowBorder = middle + 1;
        }
        return lowBorder; // returns columnIndex of sorted array the first
        // element starting with 1
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

part2
- we need to create a construct in which we are able to assess if a certain
'digit-column' (index) contains more 1's or more 0'. based on that, binaries
containing the 'more' part are used to compute the binary that sets the
oxygen rating; the binaries with the 'less' part are used to compute
the binary that sets the co2 scrubber rating.
- check if '1' or '0' is represented more ->
- take the range of binaries starting with the 'mores' as next input -->
- check again if '1' or '0' is represented more ->
- take the range of binaries starting with the 'mores' as next input -->
- repeat until 1 binary is left -> this is the binary for oxygen rating
- same procedure for co2 scrubber rating but taking the 'lesses'
- if 0s and 1s are equal:
    - for oxygen, take the binaries with '1'
    - for co2, take the binaries with '0'
*** by converting the list into an array and then sort it, we allow a binary
search (halving the scope of search by taking the middle element and compare
it) and make it more efficient
 */
