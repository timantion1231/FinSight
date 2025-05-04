package com.finsight.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FullCounterpartyDTO {
    private int id;
    private int entityTypeId;
    private String tin;
    private String name;
    private String phoneNumber;
    private String accountNumber;
    private int bankId;
}