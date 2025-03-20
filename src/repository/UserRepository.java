package repository;

import model.User;

public interface UserRepository {

    // create
    User addUser(String email, String password);

    // read
    boolean isEmailExist(String email);

    User getUserByEmail(String email);

    // update
    boolean updatePassword(String email, String newPassword);
    //boolean updatePassword(int id, String newPassword);

    // delete
    boolean deleteUser(int id);

}
