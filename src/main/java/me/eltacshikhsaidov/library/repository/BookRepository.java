package me.eltacshikhsaidov.library.repository;

import me.eltacshikhsaidov.library.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findByName(String name);

}
