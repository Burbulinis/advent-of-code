package me.burb.adventofcode.solutions.year2024;

import me.burb.adventofcode.AdventOfCodeSolution;
import me.burb.adventofcode.Solution;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@AdventOfCodeSolution(year = 2024, day = 3, name = "Mull It Over", link = "https://adventofcode.com/2024/day/3")
public class Day3Solution extends Solution {

    private static final Pattern PATTERN = Pattern.compile("mul\\((\\d+,\\d+)\\)|do\\(\\)|don't\\(\\)");

    @Override
    public Object part1Solution() {
        int sum = 0;

        Matcher match = Pattern.compile("mul\\((\\d+,\\d+)\\)").matcher(getInput());
        while (match.find())
            sum += mul(match.group(1));
        return sum;
    }

    @Override
    public Object part2Solution() {
        int sum = 0;
        boolean mul = true;

        Matcher match = Pattern.compile("mul\\((\\d+,\\d+)\\)|do\\(\\)|don't\\(\\)").matcher(getInput());
        while (match.find()) {
            if (match.group().equals("do()")) {
                mul = true;
                continue;
            } else if (match.group().equals("don't()")) {
                mul = false;
                continue;
            }

            if (!mul)
                continue;

            sum += mul(match.group(1));
        }
        return sum;
    }

    private int mul(String group) {
        return Arrays.stream(group.split(","))
                .mapToInt(Integer::parseInt)
                .reduce((a, b) -> a * b)
                .orElse(0);
    }
}
