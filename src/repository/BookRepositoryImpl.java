package repository;

import model.Book;
import utils.MyArrayList;
import utils.MyList;

import java.util.concurrent.atomic.AtomicInteger;

public class BookRepositoryImpl implements BookRepository {

    private final MyList<Book> books;

    // генератор id
    private final AtomicInteger currentId = new AtomicInteger(1000);

    public BookRepositoryImpl() {
        this.books = new MyArrayList<>();
        addStartBooks();
    }

    // список книг при запуске программы
    public void addStartBooks() {

        books.addAll(
                new Book(currentId.getAndIncrement(), "Колобок", "Народная сказка", "ОАО Солнце", 1990),
                new Book(currentId.getAndIncrement(), "Граф Монте-Кристо", "А. Дюма", "ОАО Первоцвет", 1997),
                new Book(currentId.getAndIncrement(), "Анна Каренина", "Л. Толстой", "ОАО Первоцвет", 1985)
        );
    }

    @Override
    public Book addBook(String title, String author, String edition, int year) {
        return null;
    }

    @Override
    public MyList<Book> getAllBooks() {
        return null;
    }

    @Override
    public Book getById(int id) {
        return null;
    }

    @Override
    public Book getByTitle(String title) {
        return null;
    }

    @Override
    public Book getByAuthor(String author) {
        return null;
    }

    @Override
    public MyList<Book> getAllFreeBooks() {
        return null;
    }

    @Override
    public MyList<Book> getAllBusyBooks() {
        return null;
    }

    //  Редактирование информации о книге (только ADMIN)
    @Override
    public void saveBook(Book book) {

    }

    @Override
    public void deleteById(int id) {

    }
}
