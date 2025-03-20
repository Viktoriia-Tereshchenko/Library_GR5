package model;

import java.util.Objects;

public class Book {

    private final int id;
    private String title;
    private String author;
    private String edition; // издание
    private int year;
    private boolean isInHand; // true - книга выдана, по-умолчанию false

    public Book(int id, String title, String author, String edition, int year) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.edition = edition;
        this.year = year;
    }

    // TODO переделать Stringformat
    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", edition='" + edition + '\'' +
                ", year=" + year +
                ", isInHand=" + isInHand +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return id == book.id && year == book.year && isInHand == book.isInHand && Objects.equals(title, book.title) && Objects.equals(author, book.author) && Objects.equals(edition, book.edition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, author, edition, year, isInHand);
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getEdition() {
        return edition;
    }

    public int getYear() {
        return year;
    }

    public boolean isInHand() {
        return isInHand;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setInHand(boolean inHand) {
        isInHand = inHand;
    }
}
