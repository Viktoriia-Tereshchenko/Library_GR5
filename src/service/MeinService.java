package service;

import model.Book;
import model.User;
import utils.MyList;

public interface MeinService {

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

