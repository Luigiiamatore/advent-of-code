package aoc.year2025;

import aoc.Solution;

import java.util.ArrayList;
import java.util.List;

public class Day06 extends Solution {
    private static class Problem {
        List<Long> operands = new ArrayList<>();
        char operator;

        long operate() {
            if (operands.isEmpty()) return 0;

            long result = (operator == '*' ? 1 : 0);
            if (operator == '+') {
                for (Long operand : operands) result += operand;
            } else {
                for (Long operand : operands) result *= operand;
            }

            return result;
        }
    }

    public Day06() {
        super(2025, 6);
    }

    @Override
    public Object part1() {
        List<Problem> problems = new ArrayList<>();

        for (String line : input) {
            if (line.isBlank()) continue;

            String[] tokens = line.trim().split("\\s+");

            while (problems.size() < tokens.length) problems.add(new Problem());

            for (int i = 0; i < tokens.length; i++) {
                String token = tokens[i];
                char firstChar = token.charAt(0);

                if (firstChar == '+' || firstChar == '*') problems.get(i).operator = token.charAt(0);
                else problems.get(i).operands.add(Long.parseLong(token));
            }
        }

        long sum = 0;
        for (Problem problem : problems) sum += problem.operate();
        return sum;
    }

    @Override
    public Object part2() {
        int height = input.size();
        int width = 0;
        for (String s : input)
            if (s.length() > width) width = s.length();

        List<Problem> problems = new ArrayList<>();
        Problem currentProblem = new Problem();
        boolean inBlock = false;

        for (int col = 0; col < width; col++) {
            StringBuilder numberBuilder = new StringBuilder();
            boolean isSpaceColumn = true;
            Character foundOperator = null;

            for (int row = 0; row < height; row++) {
                if (col >= input.get(row).length()) continue;

                char c = input.get(row).charAt(col);

                if (c != ' ') {
                    isSpaceColumn = false;
                    if (c == '+' || c == '*') foundOperator = c;
                    else if (Character.isDigit(c)) numberBuilder.append(c);
                }
            }

            if (isSpaceColumn) {
                if (inBlock) {
                    problems.add(currentProblem);
                    currentProblem = new Problem();
                    inBlock = false;
                }
            } else {
                inBlock = true;
                if (foundOperator != null) currentProblem.operator = foundOperator;
                if (!numberBuilder.isEmpty()) currentProblem.operands.add(Long.parseLong(numberBuilder.toString()));
            }
        }
        if (inBlock) problems.add(currentProblem);

        return problems.stream().mapToLong(Problem::operate).sum();
    }

    static void main() {
        new Day06().run();
    }
}
