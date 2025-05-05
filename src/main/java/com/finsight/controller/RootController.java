package com.finsight.controller;

import com.finsight.DTO.general.*;
import com.finsight.service.RootService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/")
public class RootController {

    private final RootService rootService;

    @Autowired
    public RootController(RootService rootService) {
        this.rootService = rootService;
    }

    @GetMapping("/entity_types")
    public ArrayList<EntityTypesDTO> getEntityTypes() {
        return rootService.getAllEntityTypes();
    }

    @GetMapping("/transaction_types")
    public ArrayList<TransactionTypesDTO> getTransactionTypes() {
        return rootService.getAllTransactionTypes();
    }

    @GetMapping("/transaction_statuses")
    public ArrayList<TransactionStatusesDTO> getTransactionStatuses() {
        return rootService.getAllTransactionStatuses();
    }

    @GetMapping("/banks")
    public ArrayList<BankDTO> getBanks() {
        return rootService.getAllBankDTO();
    }

    @GetMapping("/categories")
    public ArrayList<CategoryDTO> getCategories() {
        return rootService.getAllCategories();
    }

}
