package com.finsight.DTO.request;

import java.time.Instant;

public class EditTransactionDTO {
    private Instant dateTime;
    private int transactionTypeId;
    private int transactionStatusId;
    private int amount;
    private String comment;
    private int accountId;
    private int counterPartyId;
    private boolean userIsSender;
    private int categoryId;
}
/*
{
  "dateTime": "2025-05-04T13:11:50.222Z",
  "transactionTypeId": 0,
  "transactionStatusId": 0,
  "amount": 0,
  "comment": "string",
  "userId": 0,
  "counterpartyId": 0,
  "userIsSender": true,
  "receiverTin": "string",
  "receiverPhone": "string",
  "categoryId": 0
}
 */