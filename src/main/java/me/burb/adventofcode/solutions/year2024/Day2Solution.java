package me.burb.adventofcode.solutions.year2024;

import me.burb.adventofcode.AdventOfCodeSolution;
import me.burb.adventofcode.Solution;

import java.util.Arrays;
import java.util.function.Predicate;

@AdventOfCodeSolution(year = 2024, day = 2, name = "Red-Nosed Reports", link = "https://adventofcode.com/2024/day/2")
public class Day2Solution extends Solution {

    @Override
    public Object part1Solution() {
        return countSafeReports(this::isValidPartOne);
    }

    @Override
    public Object part2Solution() {
        return countSafeReports(this::isValidPartTwo);
    }

    private int countSafeReports(Predicate<int[]> predicate) {
        return (int) Arrays.stream(getInputLines())
                .map(line -> Arrays.stream(line.split(" "))
                        .mapToInt(Integer::parseInt)
                        .toArray())
                .filter(predicate)
                .count();
    }

    private boolean isValidPartOne(int[] row) {
        return isRowSafe(row);
    }

    private boolean isValidPartTwo(int[] row) {
        return isRowSafe(row) || canDampen(row);
    }

    private boolean isRowSafe(int[] row) {
        boolean isIncreasing = row[0] < row[1];

        for (int i = 0; i < row.length-1; i++) {
            if (!isSafe(row[i], row[i+1], isIncreasing))
                return false;
        }
        return true;
    }

    private boolean canDampen(int[] row) {
        for (int i = 0; i < row.length; i++) {
            int[] copy = remove(row, i);
            if (isRowSafe(copy)) return true;
        }
        return false;
    }

    private boolean isSafe(int a, int b, boolean increasing) {
        if (a == b) return false;
        if (a > b && increasing) return false;
        if (a < b && !increasing) return false;

        int diff = Math.abs(a - b);
        return diff <= 3 && diff >= 1;
    }

    public static int[] remove(int[] arr, int index) {
        if (arr == null || index < 0 || index >= arr.length) {
            return arr;
        }

        int[] result = new int[arr.length - 1];
        for (int i = 0, j = 0; i < arr.length; i++) {
            if (i != index) {
                result[j++] = arr[i];
            }
        }
        return result;
    }

}
