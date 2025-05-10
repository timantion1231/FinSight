package com.finsight.repository;

import com.finsight.entity.Account;
import com.finsight.entity.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    //List<Account> findAll(); // Получение всех аккаунтов

    List<Account> findByUserId(int userId); // Получение аккаунтов по user_id
    @Query("SELECT b FROM Bank b WHERE b.id = :id")
    Optional<Bank> findBankById(@Param("id") int id); // Получение банка по ID
    Optional<Account> findById(int id); // Получение аккаунта по ID
    void deleteById(int id); // Удаление аккаунта по ID

}