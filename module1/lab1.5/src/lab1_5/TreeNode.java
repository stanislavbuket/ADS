package lab1_5;

import java.util.Random;

public class TreeNode {
    private static final Random RANDOM = new Random();
    
    Student student;
    TreeNode left;
    TreeNode right;
    int priority;

    public TreeNode(Student student) {
        this.student = student;
        this.priority = RANDOM.nextInt(100000);
    }
}
