package lab1_4;

import java.util.Arrays;

public class Main {
    private static final String SEPARATOR = "+-----------------+-----------------+----------------------+----------------+";
    private static final String ROW_FORMAT = "| %-15s | %-15s | %-20s | %-14s |%n";
    private static final String DATA_FORMAT = "| %-15s | %-15s | %-20s | %-14d |%n";

    public static void main(String[] args) {
        Student[] originalArray = {
            new Student("Kovalenko", "Ivan", "Math", 5),
            new Student("Shevchenko", "Mariia", "Physics", 12),
            new Student("Boiko", "Petro", "Programming", 3),
            new Student("Tkachenko", "Anna", "History", 8),
            new Student("Lysenko", "Olena", "Math", 15),
            new Student("Honchar", "Viktoriia", "Physics", 0),
            new Student("Melnyk", "Sofiia", "Programming", 12),
            new Student("Kozak", "Daryna", "History", 7),
            new Student("Pavlenko", "Oleh", "Math", 21)
        };

        System.out.println("=== Level 1: Counting Sort ===");
        Student[] arr1 = Arrays.copyOf(originalArray, originalArray.length);
        System.out.println("Before Sorting:");
        printTable(arr1);
        countingSort(arr1);
        System.out.println("After Sorting (Descending by Completed Labs):");
        printTable(arr1);

        System.out.println("\n=== Level 2: Radix Sort ===");
        Student[] arr2 = Arrays.copyOf(originalArray, originalArray.length);
        System.out.println("Before Sorting:");
        printTable(arr2);
        radixSort(arr2);
        System.out.println("After Sorting (Descending by Completed Labs):");
        printTable(arr2);

        System.out.println("\n=== Level 3: Bottom-up Merge Sort ===");
        Student[] arr3 = Arrays.copyOf(originalArray, originalArray.length);
        System.out.println("Before Sorting:");
        printTable(arr3);
        bottomUpMergeSort(arr3);
        System.out.println("After Sorting (Descending by Completed Labs):");
        printTable(arr3);
    }

    private static void printTable(Student[] arr) {
        System.out.println(SEPARATOR);
        System.out.printf(ROW_FORMAT, "Last Name", "First Name", "Subject", "Completed Labs");
        System.out.println(SEPARATOR);
        for (Student s : arr) {
            System.out.printf(DATA_FORMAT, s.lastName(), s.firstName(), s.subject(), s.completedLabs());
        }
        System.out.println(SEPARATOR);
    }

    private static int getMaxCompletedLabs(Student[] arr) {
        int max = arr[0].completedLabs();
        for (int i = 1; i < arr.length; i++) {
            if (arr[i].completedLabs() > max) {
                max = arr[i].completedLabs();
            }
        }
        return max;
    }

    public static void countingSort(Student[] arr) {
        if (arr.length == 0) return;
        int max = getMaxCompletedLabs(arr);
        
        int[] count = new int[max + 1];
        for (Student s : arr) {
            count[s.completedLabs()]++;
        }
        
        for (int i = max - 1; i >= 0; i--) {
            count[i] += count[i + 1];
        }
        
        Student[] output = new Student[arr.length];
        for (int i = arr.length - 1; i >= 0; i--) {
            int val = arr[i].completedLabs();
            output[count[val] - 1] = arr[i];
            count[val]--;
        }
        
        System.arraycopy(output, 0, arr, 0, arr.length);
    }

    public static void radixSort(Student[] arr) {
        if (arr.length == 0) return;
        int max = getMaxCompletedLabs(arr);
        
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSortByDigit(arr, exp);
        }
    }

    private static void countingSortByDigit(Student[] arr, int exp) {
        int[] count = new int[10];
        for (Student s : arr) {
            int digit = (s.completedLabs() / exp) % 10;
            count[digit]++;
        }
        
        for (int i = 8; i >= 0; i--) {
            count[i] += count[i + 1];
        }
        
        Student[] output = new Student[arr.length];
        for (int i = arr.length - 1; i >= 0; i--) {
            int digit = (arr[i].completedLabs() / exp) % 10;
            output[count[digit] - 1] = arr[i];
            count[digit]--;
        }
        System.arraycopy(output, 0, arr, 0, arr.length);
    }

    public static void bottomUpMergeSort(Student[] arr) {
        if (arr == null || arr.length <= 1) return;
        Student[] temp = new Student[arr.length];
        for (int width = 1; width < arr.length; width = 2 * width) {
            for (int i = 0; i < arr.length; i = i + 2 * width) {
                int right = Math.min(i + width, arr.length);
                int end = Math.min(i + 2 * width, arr.length);
                merge(arr, temp, i, right, end);
            }
            System.arraycopy(temp, 0, arr, 0, arr.length);
        }
    }

    private static void merge(Student[] arr, Student[] temp, int left, int right, int end) {
        int i = left;
        int j = right;
        for (int k = left; k < end; k++) {
            if (i < right && (j >= end || arr[i].completedLabs() >= arr[j].completedLabs())) {
                temp[k] = arr[i];
                i++;
            } else {
                temp[k] = arr[j];
                j++;
            }
        }
    }
}
