package homework;

import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            System.setOut(new java.io.PrintStream(System.out, true, "UTF-8"));
        } catch (java.io.UnsupportedEncodingException e) {
            // fallback
        }
        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(Locale.US);
        
        while (true) {
            System.out.println("\n=== Домашня робота — Варіант 4 ===");
            System.out.println("1. Рівень 1: Розв'язання СЛАР методом LUP-розкладання");
            System.out.println("2. Рівень 2: Зв'язування та обхід графа мінімального зараження (MST + DFS)");
            System.out.println("3. Вихід");
            System.out.print("Оберіть пункт меню: ");
            
            if (!scanner.hasNextInt()) {
                System.out.println("Некоректне введення. Спробуйте ще раз.");
                scanner.next();
                continue;
            }
            
            int choice = scanner.nextInt();
            if (choice == 3) {
                System.out.println("Завершення роботи.");
                break;
            }
            
            switch (choice) {
                case 1:
                    runLevel1(scanner);
                    break;
                case 2:
                    runLevel2(scanner);
                    break;
                default:
                    System.out.println("Невірний вибір. Спробуйте ще раз.");
            }
        }
        scanner.close();
    }

    private static void runLevel1(Scanner scanner) {
        System.out.println("\n--- Рівень 1: LUP-розкладання для СЛАР ---");
        System.out.println("Бажаєте використовувати СЛАР за Варіантом 4 (1) чи ввести власну (2)?");
        System.out.print("Вибір: ");
        int choice = scanner.nextInt();
        
        double[][] A;
        double[] b;
        
        if (choice == 1) {
            A = new double[][]{
                {2.0, -9.0, -3.0, -6.0},
                {3.0, -5.0, 4.0, 1.0},
                {0.0, 1.0, 4.0, -3.0},
                {6.0, -3.0, 9.0, 6.0}
            };
            b = new double[]{-22.0, 6.0, 62.0, 30.0};
        } else {
            System.out.print("Введіть розмірність matrix n: ");
            int n = scanner.nextInt();
            A = new double[n][n];
            b = new double[n];
            System.out.println("Введіть коефіцієнти матриці A рядок за рядком (через пробіл):");
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    A[i][j] = scanner.nextDouble();
                }
            }
            System.out.println("Введіть вільні члени b:");
            for (int i = 0; i < n; i++) {
                b[i] = scanner.nextDouble();
            }
        }

        System.out.println("\nВхідна система рівнянь:");
        LUPSolver.printSystem(A, b);

        try {
            LUPSolver.LUPResult result = LUPSolver.decomposeAndSolve(A, b);
            
            System.out.println("\nМатриця L (нижня трикутна з одиничною діагоналлю):");
            LUPSolver.printMatrix(result.L);
            
            System.out.println("\nМатриця U (верхня трикутна):");
            LUPSolver.printMatrix(result.U);
            
            System.out.println("\nМатриця перестановки P:");
            LUPSolver.printMatrix(result.P);
            
            System.out.println("\nРозв'язок системи x:");
            for (int i = 0; i < result.x.length; i++) {
                System.out.printf("  x%d = %10.6f\n", i + 1, result.x[i]);
            }
        } catch (Exception e) {
            System.out.println("Помилка при обчисленні: " + e.getMessage());
        }
    }

    private static void runLevel2(Scanner scanner) {
        System.out.println("\n--- Рівень 2: MST + DFS для графа зараження ---");
        System.out.print("Введіть ширину сітки W (наприклад, 4): ");
        int w = scanner.nextInt();
        System.out.print("Введіть висоту сітки H (наприклад, 4): ");
        int h = scanner.nextInt();
        
        if (w <= 0 || h <= 0) {
            System.out.println("Невірні розміри сітки.");
            return;
        }

        double[] radiation = new double[w * h];
        System.out.println("Бажаєте ввести рівні радіації вручну (1) чи згенерувати випадково (2)?");
        System.out.print("Вибір: ");
        int choice = scanner.nextInt();
        
        if (choice == 1) {
            System.out.printf("Введіть %d значень радіації (від 0 до 100):\n", w * h);
            for (int i = 0; i < w * h; i++) {
                System.out.printf("  Комірка %d (x=%d, y=%d): ", i, i % w, i / w);
                radiation[i] = scanner.nextDouble();
            }
        } else {
            Random rand = new Random(42); // Seed 42 for reproducibility
            System.out.println("Згенеровані рівні радіації на сітці:");
            for (int y = 0; y < h; y++) {
                for (int x = 0; x < w; x++) {
                    int idx = y * w + x;
                    radiation[idx] = rand.nextInt(101);
                    System.out.printf("%4d ", (int) radiation[idx]);
                }
                System.out.println();
            }
        }

        GraphSolver solver = new GraphSolver(w, h, radiation);
        double[][] mst = solver.findMST();
        
        System.out.println("\nМатриця суміжності для мінімального кістякового дерева (MST):");
        LUPSolver.printMatrix(mst);
        
        System.out.printf("\nВведіть початкову вершину для DFS обходу (0 до %d): ", w * h - 1);
        int startNode = scanner.nextInt();
        if (startNode < 0 || startNode >= w * h) {
            System.out.println("Невірна вершина. Обхід почнеться з 0.");
            startNode = 0;
        }
        
        List<Integer> dfsPath = solver.runDFS(mst, startNode);
        System.out.println("\nПослідовність вершин при DFS обході:");
        System.out.print("  ");
        for (int i = 0; i < dfsPath.size(); i++) {
            System.out.print(dfsPath.get(i));
            if (i < dfsPath.size() - 1) {
                System.out.print(" -> ");
            }
        }
        System.out.println();
    }
}
