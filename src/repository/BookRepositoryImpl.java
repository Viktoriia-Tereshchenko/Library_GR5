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
                new Book(currentId.getAndIncrement(), "Колобок", "Народная сказка", "ОАО Ранок", 1991),
                new Book(currentId.getAndIncrement(), "Граф Монте-Кристо", "А. Дюма", "ОАО Первоцвет", 1997),
                new Book(currentId.getAndIncrement(), "Анна Каренина", "Л.Н. Толстой", "ОАО Первоцвет", 1985)
        );
    }

    @Override
    public Book addBook(String title, String author, String edition, int year) {
        Book book = new Book(currentId.getAndIncrement(), title, author, edition, year);

        books.add(book);

        return book;
    }

    @Override
    public MyList<Book> getAllBooks() {
        return books;
    }

    @Override
    public Book getById(int id) {
        for (Book book: books) {
            if (book.getId() == id)
                return book;
        }
        return null;
    }

    @Override
    public MyList<Book> getByTitle(String title) {
        MyList<Book> result = new MyArrayList<>();
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(title.trim().toLowerCase())) {
                result.add(book);
            }
        }
        return result;
    }


    @Override
    public MyList<Book> getByAuthor(String author) {
        MyList<Book> result = new MyArrayList<>();

        for (Book book : books) {

            if (book.getAuthor().toLowerCase().contains(author.trim().toLowerCase())) {
                result.add(book);
            }
        }
        return result;
    }

    @Override
    public MyList<Book> getAllFreeBooks() {
        return null;
    }

    @Override
    public MyList<Book> getAllBusyBooks() {
        return null;
    }

    @Override
    public Book saveBook(Book book) {
        // изменяемые поля
        // название
        getById(book.getId()).setTitle(book.getTitle());

        // автор
        getById(book.getId()).setAuthor(book.getAuthor());

        // издание
        getById(book.getId()).setEdition(book.getEdition());

        // год
        //private int year;
        getById(book.getId()).setYear(book.getYear());

        // признак, что книга выдана
        getById(book.getId()).setBusy(book.isBusy());

        return getById(book.getId());
    }

    // TODO
    @Override
    public void deleteById(int id) {
    }
}
