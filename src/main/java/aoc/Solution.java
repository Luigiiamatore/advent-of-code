package aoc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public abstract class Solution {
    protected List<String> input;
    private final int day;
    private final int year;

    public Solution(int year, int day) {
        this.year = year;
        this.day = day;
        this.input = loadInput();
    }

    public abstract Object part1();
    public abstract Object part2();

    private List<String> loadInput() {
        String fileName = String.format("day%02d.txt", day);
        Path path = Paths.get("inputs", String.valueOf(year), fileName);

        try {
            return Files.readAllLines(path);
        } catch (IOException e) {
            throw new RuntimeException("Errore lettura input: " + path.toAbsolutePath(), e);
        }
    }

    public void run() {
        System.out.println("=== Advent of Code " + year + " - Day " + day + " ===");

        long start = System.currentTimeMillis();
        System.out.println("Part 1: " + part1());
        System.out.println("Time P1: " + (System.currentTimeMillis() - start) + "ms");

        start = System.currentTimeMillis();
        System.out.println("Part 2: " + part2());
        System.out.println("Time P2: " + (System.currentTimeMillis() - start) + "ms");
    }
}