package service;

import model.Book;
import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import repository.BookRepository;
import repository.BookRepositoryImpl;
import repository.UserRepository;
import repository.UserRepositoryImpl;
import utils.MyList;

import static org.junit.jupiter.api.Assertions.*;

class ReturnBookTest {

    private BookRepository bookRepository;
    private UserRepository userRepository;
    private MeinService meinService;

    @BeforeEach
    void setUp() {

        bookRepository = new BookRepositoryImpl();
        userRepository = new UserRepositoryImpl();

        meinService = new MeinServiceImpl(bookRepository, userRepository);
    }

    @Test
    void testReturnBookWhenBookIsNotBusy() {
        // Создаем книгу с состоянием "не занята"
        Book book = new Book(1000, "Колобок", "Народная сказка", "ОАО Солнце", 1990); // книга уже в библиотеке
        bookRepository.addBook("Колобок", "Народная сказка", "ОАО Солнце", 1990);

        // Выполняем возврат книги, которая не занята
        boolean result = meinService.returnBook(1000, 1);

        assertFalse(result, "Книга должна быть в библиотеке, возврат невозможен");
    }

    @Test
    void testReturnBookWhenUserDoesNotHaveTheBook() {
        // Создаем книгу с состоянием "занята"
        Book book = new Book(1001, "Колобок", "Народная сказка", "ОАО Ранок", 1991); // книга занята
        bookRepository.addBook("Колобок", "Народная сказка", "ОАО Ранок", 1991);

        // Создаем пользователя, который не имеет книги с ID = 1001
        User user = new User(1, "Ivan", "ivan@gmail.com", "qwerty");
        userRepository.addUser( "Ivan", "ivan@gmail.com", "qwerty");

        // Пытаемся вернуть книгу, которой нет у пользователя
        boolean result = meinService.returnBook(1001, 1);

        // Проверяем, что метод вернет false, так как у пользователя нет данной книги
        assertFalse(result, "У пользователя нет этой книги, возврат невозможен");
    }
}