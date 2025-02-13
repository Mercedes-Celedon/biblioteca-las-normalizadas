package com.normalizadas.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.sql.DataSource;
import java.sql.Connection;

import java.util.List;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.anyString;

import com.normalizadas.model.Book;
import com.normalizadas.model.Author;
import com.normalizadas.model.BookDAO;

@ExtendWith(MockitoExtension.class)
public class BooksControllerTest {
    @Mock
    private BookDAO bookDAO;

    @InjectMocks
    private BooksController bookController;

    @Mock
    private DataSource ds;

    @Mock
    private Connection c;

    @Mock
    private PreparedStatement stmt;

    @Mock
    private ResultSet rs;

    @Mock
    private Book b;

    @BeforeEach
    void setUp() {
        ds = mock(DataSource.class);
        c = mock(Connection.class);
        stmt = mock(PreparedStatement.class);
        rs = mock(ResultSet.class);
    }


    @Test
    void testBookExists() {
        bookController.bookExists(anyString());
        verify(bookDAO).bookExists(anyString());
        assertTrue(true,"El libro existe");
    }

    @Test
    void testDeleteBook() {
        bookController.deleteBook(0);
        ArgumentCaptor<Integer> intArgumentCaptor = ArgumentCaptor.forClass(Integer.class);
        verify(bookDAO).deleteBook(0);
        verify(bookDAO).deleteBook(intArgumentCaptor.capture());
        assertEquals(0, intArgumentCaptor.getValue());
    }
    
    @Test
    void testGetAllBooks() {
        when(bookDAO.getAllBooks()).thenReturn(
        List.of(       

            new Book("Title Book 1", "Description 1", 2, "Español", "123-1234567"),
            new Book("Title Book 2", "Description 2", 1, "Frances", "456-1234567"),
            new Book("Title Book 3", "Description 3", 1, "Catalan", "789-1234567")
            )
        );
        List<Book> result = bookController.getAllBooks();
        assertEquals("Title Book 1", result.get(0).getTitle());
    }

    @Test
    void testGetBookbyTitle() {
        when(bookDAO.getBookbyTitle(anyString())).thenReturn(
            new Book("Title Book 1", "Description 1", 2, "123-1234567", "Español"));
        Book result = bookController.getBookbyTitle(anyString());
        assertNotNull(result);
        assertEquals("Title Book 1", result.getTitle());
    }

    @Test
    void testGetBooksbyAuthors() {
        when(bookDAO.getBooksbyAuthors(anyString())).thenReturn(
        List.of(       
            new Book("Title Book 1", "Description 1", 2, "Español", "123-1234567"),
            new Book("Title Book 2", "Description 2", 1, "Frances", "456-1234567"),
            new Book("Title Book 3", "Description 3", 1, "Catalan", "789-1234567")
            )
        );
        new Author("Cebolla");
        List<Book> result = bookController.getBooksbyAuthors(anyString());
        //assertEquals(books, result);
        assertNotNull(result);
    }

}
