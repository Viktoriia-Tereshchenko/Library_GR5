package presentation;

import service.MeinService;

import java.util.Scanner;

public class Menu {

    private final MeinService service;
    private final Scanner scanner = new Scanner(System.in);

    public Menu(MeinService service) {
        this.service = service;
    }

    public void start() {
        showMenu();
    }



    private void showMenu() {
        while (true) {
            System.out.println("Добро пожаловать в меню библиотеки \"Знания Века\"");
            System.out.println("1. Каталог книг");
            System.out.println("2. Авторизация / Регистрация ");
            System.out.println("0. Выход");

            System.out.println("\nСделайте, пожалуйста, выбор");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 0) {
                System.out.println("Спасибо за пользование библиотекой. До свидания!");
                System.exit(0);
            }

            //showSubMenu(choice);
        }
    }

    // подменю Каталог книг

/*



    private void showSubMenu(int choice) {
        switch (choice) {
            case 1:
                // TODO Меню читателя
                showUserMenu();
                break;
            case 2:
                // TODO Меню библиотекаря
                showLibrarianMenu();
                break;
            case 3:
                // TODO Меню книг
                break;
            default:
                System.out.println("Сделайте корректный выбор!");
                waitRead();

        }
    }

    private void showUserMenu() {
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

    private void choiceUserMenu(int choice) {

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

    private void showLibrarianMenu() {
        while (true) {
            System.out.println("Меню библиотекаря");
            System.out.println("1. Добавление книги");
            System.out.println("2. Список всех книг");
            System.out.println("3. Поиск книги по названию");  // + USER
            System.out.println("4. Поиск книги по автору");  // + USER
            System.out.println("5. Список всех свободных книг");  // + USER
            System.out.println("6. Список книг, которые сейчас у пользователя");  // + USER
            System.out.println("7. Редактирование информации о книге");  // добавить поле "Заметки" в книгу!
            System.out.println("8. Посмотреть у кого находится книга");
            System.out.println("9. Удаление книги");
            System.out.println("0. Вернуться в предыдущее меню");

            System.out.println("Сделайте, пожалуйста, выбор");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 0) break;
            // TODO подменю библиотекаря со switch choiceLibrarianMenu
            choiceLibrarianMenu(choice);
        }
    }

    private void choiceLibrarianMenu(int choice) {

        switch (choice) {
            case 1:
                // TODO Реализация - Добавление книги
                break;
            case 2:
                // TODO Реализация - Список всех книг
                break;
            case 3:
                // TODO Реализация - Поиск книги по названию
                break;
            case 4:
                // TODO Реализация - Поиск книги по автору
                break;
            case 5:
                // TODO Реализация - Список всех свободных книг
                break;
            case 6:
                // TODO Реализация - Список книг, которые сейчас у пользователя
                break;
            case 7:
                // TODO Реализация - Редактирование информации о книге  // добавить поле "Заметки" в книгу!
                break;
            case 8:
                // TODO Реализация - Посмотреть у кого находится книга
                break;
            case 9:
                // TODO Реализация - Вернуться в предыдущее меню
                break;

            default:
                System.out.println("Сделайте корректный выбор!");
                waitRead();
        }
    }

    private void waitRead() {
        System.out.println("\nДля продолжения нажмите Enter...");
        scanner.nextLine();
    }

    */




}
