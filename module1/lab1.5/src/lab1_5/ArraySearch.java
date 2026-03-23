package lab1_5;

public class ArraySearch {
    private final Student[] arr;
    private int size;

    public ArraySearch(int capacity) {
        arr = new Student[capacity];
        size = 0;
    }

    public boolean insert(Student student) {
        for (int i = 0; i < size; i++) {
            if (arr[i].lastName().equalsIgnoreCase(student.lastName())) {
                return false;
            }
        }
        if (size < arr.length) {
            arr[size++] = student;
            return true;
        }
        return false;
    }

    public void sort() {
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (arr[j].lastName().compareToIgnoreCase(arr[j + 1].lastName()) > 0) {
                    Student temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public int binarySearch(String lastName) {
        int left = 0;
        int right = size - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int cmp = arr[mid].lastName().compareToIgnoreCase(lastName);
            if (cmp == 0) return mid;
            if (cmp < 0) left = mid + 1;
            else right = mid - 1;
        }
        return -1;
    }

    public boolean processTask(String targetLastName) {
        sort();
        int index = binarySearch(targetLastName);
        if (index != -1) {
            if ("Donetsk".equalsIgnoreCase(arr[index].city())) {
                for (int i = index; i < size - 1; i++) {
                    arr[i] = arr[i + 1];
                }
                arr[size - 1] = null;
                size--;
                return true;
            }
        }
        return false;
    }

    public void print() {
        Main.printTable(arr, size);
    }
}
