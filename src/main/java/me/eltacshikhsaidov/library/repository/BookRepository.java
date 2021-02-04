package me.eltacshikhsaidov.library.repository;

import me.eltacshikhsaidov.library.entity.Book;
import me.eltacshikhsaidov.library.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findByName(String name);

    List<Book> findByUser(User user);
}
