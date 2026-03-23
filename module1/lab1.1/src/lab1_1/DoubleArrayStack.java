package lab1_1;

public class DoubleArrayStack {
    private final double[] array;
    private int top;

    public DoubleArrayStack(int capacity) {
        array = new double[capacity];
        top = -1;
    }

    public boolean isFull() {
        return top == array.length - 1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean push(double value) {
        if (isFull()) return false;
        array[++top] = value;
        return true;
    }

    public double pop() {
        if (isEmpty()) throw new RuntimeException("Stack is empty");
        return array[top--];
    }

    public void print() {
        System.out.print("Stack: ");
        for (int i = top; i >= 0; i--) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}