package me.eltacshikhsaidov.library.service.impl;

import me.eltacshikhsaidov.library.entity.*;
import me.eltacshikhsaidov.library.exception.UserNotFoundException;
import me.eltacshikhsaidov.library.repository.*;
import me.eltacshikhsaidov.library.service.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    private final BookService bookService;


    public UserServiceImpl(UserRepository userRepository, BookRepository bookRepository, BookService bookService) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.bookService = bookService;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public void deleteUserById(Long id) {

        List<Book> books = bookRepository.findByUser(
                userRepository.findById(id).get()
        );

        for (Book book: books) {
            book.setId(book.getId());
            book.setName(book.getName());
            book.setIsFree(true);
            book.setUser(null);
            bookService.updateBookById(book, book.getId());
        }
        userRepository.deleteById(id);
        System.out.println("Deleted");
    }

    @Override
    public User updateUserById(User newUser, Long id) {
        return userRepository.findById(id).map(user -> {
            user.setUsername(newUser.getUsername());
            user.setPassword(newUser.getPassword());
            return userRepository.save(user);
        }).orElseGet(() -> {
            newUser.setId(id);
            return userRepository.save(newUser);
        });
    }

    @Override
    public User createOrSaveUser(User user) {

        Optional<User> userOptional = userRepository.findByUsername(user.getUsername());
        if (userOptional.isPresent()) {
            throw new IllegalStateException("Username is taken");
        }

        return userRepository.save(user);
    }

    @Override
    public String takeBook(Long user_id, Long book_id) {
        User user = getUserById(user_id);
        Book book = bookService.getBookById(book_id);

        if (user != null) {
            if (book != null) {
                if (book.getUser() == null) {
                    book.setUser(user);
                    bookService.updateBookById(book, book_id);
                } else {
                    throw new IllegalStateException("book with id=" + book_id +
                            " is already taken by another user");
                }
            } else {
                throw new IllegalStateException("book with id=" + book_id + " not found");
            }
        } else {
            throw new IllegalStateException("user with id=" + user_id + " not found");
        }

        return "book with id=" + book_id + " is taken by user with id=" + user_id;
    }
}