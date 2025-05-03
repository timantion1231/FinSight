package com.finsight.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.github.benas.randombeans.EnhancedRandomBuilder;
import io.github.benas.randombeans.api.EnhancedRandom;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Random;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountDTO {
    private int id;
    private int userId;
    private String accountNumber;
    private int bankId;

    public AccountDTO randomFill() {
        EnhancedRandom rnd = EnhancedRandomBuilder.aNewEnhancedRandom();
        return rnd.nextObject(AccountDTO.class);
    }

    @JsonIgnore
    public AccountDTO getBaseAccount() {
        return builder()
                .accountNumber(this.accountNumber)
                .bankId(this.bankId)
                .build();
    }

    @JsonIgnore
    public AccountDTO getFullAccount() {
        return this;
    }
}
