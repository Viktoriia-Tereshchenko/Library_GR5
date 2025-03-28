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
            System.out.println("--------------------------------------------------");
            System.out.println("Добро пожаловать в меню библиотеки \"Знания Века\"");
            System.out.println("--------------------------------------------------");
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
                System.out.println("\n--------Вход в систему---------");
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

                System.out.printf("\nBы авторизованы. %s: %s",
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
                if (service.getActiveUser() != null) {
                    System.out.println("Вы уже авторизованы! Сначала выйдите из системы!");
                    waitRead();
                    break;
                }

                System.out.println("Введите имя:");
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
                System.out.println("-----------Мои книги-----------");
                showBooksList(service.getUserBooks());
                waitRead();
                break;
            case 2:
                System.out.println("----------Взять книгу----------");
                System.out.println("Введите номер книги:");

                int input = scanner.nextInt();
                scanner.nextLine();

                if (service.takeBook(input, service.getActiveUser().getUserId())) {
                    //System.out.println("Вам выдана книга № " + input);
                    System.out.printf("Вам выдана книга № %d (%s / %s)\n", input,
                            service.getBookById(input).getTitle(), service.getBookById(input).getAuthor());
                } else {
                    System.out.println("Невозможно выдать книгу № " + input);
                }
                waitRead();
                break;

            case 3:
                System.out.println("----------Вернуть книгу----------");
                System.out.println("Введите номер книги:");

                int inputId = scanner.nextInt();
                scanner.nextLine();

                if (service.returnBook(inputId, service.getActiveUser().getUserId())) {
                    System.out.println("Вы вернули книгу № " + inputId);
                } else {
                    System.out.println("Невозможно вернуть книгу № " + inputId);
                }
                ;
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
            System.out.println("1. Добавить книгу");
            System.out.println("2. Список всех свободных книг");
            System.out.println("3. Список книг, которые сейчас у читателей");
            System.out.println("4. Каталог книг");
            System.out.println("5. Посмотреть, у кого находится книга");
            // System.out.println("4. Редактирование информации о книге"); // добавить поле "Заметки" в книгу!
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
                System.out.println("--------Добавление книги--------");
                System.out.println("Введите название книги:");
                String title = scanner.nextLine();

                System.out.println("Введите автора:");
                String author = scanner.nextLine();

                System.out.println("Введите издание:");
                String edition = scanner.nextLine();

                System.out.println("Введите год:");
                int year = scanner.nextInt();
                scanner.nextLine();

                Book result = service.addBook(title, author, edition, year);

                if (result == null) {
                    System.out.println("Книга не добавлена!");
                } else {
                    System.out.println("Вы успешно добавили книгу в каталог!");
                }
                waitRead();
                break;

            case 2:
                System.out.println("------Все свободные книги------");
                showBooksList(service.getAllFreeBooks());
                waitRead();
                break;

            case 3:
                System.out.println("--Книги, находящиеся у читателей--");
                showBooksList(service.getAllBusyBooks());
                waitRead();
                break;

            case 4:
                //Каталог книг
                showCatalogBookMenu();
                break;

            case 5:
                System.out.println("----Поиск читателя по № книгу----");

                System.out.println("Укажите № книги:");
                int id = scanner.nextInt();
                scanner.nextLine();

                User userTakeBook = service.getUserWhoTakeBook(id);
                if (userTakeBook != null)
                    System.out.printf("Книга выдана читателю: имя - %s | email - %s\n",
                            userTakeBook.getName(), userTakeBook.getEmail());
                waitRead();
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
