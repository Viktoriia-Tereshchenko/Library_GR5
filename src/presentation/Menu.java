package presentation;

import model.Book;
import model.Role;
import model.User;
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
                showCatalogBookMenu();
                break;
            case 2:
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

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 0) break;
            showSubCatalogBookMenu(choice);
        }
    }

    private void showSubCatalogBookMenu(int choice) {

        switch (choice) {
            case 1:
                System.out.println("-------Список всех книг-------");
                MyList<Book> listAllBooks = service.getAllBooks();
                showBooksList(listAllBooks);
                waitRead();
                break;
            case 2:
                System.out.println("---Поиск книги по названию---");
                System.out.println("Введите название книги:");

                String strTitle = scanner.nextLine();
                MyList<Book> listByTitle = service.getByTitle(strTitle);
                showBooksList(listByTitle);
                waitRead();
                break;

            case 3:
                System.out.println("----Поиск книги по автору----");
                System.out.println("Введите автора книги:");

                String strAuthor = scanner.nextLine();
                MyList<Book> listByAuthor = service.getByAuthor(strAuthor);
                showBooksList(listByAuthor);
                waitRead();
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

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 0) break;
            showSubAuthorizationMenu(choice);
        }
    }

    private void showSubAuthorizationMenu(int choice) {
        switch (choice) {
            case 1:
                // Login
                System.out.println("\n-------------------------------");

                if (service.getActiveUser() != null) {
                    System.out.println("Вы уже авторизованы!");
                    waitRead();
                    break;
                }
                System.out.println("Введите email : ");
                String logEmail = scanner.nextLine();

                System.out.println("Введите пароль : ");
                String logPassword = scanner.nextLine();

                if (!service.loginUser(logEmail, logPassword)) {
                    System.out.println("Некорректные данные! Не удалось войти в систему");
                    waitRead();
                    break;
                }
                Role role = service.getActiveUser().getRole();

                System.out.printf("Вы авторизованы. %s: %s",
                        (role == Role.READER) ? "Читатель" : "Библиотекарь",
                        service.getActiveUser().getName());

                if (role == Role.READER) showReaderMenu();
                if (role == Role.ADMIN) showLibrarianMenu();
                //waitRead();
                break;

            case 2:
                // Регистрация
                System.out.println("\n-------------------------------");
                System.out.println("Регистрация нового читателя");
                System.out.println("Введите имя и фамилию:");
                String name = scanner.nextLine();

                System.out.println("Введите email:");
                String email = scanner.nextLine();

                System.out.println("Введите пароль:");
                String password = scanner.nextLine();

                User user = service.registerUser(name, email, password);

                if (user == null) {
                    System.out.println("Регистрация не выполнена!");
                } else {
                    System.out.println("Вы успешно зарегистрировались в системе!");
                }
                waitRead();
                break;

            case 3:
                // Logout
                if (service.getActiveUser() == null) {
                    System.out.println("Сейчас в системе нет авторизованных пользователей!");
                    waitRead();
                    break;
                }
                service.logout();
                System.out.println("Вы вышли из системы");
                waitRead();
                break;

            default:
                System.out.println("Сделайте корректный выбор!");
                waitRead();
        }
    }

    private void showReaderMenu() {
        while (true) {
            System.out.println("\n-------------------------------");
            System.out.println("Меню читателя");
            System.out.println("1. Мои книги");
            System.out.println("2. Взять книгу");
            System.out.println("3. Вернуть книгу");
            System.out.println("4. Каталог книг");
            System.out.println("0. Вернуться в предыдущее меню");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 0) break;
            showSubReaderMenu(choice);
        }
    }

    private void showSubReaderMenu(int choice) {
        switch (choice) {
            case 1:
                // TODO Реализация - Мои книги
                System.out.println("Реализация - Мои книги");
                waitRead();
                break;
            case 2:
                // TODO Реализация -  Взять книгу
                System.out.println("Реализация - Взять книгу");
                waitRead();
                break;
            case 3:
                // TODO Реализация -  Вернуть книгу
                System.out.println("Реализация - Вернуть книгу");
                waitRead();
                break;
            case 4:
                //Каталог книг
                showCatalogBookMenu();
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
            System.out.println("4. Каталог книг");
            // System.out.println("4. Редактирование информации о книге"); // добавить поле "Заметки" в книгу!
            // System.out.println("5. Посмотреть у кого находится книга");
            // System.out.println("6. Удаление книги");
            System.out.println("0. Вернуться в предыдущее меню");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 0) break;
            choiceSubLibrarianMenu(choice);
        }
    }

    private void choiceSubLibrarianMenu(int choice) {

        switch (choice) {
            case 1:
                // TODO Реализация - Добавление книги
                System.out.println("Реализация - Добавление книги");
                waitRead();
                break;
            case 2:
                // TODO Реализация -  Список всех свободных книг
                System.out.println("Реализация - Список всех свободных книг");
                waitRead();
                break;
            case 3:
                // TODO Реализация - Список всех книг, находящихся сейчас у читателей
                System.out.println("Список всех книг, находящихся сейчас у читателей");
                waitRead();
                break;
            case 4:
                //Каталог книг
                showCatalogBookMenu();
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

    // вызываем каждый раз при выводе наших списков
    private void showBooksList(MyList<Book> books) {

        if (books == null || books.isEmpty()) {
            System.out.println("Книги не найдены!");
            return;
        }
        for (Book book : books) {
            //System.out.println(book);
            System.out.printf("№ %d - \"%s\" / %s / %s / %d\n",
                    book.getId(), book.getTitle(), book.getAuthor(), book.getEdition(), book.getYear());
        }
    }
}
