package me.eltacshikhsaidov.library.controller;

import me.eltacshikhsaidov.library.entity.User;
import me.eltacshikhsaidov.library.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users", produces = {MediaType.APPLICATION_JSON_VALUE})
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
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
        return userService.takeBook(user_id, book_id);
    }
}
