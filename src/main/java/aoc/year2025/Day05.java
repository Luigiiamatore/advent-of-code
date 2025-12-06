package aoc.year2025;

import aoc.Solution;

import java.util.*;

public class Day05 extends Solution {
    private final TreeMap<Long, Long> intervals = new TreeMap<>();

    private static class Interval {
        long start, end;
        Interval(long start, long end) {
            this.start = start;
            this.end = end;
        }
    }

    private void mergeIntervals(List<long[]> ranges) {
        List<Interval> mergedIntervals = new ArrayList<>();
        for (long[] range : ranges)
            mergedIntervals.add(new Interval(range[0], range[1]));

        mergedIntervals.sort(Comparator.comparingLong(o -> o.start));

        Interval curr = mergedIntervals.getFirst();
        for (int i = 1; i < mergedIntervals.size(); i++) {
            Interval next = mergedIntervals.get(i);
            if (curr.end >= next.start - 1) {
                curr.end = Math.max(curr.end, next.end);
            } else {
                intervals.put(curr.start, curr.end);
                curr = next;
            }
        }
        intervals.put(curr.start, curr.end);
    }

    private boolean contains(long number) {
        Map.Entry<Long, Long> candidate = intervals.floorEntry(number);

        if (candidate == null) return false;

        return number <= candidate.getValue();
    }

    public Day05() {
        super(2025, 5);
    }

    @Override
    public Object part1() {
        List<long[]> listIntervals = new ArrayList<>();

        Iterator<String> iterator = input.iterator();
        while (iterator.hasNext()) {
            String line = iterator.next();
            if (line.isBlank()) break;

            long start = Long.parseLong(line.split("-")[0]);
            long end = Long.parseLong(line.split("-")[1]);

            listIntervals.add(new long[]{start, end});
        }

        mergeIntervals(listIntervals);
        for (Map.Entry<Long, Long> longLongEntry : intervals.entrySet()) {
            IO.println(longLongEntry.getKey() + " - " + longLongEntry.getValue());
        }

        int fresh = 0;
        while (iterator.hasNext()) {
            String line = iterator.next();
            long number = Long.parseLong(line);
            if (contains(number)) fresh++;
        }

        return fresh;
    }

    @Override
    public Object part2() {
        long freshIDs = 0;
        for (Map.Entry<Long, Long> entry : intervals.entrySet()) {
            freshIDs += entry.getValue() - entry.getKey() + 1;
        }
        return freshIDs;
    }

    static void main() {
        new Day05().run();
    }
}
