package com.finsight.service;

import com.finsight.DTO.request.EditTransactionDTO;
import com.finsight.DTO.request.NewTransactionDTO;
import com.finsight.DTO.response.FullTransactionDTO;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

public interface TransactionService {
    ArrayList<FullTransactionDTO> getAllTransactions(int userId);

    FullTransactionDTO getTransaction(int id);

    FullTransactionDTO editTransaction(int id, EditTransactionDTO transaction);

    FullTransactionDTO createTransaction(NewTransactionDTO transaction);

    String deleteTransaction(int id);

    ResponseEntity<ByteArrayResource> generateExcelReport(
            Long senderBankId,
            Long counterpartyBankId,
            LocalDate dateFrom,
            LocalDate dateTo,
            Integer transactionStatusId,
            Integer transactionTypeId,
            String counterpartyTin,
            BigDecimal amountMin,
            BigDecimal amountMax,
            Long categoryId
    );

}
