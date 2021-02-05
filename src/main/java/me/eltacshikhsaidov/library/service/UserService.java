package me.eltacshikhsaidov.library.service;

import me.eltacshikhsaidov.library.entity.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();
    User getUserById(Long id);
    void deleteUserById(Long id);
    User updateUserById(User newUser, Long id);
    User createOrSaveUser(User user);
    String takeBook(Long user_id, Long book_id);

}
