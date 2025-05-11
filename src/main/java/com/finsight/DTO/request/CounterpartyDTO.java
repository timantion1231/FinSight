package com.finsight.DTO.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CounterpartyDTO {
    private int entityTypeId;
    private String tin;
    private String name;
    private String phoneNumber;
    private String accountNumber;
    private int bankId;
    private int userId;
}