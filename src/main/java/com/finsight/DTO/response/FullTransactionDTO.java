package com.finsight.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FullTransactionDTO {
    private int id;
    private Instant dateTime;
    private int transactionTypeId;
    private int transactionStatusId;
    private int amount;
    private String comment;
    private int userId;
    private int counterpartyId;
    private boolean userIsSender;
    private int accountId;
    private int categoryId;
}