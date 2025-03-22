package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    Book book;

        @BeforeEach
        public void setUp() {
            book = new Book(1000, "Колобок", "Народная сказка", "Издательство: Ранок", 1991);
        }


        @ParameterizedTest
        @ValueSource(strings = {"Колобок", "Три Мушкетера", "Колобок"})
        public void testBookTitleSet(String validTitle) {

            book.setTitle(validTitle);


            Assertions.assertEquals(validTitle, book.getTitle());
        }

}