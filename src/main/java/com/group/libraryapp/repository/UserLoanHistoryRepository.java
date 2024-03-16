package com.group.libraryapp.repository;

import com.group.libraryapp.domain.UserLoanHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserLoanHistoryRepository extends JpaRepository<UserLoanHistory, Long> {

    boolean existsByBookNameAndIsReturned(String bookName, boolean isReturned);

    Optional<UserLoanHistory> findByUserIdAndBookName(Long userId, String bookName);

}

