package service;

import model.Book;
import model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import repository.BookRepository;
import repository.BookRepositoryImpl;
import repository.UserRepository;
import repository.UserRepositoryImpl;
import utils.MyArrayList;
import utils.MyList;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class MeinServiceImplTest {

    private final UserRepository repositoryU = new UserRepositoryImpl();
    private final BookRepository repositoryB = new BookRepositoryImpl();
    private final MeinService service = new MeinServiceImpl(repositoryB, repositoryU);

    User user;

    @BeforeEach
    void start() {
        repositoryU.addUser("Иван", "ivan@test.com", "qwerty1Q%S");
    }

    // тест public User registerUser(String name, String email, String password)
    @ParameterizedTest
    @MethodSource("testDataArguments1")
    void testRegisterUserValidNameEmailPassword(String name, String email, String password) {
        user = service.registerUser(name, email, password);
        Assertions.assertEquals(name, user.getName());
        Assertions.assertEquals(email, user.getEmail());
        Assertions.assertEquals(password, user.getPassword());
        Assertions.assertNotNull(user);
    }

    static Stream<Arguments> testDataArguments1() {
        return Stream.of(
                Arguments.of("Ольга", "olga@test.com", "olgA-12345"),
                Arguments.of("Tom", "Upiter@test.de", "UP&23541qw")
        );
    }

    @ParameterizedTest
    @MethodSource("testDataArguments2")
    void testRegisterInvalidName(String name, String email, String password) {
        user = service.registerUser(name, email, password);
        Assertions.assertNull(user);
    }

    static Stream<Arguments> testDataArguments2() {
        return Stream.of(
                Arguments.of("Tom  34", "@test.de", "UP"),
                Arguments.of("798", "@test.de", "yucvcv12#v$"),
                Arguments.of("Ольга", " ", "345"),
                Arguments.of("Ольга", "rt4@df.r", "345"),
                Arguments.of("Hanna", "hanna@gmail.com", "345"),
                Arguments.of("Hanna", "hanna@gmail.com", "QWWEZ12"),
                Arguments.of(null, null, null)
        );
    }

    // тест boolean loginUser(String email, String password)
    @ParameterizedTest
    @CsvSource({"'ivan@test.com', 'qwerty1Q%S'"})
    void testLoginUserValidEmailValidPassword(String email, String password) {
        Assertions.assertTrue(service.loginUser(email, password));
        Assertions.assertTrue(repositoryU.isEmailExist(email));
        Assertions.assertEquals(repositoryU.getUserByEmail(email).getEmail(), email);
        Assertions.assertEquals(repositoryU.getUserByEmail(email).getPassword(), password);
    }

    @ParameterizedTest
    @MethodSource("testDataArguments3")
    void testLoginUserValidEmailInvalidPassword(String email, String password) {
        Assertions.assertFalse(service.loginUser(email, password));
        Assertions.assertEquals(repositoryU.getUserByEmail(email).getEmail(), email);
        Assertions.assertNotEquals(repositoryU.getUserByEmail(email).getPassword(), password);
    }

    static Stream<Arguments> testDataArguments3() {
        return Stream.of(
                Arguments.of("ivan@test.com", "qwerty"),
                Arguments.of("ivan@test.com", "5987151"),
                Arguments.of("ivan@test.com", "qw89"),
                Arguments.of("ivan@test.com", "tr 56 "),
                Arguments.of("ivan@test.com", ""),
                Arguments.of("ivan@test.com", null)
        );
    }

    @ParameterizedTest
    @CsvSource({"'i@test.com', 'qwerty1Q%S'", "'weer@test.com', 'qwerty1Q%S'", "'myemail@test.com', 'qwerty1Q%S'"})
    void testLoginUserInvalidEmailValidPassword(String email, String password) {
        Assertions.assertFalse(service.loginUser(email, password));
        Assertions.assertFalse(repositoryU.isEmailExist(email));
    }

    // тест public MyList<Book> getAllBooks()
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void testGetAllBooksSize(int size) {
        Assertions.assertNotNull(service.getAllBooks());
        Assertions.assertNotEquals(size, service.getAllBooks().size());
        Assertions.assertEquals(4, service.getAllBooks().size());
    }

    // тест public MyList<Book> getUserBooks()
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void testGetUserBooksEmptyList(int id) {
        Assertions.assertTrue(service.loginUser(repositoryU.getById(id).getEmail(), repositoryU.getById(id).getPassword()));
        Assertions.assertNotNull(service.getActiveUser());
        Assertions.assertEquals(id, service.getActiveUser().getUserId());
        Assertions.assertNull(service.getUserBooks());
    }

    @ParameterizedTest
    @ValueSource(ints = {5, 7})
    void testGetUserBooksIsNull(int id) {
        Assertions.assertNull(repositoryU.getById(id));
        Assertions.assertNull(service.getActiveUser());
        Assertions.assertNull(service.getUserBooks());
    }

    // тест  public User getActiveUser()
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4})
    void testGetActiveUserNotNull(int id) {
        Assertions.assertTrue(service.loginUser(repositoryU.getById(id).getEmail(), repositoryU.getById(id).getPassword()));
        Assertions.assertNotNull(service.getActiveUser());
        Assertions.assertEquals(id, service.getActiveUser().getUserId());
    }

    @ParameterizedTest
    @ValueSource(ints = {7, 8})
    void testGetActiveUserIsNull(int id) {
        Assertions.assertNull(repositoryU.getById(id));
        Assertions.assertNull(service.getActiveUser());
    }

    // тест public MyList<Book> getAllFreeBooks()
    @ParameterizedTest
    @ValueSource(ints = {1, 2})
    void getGetAllFreeBooksNotNull(int size) {
        Assertions.assertNotNull(service.getAllFreeBooks());
        Assertions.assertNotEquals(size, service.getAllFreeBooks().size());
    }

    @ParameterizedTest
    @ValueSource(ints = {4})
    void getGetAllFreeBooksStartSize(int size) {
        Assertions.assertNotNull(service.getAllFreeBooks());
        Assertions.assertEquals(size, service.getAllFreeBooks().size());
    }

    // тест public MyList<Book> getAllBusyBooks()
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 5})
    void getGetAllFreeBooksIsEmpty(int size) {
        Assertions.assertEquals(0, service.getAllBusyBooks().size());
        Assertions.assertNotEquals(size, service.getAllBusyBooks().size());
        Assertions.assertNotNull(service.getAllBusyBooks());
    }

    // тест public MyList<Book> getByTitle(String title)
    @ParameterizedTest
    @ValueSource(strings = {"кол", "анна", "АФ", "Кри"})
    void testGetByTitleNotNull(String title) {
        Assertions.assertNotNull(service.getByTitle(title));
        Assertions.assertNotEquals(0, service.getByTitle(title).size());
    }

    // тест public MyList<Book> getByTitle(String title)
    @ParameterizedTest
    @ValueSource(strings = {"JJJ", "ВВВ", "ККЮЧ"})
    void testGetByTitleIsEmpty(String title) {
        Assertions.assertNotNull(service.getByTitle(title));
        Assertions.assertEquals(0, service.getByTitle(title).size());
    }

    // тест public MyList<Book> getByAuthor(String author)
    @ParameterizedTest
    @ValueSource(strings = {"тол", "наРОД", "дю", "юм"})
    void testGetByAuthorNotNull(String author) {
        Assertions.assertNotNull(service.getByAuthor(author));
        Assertions.assertNotEquals(0, service.getByAuthor(author).size());
    }

    // тест public MyList<Book> getByTitle(String title)
    @ParameterizedTest
    @ValueSource(strings = {"JJJ", "ВВВ", "ККЮЧ"})
    void testGetByAuthorIsEmpty(String author) {
        Assertions.assertNotNull(service.getByAuthor(author));
        Assertions.assertEquals(0, service.getByAuthor(author).size());
    }

    // тест public void logout()
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4})
    void testlogoutCorrect(int id) {
        Assertions.assertTrue(service.loginUser(repositoryU.getById(id).getEmail(), repositoryU.getById(id).getPassword()));
        Assertions.assertNotNull(service.getActiveUser());
        //System.out.println(service.getActiveUser());
        Assertions.assertEquals(id, service.getActiveUser().getUserId());
        service.logout();
        Assertions.assertNull(service.getActiveUser());
        //System.out.println(service.getActiveUser());
    }

    // тест public boolean takeBook(int bookId, int userId)
    @ParameterizedTest
    @MethodSource("testDataArguments4")
    void testTakeBookTrue(int bookId, int userId) {
        Assertions.assertTrue(service.loginUser(repositoryU.getById(userId).getEmail(), repositoryU.getById(userId).getPassword()));
        Assertions.assertTrue(service.takeBook(bookId, userId));
        Assertions.assertNotEquals(0, service.getUserBooks().size());
        Assertions.assertEquals(1, service.getUserBooks().size());
    }

    static Stream<Arguments> testDataArguments4() {
        return Stream.of(
                Arguments.of(1000, 2),
                Arguments.of(1001, 2),
                Arguments.of(1002, 3),
                Arguments.of(1003, 3)
        );
    }

    @ParameterizedTest
    @MethodSource("testDataArguments5")
    void testTakeBookFalse(int bookId, int userId) {
        Assertions.assertNull(repositoryU.getById(userId));
        Assertions.assertNull(repositoryB.getById(bookId));
        Assertions.assertFalse(service.takeBook(bookId, userId));
    }

    static Stream<Arguments> testDataArguments5() {
        return Stream.of(
                Arguments.of(10, 15),
                Arguments.of(11, 17),
                Arguments.of(12, 8),
                Arguments.of(13, 10),
                Arguments.of(0, 0),
                Arguments.of(-5, -5)
        );
    }

     // тест public Book getBookById(int id)
    @ParameterizedTest
    @ValueSource(ints = {1001, 1002, 1003})
    void testGetBookByIdNotNull(int id) {
        Assertions.assertNotNull(service.getBookById(id));
        Assertions.assertEquals(id, service.getBookById(id).getId());
    }

    @ParameterizedTest
    @ValueSource(ints = {1020, 1023})
    void testGetBookByIdIsNull(int id) {
        Assertions.assertNull(service.getBookById(id));
    }
}