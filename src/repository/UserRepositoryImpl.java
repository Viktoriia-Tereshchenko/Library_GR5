package repository;

import model.Role;
import model.User;
import utils.MyArrayList;
import utils.MyList;

import java.util.concurrent.atomic.AtomicInteger;

public class UserRepositoryImpl implements UserRepository {

    // список пользователей при запуске программы
    private  final MyList<User> users;

    private final AtomicInteger currentId = new AtomicInteger(1);

    public UserRepositoryImpl() {
        users = new MyArrayList<>();
        addUsers();
    }

    private  void  addUsers() {
        User admin = new User(currentId.getAndIncrement(), "ADMIN Oleg","1", "1");
        admin.setRole(Role.ADMIN);

        User user = new User(currentId.getAndIncrement(), "User Ivan","2", "2");
        admin.setRole(Role.READER);

        users.addAll(admin, user);
    }

    @Override
    public User addUser(String name, String email, String password) {
        return null;
    }

    @Override
    public boolean isEmailExist(String email) {
        return false;
    }

    @Override
    public User getUserByEmail(String email) {
        return null;
    }

    @Override
    public boolean updatePassword(String name, String email, String newPassword) {
        return false;
    }


    @Override
    public boolean deleteUser(int id) {
        return false;
    }
}
