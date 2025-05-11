package com.finsight.service.Impl;

import com.finsight.DTO.general.*;
import com.finsight.entity.*;
import com.finsight.exceptions.ResourceNotFoundException;
import com.finsight.repository.*;
import com.finsight.service.RootService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RootServiceImpl implements RootService {

    private final TransactionStatusRepository transactionStatusRepository;
    private final TransactionTypeRepository transactionTypeRepository;
    private final BankRepository bankRepository;
    private final CategoryRepository categoryRepository;
    private final EntityTypeRepository entityTypeRepository;

    @Autowired
    public RootServiceImpl(TransactionTypeRepository transactionTypeRepository,
                           TransactionStatusRepository transactionStatusRepository,
                           BankRepository bankRepository,
                           CategoryRepository categoryRepository,
                           EntityTypeRepository entityTypeRepository) {
        this.transactionTypeRepository = transactionTypeRepository;
        this.transactionStatusRepository = transactionStatusRepository;
        this.bankRepository = bankRepository;
        this.categoryRepository = categoryRepository;
        this.entityTypeRepository = entityTypeRepository;
    }

    @Override
    public ArrayList<EntityTypesDTO> getAllEntityTypes() {
        List<EntityType> list = entityTypeRepository.findAll();
        if (list.isEmpty()) {
            throw new ResourceNotFoundException("No entity types found");
        }
        ArrayList<EntityTypesDTO> entityTypesDTOs = new ArrayList<>();
        for (EntityType entityType : list) {
            EntityTypesDTO entityTypesDTO = new EntityTypesDTO();
            entityTypesDTO.setId(entityType.getId());
            entityTypesDTO.setName(entityType.getName());
            entityTypesDTOs.add(entityTypesDTO);
        }
        return entityTypesDTOs;
    }

    @Override
    public ArrayList<TransactionTypesDTO> getAllTransactionTypes() {
        List<TransactionType> list;
        list = transactionTypeRepository.findAll();
        ArrayList<TransactionTypesDTO> transactionTypesDTOs = new ArrayList<>();
        for (TransactionType transactionType : list) {
            TransactionTypesDTO transactionTypesDTO = new TransactionTypesDTO();
            transactionTypesDTO.setId(transactionType.getId());
            transactionTypesDTO.setName(transactionType.getName());
            transactionTypesDTOs.add(transactionTypesDTO);
        }
        return transactionTypesDTOs;
    }

    @Override
    public ArrayList<TransactionStatusesDTO> getAllTransactionStatuses() {
        List<TransactionStatus> list;
        list = transactionStatusRepository.findAll();
        ArrayList<TransactionStatusesDTO> transactionStatusesDTOs = new ArrayList<>();
        for (TransactionStatus transactionStatus : list) {
            TransactionStatusesDTO transactionStatusesDTO = new TransactionStatusesDTO();
            transactionStatusesDTO.setId(transactionStatus.getId());
            transactionStatusesDTO.setName(transactionStatus.getName());
            transactionStatusesDTOs.add(transactionStatusesDTO);
        }
        return transactionStatusesDTOs;
    }

    @Override
    public ArrayList<BankDTO> getAllBankDTO() {
        List<Bank> list;
        list = bankRepository.findAll();
        ArrayList<BankDTO> bankDTOs = new ArrayList<>();
        for (Bank bank : list) {
            BankDTO bankDTO = new BankDTO();
            bankDTO.setId(bank.getId());
            bankDTO.setName(bank.getName());
            bankDTOs.add(bankDTO);
        }
        return bankDTOs;
    }

    @Override
    public ArrayList<CategoryDTO> getAllCategories() {
        List<Categories> list;
        list = categoryRepository.findAll();
        ArrayList<CategoryDTO> categoryDTOs = new ArrayList<>();
        for (Categories categories : list) {
            CategoryDTO categoryDTO = new CategoryDTO();
            categoryDTO.setId(categories.getId());
            categoryDTO.setName(categories.getName());
            categoryDTOs.add(categoryDTO);
        }
        return categoryDTOs;
    }
}
