package service;

import model.Book;
import model.User;
import utils.MyList;

import java.util.List;

public interface MeinService {


    //Список всех книг
    MyList<Book> getAllBooks();

    // Поиск книги по названию (полному или части названия)
    MyList<Book> getByTitle(String title);

    // Поиск книги по автору (полное имя или часть имени)
    MyList<Book> getByAuthor(String author);

    // Список всех занятых книг, находящихся сейчас у читателей
    MyList<Book> getAllBusyBooks();

    // Добавление книги
    Book addBook (String title,String author, String edition, int year);

    //Список всех свободных книг
    MyList<Book> getAllFreeBooks();

    //  Регистрация пользователя
    User registerUser(String name, String email, String password);

    // Авторизация пользователей
    boolean loginUser(String email, String password);

    void logout();

    //Взятие книги из библиотеки
    boolean takeBook(int bookId, int userId);

    //Возврат книги в библиотеку
    boolean returnBook(int bookId, int userId);

    // Список книг, которые сейчас у пользователя - репозиторий ?
    MyList<Book> getUserBooks();

    // Посмотреть у кого находится книга, если занята
    User getUserWhoTakeBook(int Id);

    User getActiveUser();

    Book getBookById(int id);
}

