package service;

import model.Book;
import model.User;
import repository.BookRepository;
import repository.UserRepository;
import utils.MyArrayList;
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

        if (!UserValidation.isNameValid(name)) {
            System.out.println("Некорректное имя!");
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

        return userRepository.addUser(name, email, password);
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


    @Override
    public boolean takeBook(int bookId, int userId) {
        Book book = bookRepository.getById(bookId);
        User user = userRepository.getById(userId);

        if (book == null || user == null) {
            return false;
        }
        if (book.isBusy()) {
            System.out.println("Книга уже выдана!");
            return false;
        }

        book.setBusy(true);

        user.getUserBooks().add(book);
        return true;
    }


    @Override
    public boolean returnBook(int bookId, int userId) {
        Book book = bookRepository.getById(bookId);
        User user = userRepository.getById(userId);

        if (book == null || user == null) {
            return false;
        }

        if (!book.isBusy()) {
            System.out.println("Книга в библиотеке ");
            return false;
        }


        int index = -1;
        MyList<Book> usersBook = user.getUserBooks();
        for (int i = 0; i < user.getUserBooks().size(); i++) {
            if (usersBook.get(i).getId() == bookId) {
                index = i;
            }

        }
        if (index == -1) {
            System.out.println("У пользователя нет данной книги ");
            return false;
        }
        usersBook.remove(index);
        book.setBusy(false);
        return true;
    }

    @Override
    public MyList<Book> getUserBooks() {

        if (activeUser == null) return null;
        MyList<Book> listUserBooks = userRepository.getAllUserBooks(activeUser.getUserId());
        if (listUserBooks == null || listUserBooks.isEmpty()) return null;

        return listUserBooks;
    }

    @Override
    public User getUserWhoTakeBook(int id) {

        if (id < 1000) {
            System.out.println("Некорректный номер книги!");
            return null;
        }

        if (bookRepository.getById(id) == null) {
            System.out.println("Книги с таким номером нет в библиотеке!");
            return null;
        }

         if (!bookRepository.getById(id).isBusy()) {
             System.out.println("Книга в библиотеке!");
             return null;
         }

         for (User user : userRepository.getAllUsers()) {
             for (Book book : user.getUserBooks()) {
                 if (book.getId() == id) return user;
             }
         }
        System.out.println("Читатель не найден!");
        return null;
    }

    @Override
    public MyList<Book> getAllBooks() {

        MyList<Book> books = bookRepository.getAllBooks();
        if (books == null || books.isEmpty()) {
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


    @Override
    public MyList<Book> getAllBusyBooks() {
        MyList<Book> busyBooks = new MyArrayList<>();
        for (Book book : bookRepository.getAllBooks()) {
            if (book.isBusy()) {
                busyBooks.add(book);
            }
        }
        return busyBooks;   // Возвращаем список занятых книг
    }


    @Override
    public Book addBook(String title, String author, String edition, int year) {

       // проверка строк
        if (title == null || title.trim().isEmpty()) {
            System.out.println("Ошибка. Введите название книги");
            return null;
        }
        if (author == null || author.trim().isEmpty()){
            System.out.println("Ошибка. Введите имя автора");
            return null;
        }
        if (edition == null || edition.trim().isEmpty()) {
            System.out.println("Ошибка. Введите название издания");
            return  null;
        }
        if (year <= 1450 || year > java.time.Year.now().getValue()) {
            System.out.println("Ошибка. Некорректный год выпуска");
            return null;
        }

        return bookRepository.addBook(title, author, edition, year);
    }

    @Override
    public MyList<Book> getAllFreeBooks() {
        MyList<Book> freeBooks = new MyArrayList<>();
        for (Book book : bookRepository.getAllBooks()) {
            if (!book.isBusy()) {
                freeBooks.add(book);
            }
        }
        return freeBooks;
    }


    @Override
    public User getActiveUser() {
        return activeUser;
    }

    @Override
    public Book getBookById(int id) {
        if (id < 1) return null;
        return bookRepository.getById(id);
    }
}