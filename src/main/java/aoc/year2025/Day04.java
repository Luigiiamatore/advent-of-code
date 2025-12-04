package aoc.year2025;

import aoc.Solution;

import java.util.ArrayList;
import java.util.List;

public class Day04 extends Solution {
    private final int MAX_ROLLS_NEARBY = 3;

    public Day04() {
        super(2025, 4);
    }

    @Override
    public Object part1() {
        long count = 0;
        for (int i = 0; i < input.size(); i++) {
            String line = input.get(i);
            char[] charArray = line.toCharArray();

            for (int j = 0, charArrayLength = charArray.length; j < charArrayLength; j++) {
                char item = charArray[j];
                if (item == '@' && hasEnoughSpace(i, j, input)) count++;
            }
        }
        return count;
    }

    @Override
    public Object part2() {
        long count = 0;
        List<String> disposition = this.input;
        List<String> newDisposition = new ArrayList<>();
        boolean madeChange = true;

        while (madeChange) {
            madeChange = false;
            for (int i = 0; i < disposition.size(); i++) {
                String line = disposition.get(i);
                StringBuilder newLine = new StringBuilder();
                char[] charArray = line.toCharArray();

                for (int j = 0, charArrayLength = charArray.length; j < charArrayLength; j++) {
                    char item = charArray[j];
                    if (item == '@') {
                        if (hasEnoughSpace(i, j, disposition)) {
                            newLine.append('.');
                            count++;
                            madeChange = true;
                        } else newLine.append('@');
                    } else newLine.append(item);
                }

                newDisposition.add(newLine.toString());
            }

            disposition = newDisposition;
            newDisposition = new ArrayList<>();
        }

        return count;
    }

    private boolean hasEnoughSpace(int rollLine, int rollCol, List<String> disposition) {
        int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};
        int rollsNearby = 0;

        for (int i = 0; i < dr.length; i++) {
            int r = rollLine + dr[i];
            int c = rollCol + dc[i];
            if (r >= 0 && r < disposition.size() && c >= 0 && c < disposition.getFirst().length() && disposition.get(r).charAt(c) == '@') {
                rollsNearby++;
            }
        }

        return rollsNearby <= MAX_ROLLS_NEARBY;
    }

    static void main() {
        new Day04().run();
    }
}
