package com.finsight.service.Impl;

import com.finsight.DTO.request.EditTransactionDTO;
import com.finsight.DTO.request.NewTransactionDTO;
import com.finsight.DTO.response.FullTransactionDTO;
import com.finsight.Randomizer;
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
    public ArrayList<FullTransactionDTO> getAllTransactions() {
        ArrayList<FullTransactionDTO> transactions = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            transactions.add(Randomizer.randomize(FullTransactionDTO.class));
        }
        return transactions;
    }

    @Override
    public FullTransactionDTO getTransaction(int id) {
        return Randomizer.randomize(FullTransactionDTO.class);
    }

    @Override
    public FullTransactionDTO editTransaction(EditTransactionDTO transaction) {
        return Randomizer.randomize(FullTransactionDTO.class);
    }

    @Override
    public FullTransactionDTO createTransaction(NewTransactionDTO transaction) {
        return Randomizer.randomize(FullTransactionDTO.class);
    }

    @Override
    public String deleteTransaction(int id) {
        return "Transaction removed successfully";
    }
}
