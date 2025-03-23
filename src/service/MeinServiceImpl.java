package service;

import model.Book;
import model.User;
import repository.BookRepository;
import repository.UserRepository;
import utils.MyList;
import utils.UserValidation;

import java.util.Objects;

public class MeinServiceImpl implements MeinService {

    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    private User activeUser;


    public MeinServiceImpl(BookRepository bookRepository, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    @Override
    public User registerUser(String name, String email, String password) {

        if (name == null || name.isEmpty()) {
            System.out.println("Укажите имя и фамилию!");
            return null;
        }

        if (!UserValidation.isEmailValid(email)) {
            System.out.println("Email не прошел проверку!");
            return null;
        }

        if (!UserValidation.isPasswordValid(password)) {
            System.out.println("Пароль не прошел проверку!");
            return null;
        }

        if (userRepository.isEmailExist(email)) {
            System.out.println("Пользователь с таким email уже существует!");
            return null;
        }

        User user = userRepository.addUser(name, email, password);
        return user;
    }

    @Override
    public boolean loginUser(String email, String password) {

        // TODO -раскомментировать перед демонстрацией
        //if (!UserValidation.isEmailValid(email)) return false;
        //if (!UserValidation.isPasswordValid(password)) return false;

        User user = userRepository.getUserByEmail(email);
        if (user == null) return false;
        if (Objects.equals(user.getPassword(), password)) {
            activeUser = user;
            return true;
        }
        return false;
    }

    @Override
    public void logout() {
        activeUser = null;
    }

    // TODO
    @Override
    public boolean takeBook(int id) {
        return false;
    }

    // TODO
    @Override
    public boolean returnBook(int id) {
        return false;
    }

    // TODO
    @Override
    public MyList<Book> getUserBooks() {
        return null;
    }

    @Override
    public User getUserWhoTakeBook(int Id) {
        return null;
    }

    @Override
    public MyList<Book> getAllBooks() {

        MyList<Book> books = bookRepository.getAllBooks();
        if (books == null || books.isEmpty()) {
            System.out.println("В библиотеке нет книг!");
            return null;
        }
        return bookRepository.getAllBooks();
    }

    @Override
    public MyList<Book> getByTitle(String title) {
        if (title == null || title.length() == 0) {
            return null;
        }

        return bookRepository.getByTitle(title);
    }


    @Override
    public MyList<Book> getByAuthor(String author) {
        if (author == null || author.length() == 0) {
            return null;
        }
        return bookRepository.getByAuthor(author);
    }

    // TODO
    @Override
    public MyList<Book> getUserBooks(int id) {
        return null;
    }

    // TODO
    @Override
    public MyList<Book> getAllBusyBooks() {
        return null;
    }

    @Override
    public Book addBook(String title, String author, String edition, int year) {
        return null;
    }

    // TODO
    @Override
    public MyList<Book> getAllFreeBooks() {
        return null;
    }

    @Override
    public User getActiveUser() {
        return activeUser;
    }
}

