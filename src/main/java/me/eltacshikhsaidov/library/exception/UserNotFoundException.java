package me.eltacshikhsaidov.library.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id) {
        super("Could not found user " + id);
    }
}
