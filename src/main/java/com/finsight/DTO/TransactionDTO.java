package com.finsight.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.github.benas.randombeans.EnhancedRandomBuilder;
import io.github.benas.randombeans.api.EnhancedRandom;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionDTO {

    private int id;
    private Instant dateTime;
    private int transactionTypeId;
    private int transactionStatusId;
    private int amount;
    private String comment;
    private int accountId;
    private int counterPartyId;
    private boolean userIsSender;
    private int categoryId;

    @JsonIgnore
    public TransactionDTO getFullTransaction() {
        return this;
    }

    @JsonIgnore
    public TransactionDTO getBaseTransaction() {
        return builder()
                .dateTime(this.dateTime)
                .transactionTypeId(this.transactionTypeId)
                .transactionStatusId(this.transactionStatusId)
                .amount(this.amount)
                .comment(this.comment)
                .accountId(this.accountId)
                .counterPartyId(this.counterPartyId)
                .userIsSender(this.userIsSender)
                .categoryId(this.categoryId)
                .build();
    }

    public TransactionDTO randomFill() {
        EnhancedRandom rnd = EnhancedRandomBuilder.aNewEnhancedRandom();
        return rnd.nextObject(TransactionDTO.class);
    }
}
