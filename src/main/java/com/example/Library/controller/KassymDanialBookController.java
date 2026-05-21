package com.example.Library.controller;

import com.example.Library.controller.dto.BookRequestDto;
import com.example.Library.controller.dto.BookResponseDto;
import com.example.Library.domain.Book;
import com.example.Library.domain.mapper.BookMapper;
import com.example.Library.service.KassymDanialBookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class KassymDanialBookController {

    private final KassymDanialBookService bookService;
    private final BookMapper bookMapper;

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public BookResponseDto getBookById(@PathVariable Long id) {
        return bookMapper.toDto(bookService.getBookById(id));
    }

    @PostMapping
    public BookResponseDto createBook(@Valid @RequestBody BookRequestDto dto) {

        Book book = bookMapper.toEntity(dto);

        return bookMapper.toDto(bookService.createBook(book));
    }

    @PutMapping("/{id}")
    public Book updateBook(
            @PathVariable Long id,
            @RequestBody Book book
    ) {
        return bookService.updateBook(id, book);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }

    @GetMapping("/search")
    public Page<BookResponseDto> searchBooks(
            @RequestParam(defaultValue = "") String title,
            @PageableDefault(sort = "title") Pageable pageable
    ) {

        return bookService.searchBooks(title, pageable)
                .map(bookMapper::toDto);
    }
}