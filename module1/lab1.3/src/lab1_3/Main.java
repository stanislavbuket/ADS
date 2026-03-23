package lab1_3;

import java.util.List;

public class Main {
    private static final String SEPARATOR = "+-----------------+-----------------+--------+------------+------------+-----------------+";
    private static final String ROW_FORMAT = "| %-15s | %-15s | %-6s | %-10s | %-10s | %-15s |%n";
    private static final String DATA_FORMAT = "| %-15s | %-15s | %-6d | %-10d | %-10s | %-15s |%n";

    private static BinaryTree buildTree() {
        BinaryTree tree = new BinaryTree();
        tree.insert(new Student("Kovalenko", "Ivan", 2, 100, "Male", "Apartment"));
        tree.insert(new Student("Shevchenko", "Mariia", 1, 50, "Female", "Dormitory"));
        tree.insert(new Student("Boiko", "Petro", 3, 150, "Male", "Apartment"));
        tree.insert(new Student("Tkachenko", "Anna", 1, 25, "Female", "Dormitory"));
        tree.insert(new Student("Lysenko", "Olena", 1, 75, "Female", "Dormitory"));
        tree.insert(new Student("Honchar", "Viktoriia", 1, 125, "Female", "Dormitory"));
        tree.insert(new Student("Melnyk", "Sofiia", 1, 175, "Female", "Dormitory"));
        tree.insert(new Student("Kozak", "Daryna", 1, 10, "Female", "Apartment"));
        tree.insert(new Student("Pavlenko", "Oleh", 1, 130, "Male", "Dormitory"));
        return tree;
    }

    public static void main(String[] args) {
        BinaryTree tree = buildTree();

        System.out.println("Initial Tree (In-Order Traversal):");
        tree.printTable();

        System.out.println("\nSearch Results (1st-year female students living in dormitory):");
        List<Student> targets = tree.searchTarget();
        if (targets.isEmpty()) {
            System.out.println("No students found matching the criteria.");
        } else {
            System.out.println(SEPARATOR);
            System.out.printf(ROW_FORMAT, "Last Name", "First Name", "Course", "ID", "Gender", "Residence");
            System.out.println(SEPARATOR);
            for (Student s : targets) {
                System.out.printf(DATA_FORMAT,
                    s.lastName(), s.firstName(), s.course(), s.studentId(), s.gender(), s.residence());
            }
            System.out.println(SEPARATOR);
        }

        System.out.println("\nDeleting found nodes...");
        tree.deleteTargets();

        System.out.println("\nTree after deletion:");
        tree.printTable();
    }
}
