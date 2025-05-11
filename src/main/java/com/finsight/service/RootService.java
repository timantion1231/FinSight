package com.finsight.service;

import com.finsight.DTO.general.*;

import java.util.ArrayList;

public interface RootService {
    ArrayList<EntityTypesDTO> getAllEntityTypes();

    ArrayList<TransactionTypesDTO> getAllTransactionTypes();

    ArrayList<TransactionStatusesDTO> getAllTransactionStatuses();

    ArrayList<BankDTO> getAllBankDTO();

    ArrayList<CategoryDTO> getAllCategories();
}
