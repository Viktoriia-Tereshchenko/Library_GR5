package presentation;

import service.MeinService;

import java.util.Scanner;

public class Menu {

    private final MeinService service;
    private  final Scanner scanner = new Scanner(System.in);

    public Menu(MeinService service) {
        this.service = service;
    }

    public void start() {
      showMenu();
    }

    private void  showMenu() {
        while (true) {
            System.out.println("Добро пожаловать в меню библиотеки \"Знания Века\"");
            System.out.println("1. Меню читателя");
            System.out.println("2. Меню библиотекаря");
            System.out.println("3. Меню книг");
            System.out.println("0. Выход");

            System.out.println("Сделайте, пожалуйста, выбор");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 0) {
                System.out.println("Спасибо за пользование библиотекой. До свидания!");
                System.exit(0);
            }

            showSubMenu(choice);
        }
    }

    private  void  showSubMenu(int choice) {
        switch (choice) {
            case 1:
                // TODO Меню читателя
                showUserMenu();
                break;
            case 2:
                // TODO Меню библиотекаря
                break;
            case 3:
                // TODO Меню книг
                break;
            default:
                System.out.println("Сделайте корректный выбор!");
                waitRead();

        }
    }

    private  void  showUserMenu() {
        while (true) {
            System.out.println("Меню читателя");
            System.out.println("1. Войти в систему (Login)");
            System.out.println("2. Регистрация нового пользователя");
            System.out.println("3. Выйти из системы (Logout)");
            System.out.println("0. Вернуться в предыдущее меню");

            System.out.println("Сделайте, пожалуйста, выбор");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 0) break;
            // TODO подменю пользователя со switch choiceUserMenu
            choiceUserMenu(choice);

        }
    }

    private  void  choiceUserMenu(int choice) {

        switch (choice) {
            case 1:
                // TODO Реализация входа в систему - Login
                break;
            case 2:
                // TODO Реализация регистрации нового пользователя
                break;
            case 3:
                // TODO Реализация выхода из системы
                break;
            default:
                System.out.println("Сделайте корректный выбор!");
                waitRead();
        }
    }


    private void  waitRead() {
        System.out.println("\nДля продолжения нажмите Enter...");
        scanner.nextLine();
    }


}
