package com.example.Library.domain.repository;

import com.example.Library.domain.BorrowRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowRepository extends JpaRepository<BorrowRecord, Long> {
}
