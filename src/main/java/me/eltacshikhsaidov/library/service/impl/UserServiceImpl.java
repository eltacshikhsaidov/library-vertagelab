package me.eltacshikhsaidov.library.service.impl;

import me.eltacshikhsaidov.library.entity.Book;
import me.eltacshikhsaidov.library.entity.User;
import me.eltacshikhsaidov.library.exception.UserNotFoundException;
import me.eltacshikhsaidov.library.repository.BookRepository;
import me.eltacshikhsaidov.library.repository.UserRepository;
import me.eltacshikhsaidov.library.service.BookService;
import me.eltacshikhsaidov.library.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
}

