package service;

import model.Book;
import model.User;
import repository.BookRepository;
import repository.UserRepository;
import utils.MyList;

public class MeinServiceImpl implements MeinService {

    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    private User activeUser;


    public MeinServiceImpl(BookRepository bookRepository, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    @Override
    public User registerUser(String email, String password) {
        return null;
    }

    @Override
    public boolean loginUser(String email, String password) {
        return false;
    }

    @Override
    public void logout() {

    }

    @Override
    public boolean takeBook(int id) {
        return false;
    }

    @Override
    public boolean returnBook(int id) {
        return false;
    }

    @Override
    public MyList<Book> getUserBooks() {
        return null;
    }

    @Override
    public User getUserWhoTakeBook(int Id) {
        return null;
    }

    @Override
    public void getAllBooks(MyList<Book> books) {

        if (books == null || books.size() == 0) {
            System.out.println("В библиотеке нет книг!");
            return;
        }

        for (int i = 0; i < books.size(); i++) {
            Book currentBook = books.get(i);
            System.out.println(currentBook.toString());
        }
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
    public MyList<Book> getUserBooks(int id) {
        return null;
    }

    @Override
    public MyList<Book> getAllBusyBooks() {
        return null;
    }

    @Override
    public Book addBook(String title, String author, String edition, int year) {
        return null;
    }

    @Override
    public MyList<Book> getAllFreeBooks() {
        return null;
    }

}

