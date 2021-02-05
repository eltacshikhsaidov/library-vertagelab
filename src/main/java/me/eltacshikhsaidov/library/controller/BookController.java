package me.eltacshikhsaidov.library.controller;

import me.eltacshikhsaidov.library.entity.Book;
import me.eltacshikhsaidov.library.service.BookService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/books", produces = {MediaType.APPLICATION_JSON_VALUE})
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/all")
    public List<Book> allBooks() {
        return bookService.getAllBooks();
    }

    @PostMapping("/all")
    public Book createOrSave(@RequestBody Book book) {
        return bookService.createOrSaveBook(book);
    }

    @GetMapping("/book/{id}")
    public Book getById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    @DeleteMapping("/book/{id}")
    public void delete(@PathVariable Long id) {
        bookService.deleteBookById(id);
    }

    @PutMapping("/book/{id}")
    public Book updateBook(@RequestBody Book book, @PathVariable Long id) {
        return bookService.updateBookById(book, id);
    }

    @PutMapping("/book/return/{book_id}")
    public String returnBook(@PathVariable Long book_id) {
        return bookService.returnBook(book_id);
    }
}
