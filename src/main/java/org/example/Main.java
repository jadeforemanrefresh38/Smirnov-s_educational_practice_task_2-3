package org.example;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

class Task implements Serializable {
    private String title;

    public Task(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }
}

public class Main {
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static final String FILE_NAME = "tasks.dat";
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        loadFromFile();

        int choice;
        do {
            showMenu();
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    showTasks();
                    break;
                case 2:
                    addTask();
                    break;
                case 3:
                    deleteTask();
                    break;
                case 4:
                    editTask();
                    break;
                case 0:
                    saveToFile();
                    System.out.println("Выход...");
                    break;
                default:
                    System.out.println("Неверный выбор!");
            }
        } while (choice != 0);
    }

    private static void showMenu() {
        System.out.println("\n=== ToDo List ===");
        System.out.println("1. Показать задачи");
        System.out.println("2. Добавить задачу");
        System.out.println("3. Удалить задачу");
        System.out.println("4. Редактировать задачу");
        System.out.println("0. Выход");
        System.out.print("Выберите: ");
    }

    private static void showTasks() {
        if (tasks.isEmpty()) {
            System.out.println("Список пуст.");
            return;
        }

        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(i + 1 + ". " + tasks.get(i));
        }
    }

    private static void addTask() {
        System.out.print("Введите задачу: ");
        String text = scanner.nextLine();
        tasks.add(new Task(text));
        System.out.println("Задача добавлена.");
    }

    private static void deleteTask() {
        showTasks();
        if (tasks.isEmpty()) return;

        System.out.print("Введите номер задачи для удаления: ");
        int index = scanner.nextInt() - 1;

        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
            System.out.println("Задача удалена.");
        } else {
            System.out.println("Ошибка!");
        }
    }

    private static void editTask() {
        showTasks();
        if (tasks.isEmpty()) return;

        System.out.print("Введите номер задачи для редактирования: ");
        int index = scanner.nextInt() - 1;
        scanner.nextLine();

        if (index >= 0 && index < tasks.size()) {
            System.out.print("Введите новый текст: ");
            String newText = scanner.nextLine();
            tasks.get(index).setTitle(newText);
            System.out.println("Задача обновлена.");
        } else {
            System.out.println("Ошибка!");
        }
    }

    private static void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(tasks);
            System.out.println("Сохранено в файл.");
        } catch (IOException e) {
            System.out.println("Ошибка сохранения!");
        }
    }

    private static void loadFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            tasks = (ArrayList<Task>) ois.readObject();
            System.out.println("Данные загружены.");
        } catch (Exception e) {
            System.out.println("Файл не найден, будет создан новый.");
        }
    }
}