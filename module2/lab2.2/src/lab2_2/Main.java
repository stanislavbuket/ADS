package lab2_2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("\n=== Лабораторна робота 2.2 — Варіант 4 ===");
            System.out.println("1. Рівень 1: Аналіз слів за допомогою регулярного виразу (words.txt)");
            System.out.println("2. Рівень 2: switch-автомат для ручного введення");
            System.out.println("3. Рівень 3: табличний автомат для тексту в дужках (text.txt)");
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
                    runLevel1();
                    break;
                case 2:
                    runLevel2(scanner);
                    break;
                case 3:
                    runLevel3();
                    break;
                default:
                    System.out.println("Невірний вибір. Будь ласка, оберіть від 1 до 4.");
            }
        }
        scanner.close();
    }

    private static void runLevel1() {
        System.out.println("\n--- Рівень 1: Регулярний вираз ---");
        String filepath = "module2/lab2.2/words.txt";
        RegexMatcher.processFile(filepath);
    }

    private static void runLevel2(Scanner scanner) {
        System.out.println("\n--- Рівень 2: switch-автомат ---");
        System.out.println("Введіть рядок для перевірки (наприклад, <+123> або <-XYZ>):");
        System.out.print("Введення: ");
        String word = scanner.next();
        
        boolean valid = SwitchStateMachine.isValid(word);
        System.out.printf("Результат аналізу switch-автоматом: %s\n", 
            valid ? "VALID (Рядок відповідає мові)" : "INVALID (Рядок не відповідає мові)");
    }

    private static void runLevel3() {
        System.out.println("\n--- Рівень 3: Табличний автомат ---");
        String filepath = "module2/lab2.2/text.txt";
        TableStateMachine.processFile(filepath);
    }
}
