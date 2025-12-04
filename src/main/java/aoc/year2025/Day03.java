package aoc.year2025;

import aoc.Solution;

import java.util.List;

public class Day03 extends Solution {
    public Day03() {
        super(2025, 3);
    }

    @Override
    public Object part1() {
        List<String> banks = input;
        long totJoltage = 0;

        for (String bank : banks) {
            int maxJoltageInBank = 0;
            int maxDigitSeenSoFar = -1;

            for (char c : bank.toCharArray()) {
                int currentDigit = c - '0';

                if (maxDigitSeenSoFar != -1) {
                    int currentJoltage = (maxDigitSeenSoFar * 10) + currentDigit;
                    if (currentJoltage > maxJoltageInBank) {
                        maxJoltageInBank = currentJoltage;
                    }
                }

                if (currentDigit > maxDigitSeenSoFar) {
                    maxDigitSeenSoFar = currentDigit;
                }
            }
            totJoltage += maxJoltageInBank;
        }
        return totJoltage;
    }

    @Override
    public Object part2() {
        List<String> banks = input;
        long totJoltage = 0;

        for (String bank : banks) {
            totJoltage += findMax12DigitNumber(bank);
        }
        return totJoltage;
    }

    private long findMax12DigitNumber(String bank) {
        int n = bank.length();
        int k = 12;

        if (n <= k) return Long.parseLong(bank);

        int dropBudget = n - k;
        StringBuilder stack = new StringBuilder();

        for (char digit : bank.toCharArray()) {
            while (dropBudget > 0 && !stack.isEmpty() && stack.charAt(stack.length() - 1) < digit) {
                stack.deleteCharAt(stack.length() - 1);
                dropBudget--;
            }
            stack.append(digit);
        }

        String resultString = stack.substring(0, k);

        return Long.parseLong(resultString);
    }

    static void main() {
        new Day03().run();
    }
}
