package com.finsight.service;

import com.finsight.DTO.general.*;

import java.util.ArrayList;

public interface RootService {
    public ArrayList<EntityTypesDTO> getAllEntityTypes();

    public ArrayList<TransactionTypesDTO> getAllTransactionTypes();

    public ArrayList<TransactionStatusesDTO> getAllTransactionStatuses();

    public ArrayList<BankDTO> getAllBankDTO();

    public ArrayList<CategoryDTO> getAllCategories();
}
