package aoc.year2025;

import aoc.Solution;

import java.util.ArrayList;
import java.util.List;

public class Day06 extends Solution {
    private final List<Problem> problems = new ArrayList<>();

    private static class Problem {
        List<Long> operands = new ArrayList<>();
        char operator;

        long operate() {
            return switch (operator) {
                case '+' -> operands.stream().reduce(0L, Long::sum);
                case '*' -> operands.stream().reduce(1L, (a, b) -> a * b);
                default -> -1;
            };
        }
    }

    public Day06() {
        super(2025, 6);
    }

    @Override
    public Object part1() {
        for (String line : input) {
            String[] tokens = line.trim().split("\\s+");

            if (problems.isEmpty())
                for (int i = 0; i < tokens.length; i++)
                    problems.add(new Problem());

            for (int i = 0; i < tokens.length; i++) {
                String token = tokens[i];
                Problem problem = problems.get(i);

                if (token.equals("+") || token.equals("*"))
                    problem.operator = token.charAt(0);
                else
                    problem.operands.add(Long.parseLong(token));
            }
        }

        long sum = 0;
        for (Problem problem : problems) {
            sum += problem.operate();
        }
        return sum;
    }

    @Override
    public Object part2() {
        return null;
    }

    static void main() {
        new Day06().run();
    }
}
