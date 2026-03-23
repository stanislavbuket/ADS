package lab1_3;

import java.util.ArrayList;
import java.util.List;

public class BinaryTree {
    private TreeNode root;

    public void insert(Student student) {
        root = insertRec(root, student);
    }

    private TreeNode insertRec(TreeNode node, Student student) {
        if (node == null) {
            return new TreeNode(student);
        }
        if (student.studentId() < node.student.studentId()) {
            node.left = insertRec(node.left, student);
        } else if (student.studentId() > node.student.studentId()) {
            node.right = insertRec(node.right, student);
        }
        return node;
    }

    private static final String SEPARATOR = "+-----------------+-----------------+--------+------------+------------+-----------------+";
    private static final String ROW_FORMAT = "| %-15s | %-15s | %-6s | %-10s | %-10s | %-15s |%n";
    private static final String DATA_FORMAT = "| %-15s | %-15s | %-6d | %-10d | %-10s | %-15s |%n";

    public void printTable() {
        System.out.println(SEPARATOR);
        System.out.printf(ROW_FORMAT, "Last Name", "First Name", "Course", "ID", "Gender", "Residence");
        System.out.println(SEPARATOR);
        printInOrder(root);
        System.out.println(SEPARATOR);
    }

    private void printInOrder(TreeNode node) {
        if (node != null) {
            printInOrder(node.left);
            Student s = node.student;
            System.out.printf(DATA_FORMAT,
                s.lastName(), s.firstName(), s.course(), s.studentId(), s.gender(), s.residence());
            printInOrder(node.right);
        }
    }

    public List<Student> searchTarget() {
        List<Student> result = new ArrayList<>();
        searchTargetRec(root, result);
        return result;
    }

    private void searchTargetRec(TreeNode node, List<Student> result) {
        if (node != null) {
            searchTargetRec(node.left, result);
            if (isTarget(node.student)) {
                result.add(node.student);
            }
            searchTargetRec(node.right, result);
        }
    }

    private boolean isTarget(Student s) {
        return s.course() == 1 && 
               "Female".equalsIgnoreCase(s.gender()) && 
               "Dormitory".equalsIgnoreCase(s.residence());
    }

    public void deleteTargets() {
        List<Student> targets = searchTarget();
        for (Student target : targets) {
            root = deleteRec(root, target.studentId());
        }
    }

    private TreeNode deleteRec(TreeNode root, long id) {
        if (root == null) return null;

        if (id < root.student.studentId()) {
            root.left = deleteRec(root.left, id);
        } else if (id > root.student.studentId()) {
            root.right = deleteRec(root.right, id);
        } else {
            if (root.left == null) return root.right;
            else if (root.right == null) return root.left;

            root.student = minValue(root.right);
            root.right = deleteRec(root.right, root.student.studentId());
        }
        return root;
    }

    private Student minValue(TreeNode root) {
        Student minv = root.student;
        while (root.left != null) {
            minv = root.left.student;
            root = root.left;
        }
        return minv;
    }
}
