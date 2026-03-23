package lab1_2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter hash table size: ");
            int size = scanner.nextInt();

            HashTable hashTable = new HashTable(size);

            for (int i = 0; i < size; i++) {
                double x1 = Math.random() * 10;
                double y1 = Math.random() * 10;
                double x2 = Math.random() * 10;
                double y2 = Math.random() * 10;
                
                LineSegment segment = new LineSegment(x1, y1, x2, y2);
                if (!hashTable.insert(segment)) {
                    System.out.println("Could not insert element due to quadratic probing collision limits.");
                }
            }

            System.out.println("\nAfter initial insertion:");
            hashTable.print();

            double maxAngle = 90.0;
            System.out.println("Removing elements with angle greater than " + maxAngle + "...\n");
            hashTable.removeByAngle(maxAngle);

            System.out.println("After removal:");
            hashTable.print();
        }
    }
}