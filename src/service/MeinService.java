package service;

import model.Book;
import model.User;
import utils.MyList;

public interface MeinService {


    //Список всех книг
    void getAllBooks(MyList<Book> books);

    // Поиск книги по названию (полному или части названия)
    Book getByTitle(String title);

    //  автору (полное имя или часть имени)
    Book getByAuthor(String author);

    // Список книг, которые сейчас у пользователя - репозиторий ?
    MyList<Book> getUserBooks(int id);


    // Список всех занятых книг, находящихся сейчас у читателей
    MyList<Book> getAllBusyBooks();


    // Добавление книги
    Book addBook (String title,String author, String edition, int year);

    //Список всех свободных книг
    MyList<Book> getAllFreeBooks();


    //  Регистрация пользователя
    User registerUser(String email, String password);

    // Авторизация пользователей
    boolean loginUser(String email, String password);

    void logout();

    //Взятие книги из библиотеки
    boolean takeBook(int id);

    //Возврат книги в библиотеку
    boolean returnBook(int id);

    // Список книг, которые сейчас у пользователя - репозиторий ?
    MyList<Book> getUserBooks();


    // Посмотреть у кого находится книга, если занята
    User getUserWhoTakeBook(int Id);
}

