package com.finsight.service.Impl;

import com.finsight.DTO.request.EditTransactionDTO;
import com.finsight.DTO.request.NewTransactionDTO;
import com.finsight.DTO.response.FullTransactionDTO;
import com.finsight.Randomizer;
import com.finsight.entity.Counterparty;
import com.finsight.entity.Transaction;
import com.finsight.entity.User;
import com.finsight.repository.AccountRepository;
import com.finsight.repository.CounterpartyRepository;
import com.finsight.repository.TransactionRepository;
import com.finsight.repository.UserRepository;
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

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository,
                                  AccountRepository accountRepository,
                                  CounterpartyRepository counterpartyRepository,
                                  UserRepository userRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
        this.counterpartyRepository = counterpartyRepository;
        this.userRepository = userRepository;
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
        existingTransaction.setTransactionType(transactionRepository.getTransactionTypeById(
                transaction.getTransactionTypeId()));
        existingTransaction.setTransactionStatus(transactionRepository.getTransactionStatusById(
                transaction.getTransactionStatusId()));
        existingTransaction.setAmount(transaction.getAmount());
        existingTransaction.setComment(transaction.getComment());
        existingTransaction.setAccount(accountRepository.findById(
                transaction.getAccountId()).orElseThrow(() -> new IllegalArgumentException(
                "Account not found with id: " + transaction.getAccountId())));
        existingTransaction.setCounterparty(counterpartyRepository.findById(
                transaction.getCounterpartyId()));
        existingTransaction.setUserSender(transaction.isUserIsSender());
        existingTransaction.setCategories(transactionRepository.getCategoriesById(
                transaction.getCategoryId()));
        transactionRepository.save(existingTransaction);
        return mapTransactionToDTO(existingTransaction);
    }

    @Override
    public FullTransactionDTO createTransaction(NewTransactionDTO transaction) {
        Transaction newTransaction = new Transaction();
        newTransaction.setDateTime(transaction.getDateTime());
        newTransaction.setTransactionType(transactionRepository.getTransactionTypeById(
                transaction.getTransactionTypeId()));
        newTransaction.setTransactionStatus(transactionRepository.getTransactionStatusById(
                transaction.getTransactionStatusId()));
        newTransaction.setAmount(transaction.getAmount());
        newTransaction.setComment(transaction.getComment());
        newTransaction.setAccount(accountRepository.findById(
                transaction.getAccountId()).orElseThrow(() -> new IllegalArgumentException(
                "Account not found with id: " + transaction.getAccountId())));
        newTransaction.setCounterparty(counterpartyRepository.findById(
                transaction.getCounterpartyId()));
        newTransaction.setUserSender(transaction.isUserIsSender());
        newTransaction.setCategories(transactionRepository.getCategoriesById(
                transaction.getCategoryId()));
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
