package model;

import utils.MyArrayList;
import utils.MyList;

import java.util.Objects;

public class User {

    private final int userId;

    private String email;
    private String password;

    private Role role;
    private MyList<Book> userBooks;

    public User(int userId, String email, String password) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.role = Role.READER;
        this.userBooks = new MyArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return userId == user.userId && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(role, user.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, email, password, role);
    }

    public int getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Role getRole() {
        return role;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public MyList<Book> getUserBooks() {
        return userBooks;
    }


    // TODO переделать Stringformat
    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}
