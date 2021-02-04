package me.eltacshikhsaidov.library.service.impl;

import me.eltacshikhsaidov.library.entity.Book;
import me.eltacshikhsaidov.library.entity.User;
import me.eltacshikhsaidov.library.exception.BookNotFoundException;
import me.eltacshikhsaidov.library.repository.BookRepository;
import me.eltacshikhsaidov.library.repository.UserRepository;
import me.eltacshikhsaidov.library.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public BookServiceImpl(BookRepository bookRepository, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
    }

    @Override
    public void deleteBookById(Long id) {
        bookRepository.findById(id).get().setUser(null);
        bookRepository.deleteById(id);
    }

    @Override
    public Book updateBookById(Book newBook, Long id) {
        return bookRepository.findById(id).map(book -> {
            book.setName(newBook.getName());
            book.setUser(newBook.getUser());

            book.setIsFree(newBook.getUser() == null);

            return bookRepository.save(book);
        }).orElseGet(() -> {
            newBook.setId(id);
            return bookRepository.save(newBook);
        });
    }

    @Override
    public Book createOrSaveBook(Book book) {

        Optional<Book> bookOptional = bookRepository.findByName(book.getName());
        if (bookOptional.isPresent()) {
            throw new IllegalStateException("Book name is already present");
        } else {
            if (book.getUser() == null) {
                book.setId(book.getId());
                book.setName(book.getName());
                book.setIsFree(true);
                book.setUser(null);
            } else {
                Optional<User> userOptional = userRepository.findByUsername(book.getUser().getUsername());

                book.setIsFree(false);
                if (userOptional.isPresent()) {
                    book.setUser(userOptional.get());
                } else {
                    throw new IllegalStateException("user not found");
                }
            }
        }

        return bookRepository.save(book);
    }
}
