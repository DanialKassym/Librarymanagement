package com.example.Library.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class KassymDanialAsyncService {

    @Async
    public CompletableFuture<Void> sendWelcomeMessage(String username) {

        log.info("Sending welcome message to {}", username);

        return CompletableFuture.completedFuture(null);
    }

    @Async
    public CompletableFuture<Void> saveBorrowLog(String bookTitle) {

        log.info("Saving borrow log for {}", bookTitle);

        return CompletableFuture.completedFuture(null);
    }
}
