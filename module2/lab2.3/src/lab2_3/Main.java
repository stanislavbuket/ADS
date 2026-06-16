package lab2_3;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("\n=== Лабораторна робота 2.3 — Варіант 4 ===");
            System.out.println("1. Рівень 1: Кількість варіантів розкладу (Розміщення без повторень)");
            System.out.println("2. Рівень 2: Кількість дев'ятизначних чисел (Перестановки з повтореннями)");
            System.out.println("3. Рівень 3: Запис усіх варіантів розкладу у файл");
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
        System.out.println("\n--- Рівень 1: Розміщення без повторень ---");
        System.out.println("Задача: Скільки варіантів розкладу можна скласти на один день, якщо в день викладають 3 різні дисципліни з 9?");
        System.out.print("Введіть загальну кількість дисциплін n (рекомендовано 9): ");
        int n = scanner.nextInt();
        System.out.print("Введіть кількість пар на день k (рекомендовано 3): ");
        int k = scanner.nextInt();
        
        if (n < 0 || k < 0 || k > n) {
            System.out.println("Помилка: невірні параметри n або k.");
            return;
        }

        long ans = CombinatoricsSolver.arrangementsWithoutRepetitions(n, k);
        System.out.printf("Тип вибірки: Розміщення без повторень (A_%d^%d)\n", n, k);
        System.out.printf("Формула: A_n^k = n! / (n-k)!\n");
        System.out.printf("Результат: %d\n", ans);
    }

    private static void runLevel2(Scanner scanner) {
        System.out.println("\n--- Рівень 2: Перестановки з повтореннями ---");
        System.out.println("Задача: Кількість 9-значних чисел, сформованих з цифр 1 (повторюється 2 рази), 2 (повторюється 3 рази), 3 (повторюється 4 рази).");
        System.out.print("Введіть кількість різних груп цифр (рекомендовано 3): ");
        int groupsCount = scanner.nextInt();
        int[] repetitions = new int[groupsCount];
        int sum = 0;
        for (int i = 0; i < groupsCount; i++) {
            System.out.printf("Введіть кількість повторень для групи %d: ", i + 1);
            repetitions[i] = scanner.nextInt();
            sum += repetitions[i];
        }
        
        System.out.printf("Загальна кількість елементів n = %d\n", sum);
        
        long ans = CombinatoricsSolver.permutationsWithRepetitions(sum, repetitions);
        System.out.printf("Тип вибірки: Перестановки з повтореннями (P_%d(%s))\n", sum, getRepetitionsString(repetitions));
        System.out.printf("Формула: P_n = n! / (n1! * n2! * ... * nm!)\n");
        System.out.printf("Результат: %d\n", ans);
    }

    private static String getRepetitionsString(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i < arr.length - 1) sb.append(", ");
        }
        return sb.toString();
    }

    private static void runLevel3(Scanner scanner) {
        System.out.println("\n--- Рівень 3: Генерація розміщень та запис у файл ---");
        System.out.print("Введіть n (рекомендовано 9): ");
        int n = scanner.nextInt();
        System.out.print("Введіть k (рекомендовано 3): ");
        int k = scanner.nextInt();
        
        if (n < 0 || k < 0 || k > n) {
            System.out.println("Помилка: невірні параметри n або k.");
            return;
        }
        
        System.out.println("Генерація комбінацій...");
        List<List<Integer>> placements = CombinatoricsSolver.generateAllPlacements(n, k);
        String filepath = "module2/lab2.3/combinations.txt";
        CombinatoricsSolver.writePlacementsToFile(filepath, placements);
    }
}
