package com.finsight.repository;

import com.finsight.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer> {

    List<Account> findByUserId(int userId); // Получение аккаунтов по user_id

    Optional<Account> findById(int id); // Получение аккаунта по ID

    void deleteById(int id); // Удаление аккаунта по ID

}