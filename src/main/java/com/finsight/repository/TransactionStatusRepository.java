package com.finsight.repository;

import com.finsight.entity.TransactionStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TransactionStatusRepository extends JpaRepository<TransactionStatus, Integer> {
    Optional<TransactionStatus> findById(int id); // Получение статуса транзакции по ID
}
