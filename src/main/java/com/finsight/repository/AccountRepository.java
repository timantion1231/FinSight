package com.finsight.repository;

import com.finsight.entity.Account;
import com.finsight.entity.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    List<Account> findAll(); // Получение всех аккаунтов

    List<Account> findByUserId(int userId); // Получение аккаунтов по user_id
    Bank findBankById(int id); // Получение банка по ID
}