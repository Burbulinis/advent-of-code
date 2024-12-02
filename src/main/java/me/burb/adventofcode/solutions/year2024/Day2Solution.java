package me.burb.adventofcode.solutions.year2024;

import me.burb.adventofcode.AdventOfCodeSolution;
import me.burb.adventofcode.Solution;

import java.util.Arrays;

@AdventOfCodeSolution(year = 2024, day = 2, name = "Red-Nosed Reports", link = "https://adventofcode.com/2024/day/2")
public class Day2Solution extends Solution {
    @Override
    public Object part1Solution() {
        return Arrays.stream(getInputLines())
                .map(this::parseLine)
                .filter(this::isValidPartOne)
                .count();
    }

    @Override
    public Object part2Solution() {
        return Arrays.stream(getInputLines())
                .map(this::parseLine)
                .filter(this::isValidPartTwo)
                .count();
    }

    private boolean isValidPartOne(String[] row) {
        return isSafeRow(row, false);
    }

    private boolean isValidPartTwo(String[] row) {
        return isSafeRow(row, true);
    }

    private boolean isSafeRow(String[] row, boolean useDampener) {
        boolean isIncreasing = Integer.parseInt(row[0]) < Integer.parseInt(row[1]);

        int problemDampener = 0;

        for (int i = 0; i < row.length-1; i++) {
            int current = Integer.parseInt(row[i]);
            int next = Integer.parseInt(row[i+1]);

            if (!isLevelSafe(current, next, isIncreasing)) {
                if (!useDampener) return false;
                
                problemDampener++;
                if (problemDampener > 1) return false;
            }
        }
        return true;
    }

    private boolean isLevelSafe(int a, int b, boolean increasing) {
        if (a == b) return false;
        if (a > b && increasing) return false;
        if (a < b && !increasing) return false;

        int diff = Math.abs(a - b);
        return diff <= 3 && diff >= 1;
    }

    private String[] parseLine(String line) {
        return line.split(" ");
    }
}
