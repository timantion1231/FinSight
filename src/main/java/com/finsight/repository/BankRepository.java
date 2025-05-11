package com.finsight.repository;

import com.finsight.entity.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BankRepository extends JpaRepository<Bank, Integer> {
    Optional<Bank> findById(int id); // Получение банка по ID

}
