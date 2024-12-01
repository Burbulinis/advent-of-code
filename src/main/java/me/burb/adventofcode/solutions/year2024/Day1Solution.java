package me.burb.adventofcode.solutions.year2024;

import me.burb.adventofcode.AdventOfCodeSolution;
import me.burb.adventofcode.Solution;

import java.util.*;
import java.util.function.Predicate;

@AdventOfCodeSolution(year = 2024, day = 1, name = "Historian Hysteria", link = "https://adventofcode.com/2024/day/1")
public class Day1Solution extends Solution {

    @Override
    public Integer part1Solution() {
        var lists = parseInput();

        List<Integer> leftList = lists.getKey();
        List<Integer> rightList = lists.getValue();

        Collections.sort(leftList);
        Collections.sort(rightList);

        int sum = 0;
        for (int i = 0; i < leftList.size(); i++) {
            int left = leftList.get(i);
            int right = rightList.get(i);
            sum += Math.abs(left - right);
        }

        return sum;
    }

    @Override
    public Integer part2Solution() {
        var lists = parseInput();

        List<Integer> leftList = lists.getKey();
        List<Integer> rightList = lists.getValue();

        int sum = 0;
        for (int left : leftList) {
            int right = (int) rightList.stream()
                    .filter(integer -> left == integer)
                    .count();
            sum += left * right;
        }

        return sum;
    }

    private Map.Entry<List<Integer>, List<Integer>> parseInput() {
        String[] lines = getInputLines();

        List<Integer> leftList = new ArrayList<>();
        List<Integer> rightList = new ArrayList<>();
        for (String line : lines) {
            String[] split = line.split(" {3}");
            leftList.add(Integer.parseInt(split[0]));
            rightList.add(Integer.parseInt(split[1]));
        }

        return Map.entry(leftList, rightList);
    }

}
