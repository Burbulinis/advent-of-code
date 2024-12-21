package me.burb.adventofcode.solutions.year2024;

import me.burb.adventofcode.AdventOfCodeSolution;
import me.burb.adventofcode.Solution;

import java.util.Arrays;
import java.util.List;
import java.util.function.IntBinaryOperator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@AdventOfCodeSolution(year = 2024, day = 14, name = "Restroom Redoubt", link = "https://adventofcode.com/2024/day/14")
public class Day14Solution extends Solution {

    private static final Pattern PATTERN = Pattern.compile("p=(\\d+),(\\d+) v=(-?\\d+),(-?\\d+)");

    @Override
    public Object part1Solution() {
        int[] q = new int[4];

        Arrays.stream(getInputLines())
                .map(this::parse)
                .forEach(robot -> {
                    robot.move(100);

                    int quadrant = robot.getQuadrant();
                    if (quadrant != -1)
                        q[quadrant]++;
                });

        return Arrays.stream(q).reduce((a, b) -> a * b).orElse(0);
    }

    @Override
    public Object part2Solution() {
        List<Robot> robots = Arrays.stream(getInputLines())
                .map(this::parse)
                .toList();
        int lowestSafetyFactor = Integer.MAX_VALUE, secondsPassed = 0;
        for (int i = 1; i <= (101 * 103); i++) {
            int[] q = new int[4];
            robots.forEach(robot -> {
                robot.move(1);

                int quadrant = robot.getQuadrant();
                if (quadrant != -1)
                    q[quadrant]++;
            });

            int safetyFactor = Arrays.stream(q).reduce((a, b) -> a * b).orElse(0);
            if (safetyFactor < lowestSafetyFactor) {
                lowestSafetyFactor = safetyFactor;
                secondsPassed = i;
            }
        }
        return secondsPassed;
    }

    public Robot parse(String line) {
        Matcher matcher = PATTERN.matcher(line);
        if (matcher.matches()) {
            int x = Integer.parseInt(matcher.group(1));
            int y = Integer.parseInt(matcher.group(2));
            int vx = Integer.parseInt(matcher.group(3));
            int vy = Integer.parseInt(matcher.group(4));

            return new Robot(x, y, vx, vy);
        }
        return null;
    }

    private static class Robot {

        int x, y;
        final int vx;
        final int vy;
        
        public Robot(int x, int y, int vx, int vy) {
            this.x = x;
            this.y = y;
            this.vx = vx;
            this.vy = vy;
        }

        public void move(int seconds) {
            x = mod(x + (vx * seconds), 101);
            y = mod(y + (vy * seconds), 103);
        }

        public int getQuadrant() {
            if (x < 50 && y < 51)
                return 0;
            else if (x > 50 && y < 51)
                return 1;
            else if (x < 50 && y > 51)
                return 2;
            else if (x > 50 && y > 51)
                return 3;
            return -1;
        }

        private int mod(int a, int b) {
            return (a % b + b) % b;
        }
    }
}
