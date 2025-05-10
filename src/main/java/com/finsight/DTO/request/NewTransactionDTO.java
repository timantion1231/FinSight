package com.finsight.DTO.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NewTransactionDTO {
    private Instant dateTime;
    private int transactionTypeId;
    private int transactionStatusId;
    private int amount;
    private String comment;
    private int userId;
    private int counterpartyId;
    private boolean userIsSender;
    private int categoryId;
    private int accountId;
}
