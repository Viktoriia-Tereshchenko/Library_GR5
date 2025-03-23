package service;

import model.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.BookRepository;
import repository.BookRepositoryImpl;
import utils.MyList;

import static org.junit.jupiter.api.Assertions.*;

class GetByAuthorTest {

    private BookRepository bookRepository;

    @BeforeEach
    void setUp() {
        bookRepository = new BookRepositoryImpl();

    }

    @Test
    void addStartBooks_ShouldAddCorrectBooks() {
        MyList<Book> books = bookRepository.getAllBooks();

        assertNotNull(books);



    }

}





