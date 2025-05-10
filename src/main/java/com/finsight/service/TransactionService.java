package com.finsight.service;

import com.finsight.DTO.request.EditTransactionDTO;
import com.finsight.DTO.request.NewTransactionDTO;
import com.finsight.DTO.response.FullTransactionDTO;

import java.util.ArrayList;

public interface TransactionService {
    public ArrayList<FullTransactionDTO> getAllTransactions(int userId);

    public FullTransactionDTO getTransaction(int id);

    public FullTransactionDTO editTransaction(int id, EditTransactionDTO transaction);

    public FullTransactionDTO createTransaction(NewTransactionDTO transaction);

    public String deleteTransaction(int id);

}
