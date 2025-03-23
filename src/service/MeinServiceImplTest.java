package service;

import model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import repository.BookRepository;
import repository.BookRepositoryImpl;
import repository.UserRepository;
import repository.UserRepositoryImpl;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class MeinServiceImplTest {

    private UserRepository repositoryU = new UserRepositoryImpl();
    private BookRepository repositoryB = new BookRepositoryImpl();
    private MeinService service = new MeinServiceImpl(repositoryB, repositoryU);

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
//    @ParameterizedTest
//    void testGetAllBooks() {
//        MyList<Book> result = service.getAllBooks();
//        Assertions.assertNotNull(result);
//        Assertions.assertEquals(4, result.size());
//    }


}