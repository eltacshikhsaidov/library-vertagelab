package me.eltacshikhsaidov.library.service.impl;

import me.eltacshikhsaidov.library.entity.Book;
import me.eltacshikhsaidov.library.entity.User;
import me.eltacshikhsaidov.library.repository.BookRepository;
import me.eltacshikhsaidov.library.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBookById(Long id) {
        return bookRepository.findById(id).get();
    }

    @Override
    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public Book updateBookById(Book newBook, User user, Long id) {
        return bookRepository.findById(id).map(book -> {
            book.setName(newBook.getName());
            book.setIsFree(newBook.getIsFree());
            book.setUser(newBook.getUser());
            return bookRepository.save(book);
        }).orElseGet(() -> {
            newBook.setId(id);
            return bookRepository.save(newBook);
        });
    }

    @Override
    public Book createOrSaveBook(Book book) {
        return bookRepository.save(book);
    }
}
