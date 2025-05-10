package com.finsight.repository;

import com.finsight.entity.*;
import com.finsight.entity.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
    void updateTransaction(@Param("id") int id,
                           @Param("amount") int amount,
                           @Param("dateTime") Instant dateTime,
                           @Param("transactionType") TransactionType transactionType,
                           @Param("transactionStatus") TransactionStatus transactionStatus,
                           @Param("comment") String comment,
                           @Param("account") Account account,
                           @Param("counterparty") Counterparty counterparty,
                           @Param("user") User user,
                           @Param("isUserSender") boolean isUserSender,
                           @Param("categories") Categories categories);

    @Query("SELECT t.transactionType FROM Transaction t WHERE t.id = :id")
    TransactionType getTransactionTypeById(@Param("id") int id);

    @Query("SELECT t.transactionStatus FROM Transaction t WHERE t.id = :id")
    TransactionStatus getTransactionStatusById(@Param("id") int id);

    @Query("SELECT t.categories FROM Transaction t WHERE t.id = :id")
    Categories getCategoriesById(@Param("id") int id);

    void deleteById(int id);
}