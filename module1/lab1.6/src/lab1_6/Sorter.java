package lab1_6;

public class Sorter {

    private static void performGapInsertionSort(double[] arr, int gap) {
        int n = arr.length;
        for (int i = gap; i < n; i++) {
            double temp = arr[i];
            int j;
            for (j = i; j >= gap && arr[j - gap] > temp; j -= gap) {
                arr[j] = arr[j - gap];
            }
            arr[j] = temp;
        }
    }

    public static void shellSortShell(double[] arr) {
        int n = arr.length;
        for (int gap = n / 2; gap > 0; gap /= 2) {
            performGapInsertionSort(arr, gap);
        }
    }

    public static void shellSortKnuth(double[] arr) {
        int n = arr.length;
        int gap = 1;
        while (gap < n / 3) {
            gap = 3 * gap + 1;
        }
        while (gap >= 1) {
            performGapInsertionSort(arr, gap);
            gap /= 3;
        }
    }
}
