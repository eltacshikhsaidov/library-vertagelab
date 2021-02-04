package me.eltacshikhsaidov.library.controller;

import me.eltacshikhsaidov.library.entity.Book;
import me.eltacshikhsaidov.library.entity.User;
import me.eltacshikhsaidov.library.service.BookService;
import me.eltacshikhsaidov.library.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users", produces = {MediaType.APPLICATION_JSON_VALUE})
public class UserController {

    private final UserService userService;
    private final BookService bookService;

    public UserController(UserService userService, BookService bookService) {
        this.userService = userService;
        this.bookService = bookService;
    }

    @GetMapping("/all")
    public List<User> allUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/all")
    public User createOrSave(@RequestBody User user) {
        return userService.createOrSaveUser(user);
    }

    @GetMapping("/user/{id}")
    public User getById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @DeleteMapping("/user/{id}")
    public void delete(@PathVariable Long id) {
        userService.deleteUserById(id);
    }

    @PutMapping("/user/{id}")
    public User updateUser(@RequestBody User user, @PathVariable Long id) {
        return userService.updateUserById(user, id);
    }

    @PutMapping("/user/take/{user_id}")
    public String takeBook(@PathVariable Long user_id,
                           @RequestParam(name = "book_id", defaultValue = "") Long book_id) {
        User user = userService.getUserById(user_id);
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
