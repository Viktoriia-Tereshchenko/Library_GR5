package repository;

import model.Book;
import model.User;
import utils.MyList;

public interface UserRepository {

    // create
    User addUser(String name, String email, String password);

    // read
    boolean isEmailExist(String email);

    User getUserByEmail(String email);

    // Cписок всех книг, находящихся сейчас у конкретного читателя
    MyList<Book> getAllUserBooks(int id);

    // update
    boolean updatePassword(String name, String email, String newPassword);
    //boolean updatePassword(int id, String newPassword);

    // delete
    boolean deleteUser(int id);

    User getById(int userId);

    MyList<User> getAllUsers();
}
