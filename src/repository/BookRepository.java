package repository;

import model.Book;
import utils.MyList;

public interface BookRepository {

    // create
    // Добавление книги
    Book addBook (String title,String author, String edition, int year);

    //-------------------------------------------------
    // read
    //Список всех книг
    MyList<Book> getAllBooks();

    // получение книги по id
    Book getById(int id);

    // Поиск книги по названию (полному или части названия)
    MyList<Book> getByTitle(String title);

    //  автору (полное имя или часть имени)
    MyList<Book> getByAuthor(String author);

    //Список всех свободных книг
    MyList<Book> getAllFreeBooks();

    // Список всех книг, находящихся сейчас у читателей
    MyList<Book> getAllBusyBooks();

    //-------------------------------------------------
    // update
    void saveBook(Book book);

    //-------------------------------------------------
    // delete
    void deleteById(int id);

}
