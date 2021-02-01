package me.eltacshikhsaidov.library.repository;

import me.eltacshikhsaidov.library.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;

@Controller
public interface BookRepository extends JpaRepository<Book, Long> {
}
