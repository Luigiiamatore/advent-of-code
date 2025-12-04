package aoc.year2025;

import aoc.Solution;

public class Day01 extends Solution {
    private int currentValue;
    private int crossedZero;
    private int numberOfZeroes;
    private boolean isSolved = false;

    public Day01() {
        super(2025, 1);
    }

    private void solve() {
        if (isSolved) return;

        currentValue = 50;
        crossedZero = 0;
        numberOfZeroes = 0;

        for (String line : input) {
            char direction = line.charAt(0);
            int value = Integer.parseInt(line.substring(1));

            int previousValue = currentValue;
            int newDialNumber;

            switch (direction) {
                case 'L':
                    currentValue = ((currentValue - value) % 100 + 100) % 100;

                    for (int i = 0; i < value; i++) {
                        newDialNumber = ((previousValue - i) % 100 + 100) % 100;
                        if (newDialNumber == 0) crossedZero++;
                    }
                    break;

                case 'R':
                    currentValue = (currentValue + value) % 100;

                    for (int i = 0; i < value; i++) {
                        newDialNumber = (previousValue + i) % 100;
                        if (newDialNumber == 0) crossedZero++;
                    }
                    break;
            }

            if (currentValue == 0) numberOfZeroes++;
        }
        isSolved = true;
    }

    @Override
    public Object part1() {
        solve();
        return numberOfZeroes;
    }

    @Override
    public Object part2() {
        solve();
        return crossedZero;
    }

    static void main(String[] args) {
        new Day01().run();
    }
}