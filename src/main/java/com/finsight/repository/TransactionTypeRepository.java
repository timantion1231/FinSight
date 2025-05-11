package com.finsight.repository;

import com.finsight.entity.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TransactionTypeRepository extends JpaRepository<TransactionType, Integer> {

    Optional<TransactionType> findById(int id); // Получение типа транзакции по ID

}
