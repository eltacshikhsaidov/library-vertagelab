package me.eltacshikhsaidov.library.repository;

import me.eltacshikhsaidov.library.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findByName(String name);

    List<Book> findByUser(User user);

}
