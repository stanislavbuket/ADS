package lab1_6;

import java.util.Arrays;
import java.util.Random;

public class Main {
    private static final Random RANDOM = new Random();
    private static final int RUNS = 5;

    public static void main(String[] args) {
        System.out.println("=== Level 1 & 2: Time complexity for N, N^2, N^3 (N=100) ===");
        int[] sizes = {100, 10_000, 1_000_000};
        
        System.out.printf("%-10s | %-20s | %-20s%n", "Size", "Shell Sort avg (ns)", "Knuth Sort avg (ns)");
        System.out.println("-".repeat(56));

        for (int size : sizes) {
            double[] arr = generateRandomArray(size);
            
            long timeShell = getAverageTimeShell(arr);
            long timeKnuth = getAverageTimeKnuth(arr);

            System.out.printf("%-10d | %-20d | %-20d%n", size, timeShell, timeKnuth);
        }

        System.out.println("\n=== Level 3: Effect of Data Arrangement (Size = 10,000) ===");
        int sizeLevel3 = 10_000;
        
        double[] randomArr = generateRandomArray(sizeLevel3);
        double[] sortedArr = generateSortedArray(sizeLevel3);
        double[] reversedArr = generateReverseSortedArray(sizeLevel3);

        System.out.printf("%-16s | %-20s | %-20s%n", "Arrangement", "Shell Sort avg (ns)", "Knuth Sort avg (ns)");
        System.out.println("-".repeat(62));

        measureAndPrintLevel3("Best (Sorted)", sortedArr);
        measureAndPrintLevel3("Avg (Random)", randomArr);
        measureAndPrintLevel3("Worst (Reversed)", reversedArr);
    }

    private static void measureAndPrintLevel3(String label, double[] originalArr) {
        long timeShell = getAverageTimeShell(originalArr);
        long timeKnuth = getAverageTimeKnuth(originalArr);
        System.out.printf("%-16s | %-20d | %-20d%n", label, timeShell, timeKnuth);
    }

    private static long getAverageTimeShell(double[] original) {
        long totalTime = 0;
        for (int i = 0; i < RUNS + 2; i++) {
            double[] arr = Arrays.copyOf(original, original.length);
            long start = System.nanoTime();
            Sorter.shellSortShell(arr);
            if (i >= 2) {
                totalTime += (System.nanoTime() - start);
            }
        }
        return totalTime / RUNS;
    }

    private static long getAverageTimeKnuth(double[] original) {
        long totalTime = 0;
        for (int i = 0; i < RUNS + 2; i++) {
            double[] arr = Arrays.copyOf(original, original.length);
            long start = System.nanoTime();
            Sorter.shellSortKnuth(arr);
            if (i >= 2) {
                totalTime += (System.nanoTime() - start);
            }
        }
        return totalTime / RUNS;
    }

    private static double[] generateRandomArray(int size) {
        double[] arr = new double[size];
        for (int i = 0; i < size; i++) {
            arr[i] = RANDOM.nextDouble() * 10000;
        }
        return arr;
    }

    private static double[] generateSortedArray(int size) {
        double[] arr = new double[size];
        for (int i = 0; i < size; i++) {
            arr[i] = i;
        }
        return arr;
    }

    private static double[] generateReverseSortedArray(int size) {
        double[] arr = new double[size];
        for (int i = 0; i < size; i++) {
            arr[i] = size - i;
        }
        return arr;
    }
}
