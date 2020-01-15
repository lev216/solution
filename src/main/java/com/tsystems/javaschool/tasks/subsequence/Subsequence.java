package com.tsystems.javaschool.tasks.subsequence;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Subsequence {

    /**
     * Checks if it is possible to get a sequence which is equal to the first
     * one by removing some elements from the second one.
     *
     * @param x first sequence
     * @param y second sequence
     * @return <code>true</code> if possible, otherwise <code>false</code>
     */
    @SuppressWarnings("rawtypes")
    public boolean find(List x, List y) {
        // TODO: Implement the logic here

        if (x != null && y != null) {
            if (!x.isEmpty()) {
                int count = 0;
                for (int i = 0; i < x.size(); i++) {
                    for (int j = count; j < y.size(); j++) {
                        count++;
                        if (!x.get(i).equals(y.get(j))) {
                            y.set(j, null);
                        } else {
                            break;
                        }
                    }
                }
                for (int i = count; i < y.size(); i++) {
                    y.set(i, null);
                }
                ArrayList<Object> copyY = new ArrayList<>();
                for (int i = 0; i < y.size(); i++) {
                    if (y.get(i) != null) {
                        copyY.add(y.get(i));
                    } else {
                        continue;
                    }
                }
                return x.equals(copyY);
            } else {
                return true;
            }
        } else {
            throw new IllegalArgumentException();
        }
    }
}
