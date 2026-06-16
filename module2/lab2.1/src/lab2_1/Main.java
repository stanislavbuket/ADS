package lab2_1;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("\n=== Лабораторна робота 2.1 — Варіант 4 ===");
            System.out.println("1. Рівень 1: Чисельне інтегрування функції");
            System.out.println("2. Рівень 2: Знаходження коренів рівняння");
            System.out.println("3. Рівень 3: Розв'язання диференціального рівняння");
            System.out.println("4. Вихід");
            System.out.print("Оберіть пункт меню: ");
            
            if (!scanner.hasNextInt()) {
                System.out.println("Некоректне введення. Спробуйте ще раз.");
                scanner.next();
                continue;
            }
            
            int choice = scanner.nextInt();
            if (choice == 4) {
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
                case 3:
                    runLevel3(scanner);
                    break;
                default:
                    System.out.println("Невірний вибір. Будь ласка, оберіть від 1 до 4.");
            }
        }
        scanner.close();
    }

    private static void runLevel1(Scanner scanner) {
        System.out.println("\n--- Рівень 1: Чисельне інтегрування ---");
        System.out.println("Функція: f(x) = 0.5 * e^(sqrt(1 + 2x))");
        System.out.print("Введіть початок інтервалу a (рекомендовано 1.0): ");
        double a = scanner.nextDouble();
        System.out.print("Введіть кінець інтервалу b (рекомендовано 2.0): ");
        double b = scanner.nextDouble();
        System.out.print("Введіть крок інтегрування h (рекомендовано 0.2): ");
        double h = scanner.nextDouble();
        
        if (h <= 0 || a >= b) {
            System.out.println("Помилка: невірні межі або крок.");
            return;
        }

        double leftRect = IntegrationSolver.leftRectangles(a, b, h);
        double rightRect = IntegrationSolver.rightRectangles(a, b, h);
        double midRect = IntegrationSolver.midpointRectangles(a, b, h);
        double trap = IntegrationSolver.trapezoids(a, b, h);
        double simp = IntegrationSolver.simpson(a, b, h);

        System.out.println("\nРезультати обчислення інтеграла:");
        System.out.printf("  Метод лівих прямокутників:    %.8f\n", leftRect);
        System.out.printf("  Метод правих прямокутників:   %.8f\n", rightRect);
        System.out.printf("  Метод середніх прямокутників:  %.8f\n", midRect);
        System.out.printf("  Метод трапецій:               %.8f\n", trap);
        System.out.printf("  Метод Сімпсона:               %.8f\n", simp);
    }

    private static void runLevel2(Scanner scanner) {
        System.out.println("\n--- Рівень 2: Знаходження коренів рівняння ---");
        System.out.println("Рівняння: x^4 + 6x^2 - 12x - 8 = 0");
        System.out.println("Примітка: корені знаходяться приблизно біля -0.6 та 1.4.");
        
        System.out.print("Введіть ліву межу інтервалу a: ");
        double a = scanner.nextDouble();
        System.out.print("Введіть праву межу інтервалу b: ");
        double b = scanner.nextDouble();
        
        EquationSolver.findAndPrintRoots(a, b, 0.1);
        
        double ya = EquationSolver.y(a);
        double yb = EquationSolver.y(b);
        
        if (ya * yb > 0) {
            System.out.println("Помилка: На кінцях інтервалу функція повинна мати різні знаки для знаходження одного кореня.");
            System.out.println("Будь ласка, оберіть інтервал, що містить рівно один корінь (наприклад, [-1.0, 0.0] або [1.0, 2.0]).");
            return;
        }
        
        System.out.print("Введіть точність epsilon (наприклад, 1e-6): ");
        double eps = scanner.nextDouble();
        
        int maxIter = 1000;
        
        double rootBisection = EquationSolver.bisection(a, b, eps, maxIter);
        double rootNewton = EquationSolver.newton(a, b, eps, maxIter);
        double rootChord = EquationSolver.chord(a, b, eps, maxIter);
        
        System.out.println("\nРезультати знаходження кореня:");
        System.out.printf("  Метод половинчастого ділення: %.8f (значення функції: %.2e)\n", rootBisection, EquationSolver.y(rootBisection));
        System.out.printf("  Метод дотичних (Ньютона):     %.8f (значення функції: %.2e)\n", rootNewton, EquationSolver.y(rootNewton));
        System.out.printf("  Метод хорд:                   %.8f (значення функції: %.2e)\n", rootChord, EquationSolver.y(rootChord));
    }

    private static void runLevel3(Scanner scanner) {
        System.out.println("\n--- Рівень 3: Чисельне розв'язання ДУ ---");
        System.out.println("Рівняння: dy/dx = sin(x) - y");
        System.out.print("Введіть початкове значення аргументу x0 (наприклад, 0.0): ");
        double x0 = scanner.nextDouble();
        System.out.print("Введіть початкове значення функції y0 = y(x0) (наприклад, 0.0): ");
        double y0 = scanner.nextDouble();
        System.out.print("Введіть кінцеве значення аргументу xn (наприклад, 2.0): ");
        double xn = scanner.nextDouble();
        System.out.print("Введіть крок інтегрування h (наприклад, 0.2): ");
        double h = scanner.nextDouble();
        
        if (h <= 0 || x0 >= xn) {
            System.out.println("Помилка: невірні межі або крок.");
            return;
        }

        List<ODESolver.StepResult> results = ODESolver.solve(x0, xn, y0, h);
        
        System.out.println("\nТаблиця розв'язання диференціального рівняння (Рунге-Кутта 3-го порядку):");
        System.out.println("+------------+----------------+----------------+----------------+");
        System.out.println("|     x      |  y (чисельне)  | y (аналітичне) |    Похибка     |");
        System.out.println("+------------+----------------+----------------+----------------+");
        for (ODESolver.StepResult res : results) {
            System.out.printf("| %10.4f | %14.8f | %14.8f | %14.8e |\n", 
                res.x, res.yNumerical, res.yAnalytical, res.error);
        }
        System.out.println("+------------+----------------+----------------+----------------+");
    }
}
