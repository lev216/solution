package com.tsystems.javaschool.tasks.pyramid;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PyramidBuilder {

    /**
     * Builds a pyramid with sorted values (with minumum value at the top line and maximum at the bottom,
     * from left to right). All vacant positions in the array are zeros.
     *
     * @param inputNumbers to be used in the pyramid
     * @return 2d array with pyramid inside
     * @throws {@link CannotBuildPyramidException} if the pyramid cannot be build with given input
     */
    public int[][] buildPyramid(List<Integer> inputNumbers) {
        // TODO : Implement your solution here
        try {
            Collections.sort(inputNumbers);
        } catch (NullPointerException exc) {
            throw new CannotBuildPyramidException("В последовательности есть null! Введите иную последовательность");
        } catch (OutOfMemoryError err) {
            throw new CannotBuildPyramidException("Пирамида не построится! Закончилась память");
        }
        int sum = 0;
        int number = 0;
        while (true) {
            number++;
            sum += number;
            if (sum == inputNumbers.size()) {
                break;
            }
        }
        int column = number * 2 - 1;
        int row = 0;
        if (inputNumbers.size() >= 10) {
            row = (int) (Math.sqrt(inputNumbers.size()) + (double) inputNumbers.size() / 10);
        } else {
            row = (int) Math.sqrt(inputNumbers.size()) + 1;
        }

        try {
            int[][] result = new int[row][column];
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < column; j++) {
                    result[i][j] = 0;
                }
            }
            result[0][column/2] = inputNumbers.get(0);

            int i = 1;
            int n = 1;

            while (i < row) {
                int j = 0;
                while (j <= i * 2) {
                    result[i][column / 2 - i + j] = inputNumbers.get(n);
                    n++;
                    j += 2;
                }
                i++;
            }
            return result;
        } catch (NegativeArraySizeException exc) {
            throw new CannotBuildPyramidException("Пирамида не построится! Введите иную последовательность");
        } catch (OutOfMemoryError err) {
            throw new CannotBuildPyramidException("Пирамида не построится! Закончилась память");
        }
    }
}
