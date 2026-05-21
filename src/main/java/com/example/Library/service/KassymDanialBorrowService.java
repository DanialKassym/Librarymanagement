package com.example.Library.service;

import com.example.Library.domain.Book;
import com.example.Library.domain.BorrowRecord;
import com.example.Library.domain.User;
import com.example.Library.domain.repository.BookRepository;
import com.example.Library.domain.repository.BorrowRepository;
import com.example.Library.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KassymDanialBorrowService {

    private final BorrowRepository borrowRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public BorrowRecord borrowBook(Long userId, Long bookId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        if (book.getQuantity() <= 0) {
            throw new RuntimeException("Book is not available");
        }

        book.setQuantity(book.getQuantity() - 1);
        bookRepository.save(book);

        BorrowRecord record = BorrowRecord.builder()
                .user(user)
                .book(book)
                .build();

        return borrowRepository.save(record);
    }
}
