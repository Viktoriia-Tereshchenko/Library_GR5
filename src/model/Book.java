package model;

import java.util.Objects;

public class Book {

    private final int id;
    private String title;
    private String author;
    private String edition; // издание
    private int year;
    private boolean isBusy; // true - книга выдана, по-умолчанию false

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
        return  String.format("Книга: Номер - %d Название - %s Автор - %s Издание - %s Год издания - %d В наличии - %s", id, title, author, edition, year, !isBusy);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return id == book.id && year == book.year && isBusy == book.isBusy && Objects.equals(title, book.title) && Objects.equals(author, book.author) && Objects.equals(edition, book.edition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, author, edition, year, isBusy);
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

    public boolean isBusy() {
        return isBusy;
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

    public void setBusy(boolean busy) {
        isBusy = busy;
    }
}
