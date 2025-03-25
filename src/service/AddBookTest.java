package service;

import model.Book;
import org.junit.jupiter.api.Test;
import repository.BookRepositoryImpl;
import repository.UserRepositoryImpl;

import static org.junit.jupiter.api.Assertions.*;

public class AddBookTest {

    @Test
    public void addBook() {

        BookRepositoryImpl bookRepository = new BookRepositoryImpl();
        UserRepositoryImpl userRepository = new UserRepositoryImpl();
        MeinServiceImpl service = new MeinServiceImpl(bookRepository, userRepository);

        String title = "Test book";
        String author = "Test author";
        String edition = "Test edition";
        int year = 1999;

        Book book = service.addBook(title, author, edition, year);

        assertNotNull(book);
        assertEquals(title, book.getTitle());
        assertEquals(author, book.getAuthor());
        assertEquals(edition, book.getEdition());
        assertEquals(year, book.getYear());
        assertEquals(5, bookRepository.getAllBooks().size());
    }
}