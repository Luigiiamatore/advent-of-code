package aoc.year2025;

import aoc.Solution;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day07 extends Solution {
    public Day07() {
        super(2025, 7);
    }

    @Override
    public Object part1() {
        String beamLine = input.getFirst();
        int beamPosition = beamLine.indexOf('S');

        Set<Integer> beams = new HashSet<>(Set.of(beamPosition));

        int splitCount = 0;
        for (int i = 1; i < input.size(); i++) {
            List<Integer> beamsToRemove = new ArrayList<>();
            List<Integer> beamsToAdd = new ArrayList<>();
            String line = input.get(i);

            for (Integer beam : beams) {
                if (line.charAt(beam) == '^') {
                    splitCount++;
                    beamsToRemove.add(beam);
                    beamsToAdd.add(beam - 1);
                    beamsToAdd.add(beam + 1);
                }
            }

            beamsToRemove.forEach(beams::remove);
            beams.addAll(beamsToAdd);
        }

        return splitCount;
    }

    @Override
    public Object part2() {
        return null;
    }

    static void main() {
        new Day07().run();
    }
}
