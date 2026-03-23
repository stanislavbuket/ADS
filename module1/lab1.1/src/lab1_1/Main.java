package lab1_1;

public class Main {
    public static void main(String[] args) {
        OctalStringLinkedQueue queue = new OctalStringLinkedQueue();
        
        String[] initialValues = {"100", "310", "311", "400", "10"};
        for (String val : initialValues) {
            System.out.println("Enqueue " + val + ": " + queue.enqueue(val));
        }
        
        System.out.println("\nStructure 1 (Queue):");
        queue.print();
        
        System.out.println("Removed from queue: " + queue.dequeue());
        queue.print();
        
        DoubleArrayStack stack = new DoubleArrayStack(10);
        System.out.println("\nProcessing elements...");
        
        while (!queue.isEmpty()) {
            String octalString = queue.dequeue();
            int decimalValue = Integer.parseInt(octalString, 8);
            
            double result = (decimalValue > 200) ? (decimalValue / 2.0) : (decimalValue * 2.0);
            
            boolean isPushed = stack.push(result);
            System.out.println("Pushed " + result + " to stack: " + isPushed);
        }
        
        System.out.println("\nStructure 1 and 2 after processing:");
        queue.print();
        stack.print();
        
        System.out.println("\nRemoved from stack: " + stack.pop());
        System.out.println("Removed from stack: " + stack.pop());
        stack.print();
    }
}