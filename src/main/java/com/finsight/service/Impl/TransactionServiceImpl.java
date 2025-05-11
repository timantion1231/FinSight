package com.finsight.service.Impl;

import com.finsight.DTO.request.EditTransactionDTO;
import com.finsight.DTO.request.NewTransactionDTO;
import com.finsight.DTO.response.FullTransactionDTO;
import com.finsight.entity.Transaction;
import com.finsight.repository.*;
import com.finsight.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final CounterpartyRepository counterpartyRepository;
    private final UserRepository userRepository;
    private final TransactionTypeRepository transactionTypeRepository;
    private final TransactionStatusRepository transactionStatusRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository,
                                  AccountRepository accountRepository,
                                  CounterpartyRepository counterpartyRepository,
                                  UserRepository userRepository,
                                  TransactionTypeRepository transactionTypeRepository,
                                  TransactionStatusRepository transactionStatusRepository,
                                  CategoryRepository categoryRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
        this.counterpartyRepository = counterpartyRepository;
        this.userRepository = userRepository;
        this.transactionTypeRepository = transactionTypeRepository;
        this.transactionStatusRepository = transactionStatusRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public ArrayList<FullTransactionDTO> getAllTransactions(int userId) {
        ArrayList<Transaction> transactions = (ArrayList<Transaction>) transactionRepository.getAllByUserId(userId);
        ArrayList<FullTransactionDTO> transactionDTOs = new ArrayList<>();
        for (Transaction transaction : transactions) {
            transactionDTOs.add(mapTransactionToDTO(transaction));
        }
        return transactionDTOs;
    }

    @Override
    public FullTransactionDTO getTransaction(int id) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Transaction not found with id: " + id));
        return mapTransactionToDTO(transaction);
    }

    private FullTransactionDTO mapTransactionToDTO(Transaction transaction) {
        FullTransactionDTO dto = new FullTransactionDTO();
        dto.setId(transaction.getId());
        dto.setAmount(transaction.getAmount());
        dto.setDateTime(transaction.getDateTime());
        dto.setTransactionTypeId(transaction.getTransactionType().getId());
        dto.setTransactionStatusId(transaction.getTransactionStatus().getId());
        dto.setComment(transaction.getComment());
        dto.setUserId(transaction.getUser().getId());
        dto.setCounterpartyId(transaction.getCounterparty().getId());
        dto.setUserIsSender(transaction.isUserSender());
        dto.setCategoryId(transaction.getCategories().getId());
        dto.setAccountId(transaction.getAccount().getId());
        return dto;
    }

    @Override
    public FullTransactionDTO editTransaction(int id, EditTransactionDTO transaction) {
        FullTransactionDTO dto = new FullTransactionDTO();
        Transaction existingTransaction = transactionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Transaction not found with id: " + id));
        existingTransaction.setDateTime(transaction.getDateTime());
        existingTransaction.setTransactionType(transactionTypeRepository.findById(
                transaction.getTransactionTypeId()).orElseThrow(() -> new IllegalArgumentException(
                "Transaction type not found with id: " + transaction.getTransactionTypeId())));
        existingTransaction.setTransactionStatus(transactionStatusRepository.findById(
                transaction.getTransactionStatusId()).orElseThrow(() -> new IllegalArgumentException(
                "Transaction status not found with id: " + transaction.getTransactionStatusId())));
        existingTransaction.setAmount(transaction.getAmount());
        existingTransaction.setComment(transaction.getComment());
        existingTransaction.setAccount(accountRepository.findById(
                transaction.getAccountId()).orElseThrow(() -> new IllegalArgumentException(
                "Account not found with id: " + transaction.getAccountId())));
        existingTransaction.setCounterparty(counterpartyRepository.findById(
                transaction.getCounterpartyId()).orElseThrow(() -> new IllegalArgumentException(
                "Counterparty not found with id: " + transaction.getCounterpartyId())));
        existingTransaction.setUserSender(transaction.isUserIsSender());
        existingTransaction.setCategories(categoryRepository.findById(
                transaction.getCategoryId()).orElseThrow(() -> new IllegalArgumentException(
                "Category not found with id: " + transaction.getCategoryId())));
        transactionRepository.save(existingTransaction);
        return mapTransactionToDTO(existingTransaction);
    }

    @Override
    public FullTransactionDTO createTransaction(NewTransactionDTO transaction) {
        Transaction newTransaction = new Transaction();
        newTransaction.setDateTime(transaction.getDateTime());
        newTransaction.setTransactionType(transactionTypeRepository.findById(
                transaction.getTransactionTypeId()).orElseThrow(() -> new IllegalArgumentException(
                "Transaction type not found with id: " + transaction.getTransactionTypeId())));
        newTransaction.setTransactionStatus(transactionStatusRepository.findById(
                transaction.getTransactionStatusId()).orElseThrow(() -> new IllegalArgumentException(
                "Transaction status not found with id: " + transaction.getTransactionStatusId())));
        newTransaction.setAmount(transaction.getAmount());
        newTransaction.setComment(transaction.getComment());
        newTransaction.setAccount(accountRepository.findById(
                transaction.getAccountId()).orElseThrow(() -> new IllegalArgumentException(
                "Account not found with id: " + transaction.getAccountId())));
        newTransaction.setCounterparty(counterpartyRepository.findById(
                transaction.getCounterpartyId()).orElseThrow(() -> new IllegalArgumentException(
                "Counterparty not found with id: " + transaction.getCounterpartyId())));
        newTransaction.setUserSender(transaction.isUserIsSender());
        newTransaction.setCategories(categoryRepository.findById(
                transaction.getCategoryId()).orElseThrow(() -> new IllegalArgumentException(
                "Category not found with id: " + transaction.getCategoryId())));
        newTransaction.setUser(userRepository.findById(transaction.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + transaction.getUserId())));
        transactionRepository.save(newTransaction);
        return mapTransactionToDTO(newTransaction);
    }

    @Override
    public String deleteTransaction(int id) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Transaction not found with id: " + id));
        transactionRepository.deleteById(id);
        return "Transaction removed successfully";
    }
}
