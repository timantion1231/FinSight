package com.finsight.service.Impl;

import com.finsight.DTO.AccountDTO;
import com.finsight.DTO.TransactionDTO;
import com.finsight.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    public TransactionServiceImpl() {

    }

    @Override
    public ArrayList<TransactionDTO> getAllTransactions() {
        ArrayList<TransactionDTO> transactions = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            transactions.add(new TransactionDTO().randomFill().getBaseTransaction());
        }
        return transactions;
    }

    @Override
    public TransactionDTO getTransaction(int id) {
        return new TransactionDTO().randomFill().getBaseTransaction();
    }

    @Override
    public TransactionDTO editTransaction(TransactionDTO transaction) {
        return transaction;
    }

    @Override
    public TransactionDTO createTransaction(TransactionDTO transaction) {
        return transaction;
    }

    @Override
    public String deleteTransaction(int id) {
        return "Transaction removed successfully";
    }
}
