package com.finsight.service;

import com.finsight.DTO.TransactionDTO;

import java.util.ArrayList;

public interface TransactionService {
    public ArrayList<TransactionDTO> getAllTransactions();

    public TransactionDTO getTransaction(int id);

    public TransactionDTO editTransaction(TransactionDTO transaction);

    public TransactionDTO createTransaction(TransactionDTO transaction);

    public String deleteTransaction(int id);

}
