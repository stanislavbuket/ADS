package lab2_3;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CombinatoricsSolver {

    public static long factorial(int n) {
        if (n < 0) throw new IllegalArgumentException("Number must be non-negative");
        long result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    public static long arrangementsWithoutRepetitions(int n, int k) {
        if (k > n || k < 0) return 0;
        return factorial(n) / factorial(n - k);
    }

    public static long permutationsWithRepetitions(int n, int[] repetitions) {
        long denominator = 1;
        int sum = 0;
        for (int r : repetitions) {
            denominator *= factorial(r);
            sum += r;
        }
        if (sum != n) {
            throw new IllegalArgumentException("Sum of repetitions must equal total elements count n");
        }
        return factorial(n) / denominator;
    }

    public static List<List<Integer>> generateAllPlacements(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        boolean[] used = new boolean[n + 1];
        backtrack(n, k, new ArrayList<>(), used, result);
        return result;
    }

    private static void backtrack(int n, int k, List<Integer> current, boolean[] used, List<List<Integer>> result) {
        if (current.size() == k) {
            result.add(new ArrayList<>(current));
            return;
        }
        for (int i = 1; i <= n; i++) {
            if (!used[i]) {
                used[i] = true;
                current.add(i);
                backtrack(n, k, current, used, result);
                current.remove(current.size() - 1);
                used[i] = false;
            }
        }
    }

    public static void writePlacementsToFile(String filepath, List<List<Integer>> placements) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filepath))) {
            writer.write("Кількість згенерованих розміщень: " + placements.size() + "\n\n");
            for (int i = 0; i < placements.size(); i++) {
                writer.write(String.format("%4d: %s\n", i + 1, placements.get(i).toString()));
            }
            System.out.println("Результати успішно записано у файл: " + filepath);
        } catch (IOException e) {
            System.err.println("Помилка запису у файл: " + e.getMessage());
        }
    }
}
