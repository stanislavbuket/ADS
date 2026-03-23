package lab1_5;

public class Main {
    private static final String SEPARATOR = "+-----------------+-----------------+--------+----------+-----------------+";
    private static final String ROW_FORMAT = "| %-15s | %-15s | %-6s | %-8s | %-15s |%n";
    private static final String DATA_FORMAT = "| %-15s | %-15s | %-6d | %-8s | %-15s |%n";

    public static void printTable(Student[] arr, int size) {
        System.out.println(SEPARATOR);
        System.out.printf(ROW_FORMAT, "Last Name", "First Name", "Course", "Group", "City");
        System.out.println(SEPARATOR);
        for (int i = 0; i < size; i++) {
            Student s = arr[i];
            System.out.printf(DATA_FORMAT, s.lastName(), s.firstName(), s.course(), s.group(), s.city());
        }
        System.out.println(SEPARATOR);
    }

    public static void printStudent(Student s) {
        if (s == null) {
            System.out.println("Student not found.");
            return;
        }
        System.out.println(SEPARATOR);
        System.out.printf(ROW_FORMAT, "Last Name", "First Name", "Course", "Group", "City");
        System.out.println(SEPARATOR);
        System.out.printf(DATA_FORMAT, s.lastName(), s.firstName(), s.course(), s.group(), s.city());
        System.out.println(SEPARATOR);
    }

    public static void main(String[] args) {
        Student[] students = {
            new Student("Shevchenko", "Mariia", 1, "KN-11", "Kyiv"),
            new Student("Kovalenko", "Ivan", 2, "KN-21", "Donetsk"),
            new Student("Boiko", "Petro", 1, "KN-12", "Lviv"),
            new Student("Tkachenko", "Anna", 3, "KN-31", "Kharkiv"),
            new Student("Lysenko", "Olena", 2, "KN-22", "Odesa"),
            new Student("Honchar", "Viktoriia", 1, "KN-11", "Dnipro"),
            new Student("Melnyk", "Sofiia", 4, "KN-41", "Zaporizhzhia"),
            new Student("Kozak", "Daryna", 2, "KN-21", "Donetsk"),
            new Student("Pavlenko", "Oleh", 3, "KN-32", "Kyiv"),
            new Student("Kravchenko", "Ihor", 1, "KN-12", "Lutsk"),
            new Student("Moroz", "Andrii", 4, "KN-42", "Ternopil"),
            new Student("Savchenko", "Yulia", 2, "KN-22", "Poltava"),
            new Student("Hrytsenko", "Denys", 3, "KN-31", "Rivne"),
            new Student("Boyko", "Oksana", 1, "KN-11", "Vinnytsia"),
            new Student("Rudenko", "Maksym", 2, "KN-21", "Chernihiv"),
            new Student("Koval", "Kateryna", 4, "KN-41", "Sumy"),
            new Student("Didenko", "Artem", 3, "KN-32", "Cherkasy"),
            new Student("Petryk", "Dmytro", 1, "KN-12", "Mykolaiv"),
            new Student("Zubko", "Tetiana", 2, "KN-22", "Kherson"),
            new Student("Klym", "Yevhen", 3, "KN-31", "Uzhhorod")
        };

        System.out.println("=== Level 1: Array Search ===");
        ArraySearch arraySearch = new ArraySearch(30);
        for (Student s : students) {
            arraySearch.insert(s);
        }
        
        System.out.println("Attempting to insert a duplicate last name (Shevchenko): " + 
            arraySearch.insert(new Student("Shevchenko", "Oleksandr", 1, "KN-11", "Lviv")));
        
        System.out.println("\nInitial Array (inserted unordered):");
        arraySearch.print();

        String targetLevel1 = "Kovalenko";
        System.out.println("\nDeleting student '" + targetLevel1 + "' if they live in Donetsk (using binary search)...");
        boolean deleted = arraySearch.processTask(targetLevel1);
        System.out.println("Deleted? " + deleted);
        
        System.out.println("\nArray After Deletion (was sorted for binary search):");
        arraySearch.print();

        System.out.println("\n=== Level 2: BST ===");
        BST bst = new BST();
        for (int i = 0; i < 7; i++) {
            bst.insertLevel2(students[i]);
        }
        bst.printBFS();
        
        String targetLevel2 = "Lviv";
        System.out.println("\nSearching for city '" + targetLevel2 + "':");
        TreeNode foundNode = bst.search(targetLevel2);
        printStudent(foundNode != null ? foundNode.student : null);

        System.out.println("\n=== Level 3: Randomized BST (Treap) ===");
        BST treap = new BST();
        for (int i = 0; i < 7; i++) {
            treap.insertLevel3(students[i]);
        }
        treap.printBFS();
        
        System.out.println("\nSearching for city '" + targetLevel2 + "':");
        TreeNode foundNodeTreap = treap.search(targetLevel2);
        printStudent(foundNodeTreap != null ? foundNodeTreap.student : null);
    }
}
