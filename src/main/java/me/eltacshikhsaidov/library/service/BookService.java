package me.eltacshikhsaidov.library.service;

import me.eltacshikhsaidov.library.entity.Book;

import java.util.List;

public interface BookService {

    List<Book> getAllBooks();
    Book getBookById(Long id);
    void deleteBookById(Long id);
    Book updateBookById(Book newBook, Long id);
    Book createOrSaveBook(Book book);
    String returnBook(Long book_id);

}
