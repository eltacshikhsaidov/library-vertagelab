package me.eltacshikhsaidov.library.service.impl;

import me.eltacshikhsaidov.library.entity.User;
import me.eltacshikhsaidov.library.repository.UserRepository;
import me.eltacshikhsaidov.library.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
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
        return userRepository.save(user);
    }
}
