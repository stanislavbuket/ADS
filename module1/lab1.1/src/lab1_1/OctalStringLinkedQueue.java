package lab1_1;

public class OctalStringLinkedQueue {
    private QueueNode head;
    private QueueNode tail;

    public boolean isEmpty() {
        return head == null;
    }

    public boolean enqueue(String value) {
        QueueNode newNode = new QueueNode(value);
        if (isEmpty()) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        return true;
    }

    public String dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }
        String value = head.data;
        head = head.next;
        if (head == null) {
            tail = null;
        }
        return value;
    }

    public void print() {
        System.out.print("Queue: ");
        for (QueueNode current = head; current != null; current = current.next) {
            System.out.print(current.data + " ");
        }
        System.out.println();
    }
}