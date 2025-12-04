package aoc.year2025;

import aoc.Solution;

import java.util.List;

public class Day02 extends Solution {
    public Day02() {
        super(2025, 2);
    }

    @Override
    public Object part1() {
        List<String> intervals = List.of(input.getFirst().split(","));

        long sumInvalidIDs = 0;
        for (String interval : intervals) {
            long start = Long.parseLong(interval.split("-")[0]);
            long end = Long.parseLong(interval.split("-")[1]);
            for (long i = start; i <= end; i++) {
                String number = String.valueOf(i);
                if (number.length() % 2 == 0) {
                    String firstHalf = number.substring(0, number.length() / 2);
                    String secondHalf = number.substring(number.length() / 2);
                    if (firstHalf.equals(secondHalf)) {
                        sumInvalidIDs += i;
                    }
                }
            }
        }
        return sumInvalidIDs;
    }

    @Override
    public Object part2() {
        List<String> intervals = List.of(input.getFirst().split(","));

        long sumInvalidIDs = 0;
        for (String interval : intervals) {
            long start = Long.parseLong(interval.split("-")[0]);
            long end = Long.parseLong(interval.split("-")[1]);

            for (long i = start; i <= end; i++) {
                String id = String.valueOf(i);
                for (int chunks = 2; chunks <= id.length(); chunks++) {
                    if (id.length() % chunks == 0) {
                        int chunkLength = id.length() / chunks;
                        String firstChunk = id.substring(0, chunkLength);
                        if (firstChunk.repeat(chunks).equals(id)) {
                            sumInvalidIDs += i;
                            break;
                        }
                    }
                }
            }
        }
        return sumInvalidIDs;
    }

    static void main() {
        new Day02().run();
    }
}
