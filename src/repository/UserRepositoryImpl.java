package repository;

import model.Book;
import model.Role;
import model.User;
import utils.MyArrayList;
import utils.MyList;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class UserRepositoryImpl implements UserRepository {

    // список пользователей при запуске программы
    private final MyList<User> users;

    private final AtomicInteger currentId = new AtomicInteger(1);

    public UserRepositoryImpl() {
        users = new MyArrayList<>();
        addUsers();
    }

    private void addUsers() {
        User admin = new User(currentId.getAndIncrement(), "ADMIN Oleg", "1", "1");
        admin.setRole(Role.ADMIN);

        User user = new User(currentId.getAndIncrement(), "User Ivan", "2", "2");
        user.setRole(Role.READER);

        users.addAll(admin, user);
    }

    @Override
    public User addUser(String name, String email, String password) {
        User user = new User(currentId.getAndIncrement(), name, email, password);
        users.add(user);
        return user;
    }

    @Override
    public boolean isEmailExist(String email) {
        for (User user : users) {
            if (Objects.equals(user.getEmail(), email)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public User getUserByEmail(String email) {
        for (User user : users) {
            if (Objects.equals(user.getEmail(), email)) {
                return user;
            }
        }
        return null;
    }


    // TODO
    @Override
    public MyList<Book> getAllUserBooks(int id) {
        return null;
    }

    // TODO
    @Override
    public boolean updatePassword(String name, String email, String newPassword) {
        return false;
    }

    // TODO
    @Override
    public boolean deleteUser(int id) {
        return false;
    }

    @Override
    public User getById(int userId) {
        for (User user : users) {
            if (user.getUserId() == userId) {
                return user;
            }
        }
        return null;
    }
}
