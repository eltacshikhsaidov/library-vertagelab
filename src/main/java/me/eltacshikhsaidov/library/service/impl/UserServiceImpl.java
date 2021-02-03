package me.eltacshikhsaidov.library.service.impl;

import me.eltacshikhsaidov.library.entity.User;
import me.eltacshikhsaidov.library.exception.UserNotFoundException;
import me.eltacshikhsaidov.library.repository.UserRepository;
import me.eltacshikhsaidov.library.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
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

        Optional<User> userOptional = userRepository.findByUsername(user.getUsername());
        if (userOptional.isPresent()) {
            throw new IllegalStateException("Username is taken");
        }

        return userRepository.save(user);
    }
}
