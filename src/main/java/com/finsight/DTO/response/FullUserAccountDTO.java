package com.finsight.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FullUserAccountDTO {
    private int id;
    private String accountNumber;
    private int bankId;
    private int userId;
}
