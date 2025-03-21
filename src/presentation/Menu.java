package presentation;

import model.Book;
import service.MeinService;
import utils.MyList;

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
            System.out.println("\nДобро пожаловать в меню библиотеки \"Знания Века\"\n");
            System.out.println("1. Каталог книг");
            System.out.println("2. Авторизация / Регистрация");
            System.out.println("0. Выход");

            System.out.println("\nСделайте, пожалуйста, выбор");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 0) {
                System.out.println("Спасибо за пользование библиотекой. До свидания!");
                System.exit(0);
            }
            showSubMenu(choice);
        }
    }

    // подменю Каталог книг
    private void showSubMenu(int choice) {
        switch (choice) {
            case 1:
                // TODO - Каталог книг
                showCatalogBookMenu();
                break;
            case 2:
                // TODO - Авторизация / Регистрация
                showAuthorizationMenu();
                break;
            default:
                System.out.println("Сделайте корректный выбор!");
                waitRead();
        }
    }

    private void showCatalogBookMenu() {
        while (true) {
            System.out.println("\n-------------------------------");
            System.out.println("Каталог книг");
            System.out.println("1. Список всех книг");
            System.out.println("2. Поиск книги по названию");
            System.out.println("3. Поиск книги по автору");
            System.out.println("0. Вернуться в предыдущее меню");

            //System.out.println("Сделайте, пожалуйста, выбор");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 0) break;
            // TODO showSubAuthorizationMenu()
            showSubCatalogBookMenu(choice);
        }
    }

    private void showSubCatalogBookMenu(int choice) {

        switch (choice) {
            case 1:
                System.out.println("Реализация - Список всех книг");
                service.getAllBooks();
                waitRead();
                break;
            case 2:
                System.out.println("Поиск книги по названию");
                System.out.println("Введите название книги:");

                //System.out.println("Сделайте, пожалуйста, выбор");
                String input = scanner.nextLine();

                Book result = service.getByTitle(input);

                if (result == null) {
                    System.out.println("Книга не найдена!");
                } else {
                    System.out.println(result.toString());
                }
                waitRead();
                break;
            case 3:
                // TODO Реализация - Поиск книги по автору
                /*
                String input = scanner.nextLine();
                MyList<Book> result = service.getByAuthor(input);

                if (result == null) {
                    System.out.println("Книга не найдена!");
                } else {
                    System.out.println(result.toString());
                }
                waitRead();
                */
                break;
            default:
                System.out.println("Сделайте корректный выбор!");
                waitRead();
        }
    }

    private void showAuthorizationMenu() {
        while (true) {
            System.out.println("\n-------------------------------");
            System.out.println("Авторизация / Регистрация");
            System.out.println("1. Войти в систему (Login)");
            System.out.println("2. Регистрация нового читателя");
            System.out.println("3. Выйти из системы (Logout)");
            System.out.println("0. Вернуться в предыдущее меню");

            //System.out.println("Сделайте, пожалуйста, выбор");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 0) break;
            // TODO showSubAuthorizationMenu()
            showSubAuthorizationMenu(choice);
        }
    }

    private void showSubAuthorizationMenu(int choice) {
        switch (choice) {
            case 1:
                // TODO Реализация входа в систему - Login
                System.out.println("Реализация входа в систему - Login");
                break;
            case 2:
                // TODO Реализация регистрации нового читателя
                System.out.println("Реализация регистрации нового читателя");
                break;
            case 3:
                // TODO Реализация выхода из системы
                System.out.println("Реализация выхода из системы");
                break;
            default:
                System.out.println("Сделайте корректный выбор!");
                waitRead();
        }
    }


    private void showLibrarianMenu() {
        while (true) {
            System.out.println("\n-------------------------------");
            System.out.println("Меню библиотекаря");
            System.out.println("1. Добавление книги");
            System.out.println("2. Список всех свободных книг");
            System.out.println("3. Список книг, которые сейчас у читателей");
            //System.out.println("4. Редактирование информации о книге");  // добавить поле "Заметки" в книгу!
            //System.out.println("5. Посмотреть у кого находится книга");
            // System.out.println("6. Удаление книги");
            System.out.println("0. Вернуться в предыдущее меню");

            //System.out.println("Сделайте, пожалуйста, выбор");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 0) break;
            // TODO подменю библиотекаря со switch choiceLibrarianMenu
            choiceSubLibrarianMenu(choice);
        }
    }

    private void choiceSubLibrarianMenu(int choice) {

        switch (choice) {
            case 1:
                // TODO Реализация - Добавление книги
                System.out.println("Реализация - Добавление книги");
                break;
            case 2:
                // TODO Реализация -  Список всех свободных книг
                System.out.println("Реализация - Список всех свободных книг");
                break;
            case 3:
                // TODO Реализация - Список всех книг, находящихся сейчас у читателей
                System.out.println("Список всех книг, находящихся сейчас у читателей");
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

}
