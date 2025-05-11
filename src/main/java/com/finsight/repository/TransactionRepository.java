package com.finsight.repository;

import com.finsight.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    List<Transaction> getAllByUserId(int userId);

    @Modifying
    @Transactional
    @Query("UPDATE Transaction t SET t.amount = :amount, t.dateTime = :dateTime, " +
            "t.transactionType = :transactionType, t.transactionStatus = :transactionStatus, " +
            "t.comment = :comment, t.account = :account, " +
            "t.counterparty = :counterparty, t.user = :user, " +
            "t.isUserSender = :isUserSender, t.categories = :categories " +
            "WHERE t.id = :id")


    void deleteById(int id);
}