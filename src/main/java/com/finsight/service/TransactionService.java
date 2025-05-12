package com.finsight.service;

import com.finsight.DTO.request.EditTransactionDTO;
import com.finsight.DTO.request.NewTransactionDTO;
import com.finsight.DTO.response.FullTransactionDTO;

import java.util.ArrayList;

public interface TransactionService {
    ArrayList<FullTransactionDTO> getAllTransactions(int userId);

    FullTransactionDTO getTransaction(int id);

    FullTransactionDTO editTransaction(int id, EditTransactionDTO transaction);

    FullTransactionDTO createTransaction(NewTransactionDTO transaction);

    String deleteTransaction(int id);

}
