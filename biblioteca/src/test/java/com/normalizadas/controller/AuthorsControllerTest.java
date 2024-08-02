package com.normalizadas.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.normalizadas.model.AuthorDAO;


@ExtendWith(MockitoExtension.class)
public class AuthorsControllerTest {
    @Mock
    private AuthorDAO authorDAO;

    @InjectMocks
    private AuthorsController authorsController;


    @Test
    void testFindOrCreateAuthor() {

    }

    @Test
    void testGetAuthorsByBook() {

    }

    @Test
    void testUpdateAuthor() {

    }
}
