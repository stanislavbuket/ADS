package lab1_2;

public class HashTable {
    private final LineSegment[] table;
    private final int size;
    private static final double A = 0.6180339887; 

    public HashTable(int size) {
        this.size = size;
        this.table = new LineSegment[size];
    }

    private int hashMultiplication(double length) {
        double temp = length * A;
        double fractionalPart = temp - Math.floor(temp);
        return (int) Math.floor(size * fractionalPart);
    }

    public boolean insert(LineSegment segment) {
        double key = segment.getLength();
        int hash = hashMultiplication(key);
        
        int i = 0;
        int index = hash;
        
        while (table[index] != null) {
            if (i == size) {
                return false; 
            }
            i++;
            index = (hash + i * i) % size; 
        }
        
        table[index] = segment;
        return true;
    }

    public void removeByAngle(double maxAngle) {
        for (int i = 0; i < size; i++) {
            if (table[i] != null && table[i].getAngle() > maxAngle) {
                table[i] = null;
            }
        }
    }

    public void print() {
        System.out.println("Hash Table:");
        for (int i = 0; i < size; i++) {
            if (table[i] != null) {
                System.out.printf("[%2d] Key: %.2f | %s%n", i, table[i].getLength(), table[i].toString());
            } else {
                System.out.printf("[%2d] empty%n", i);
            }
        }
        System.out.println();
    }
}