package com.finsight.service.Impl;

import com.finsight.DTO.general.*;
import com.finsight.service.RootService;
import io.github.benas.randombeans.EnhancedRandomBuilder;
import io.github.benas.randombeans.api.EnhancedRandom;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class RootServiceImpl implements RootService {

    private EnhancedRandom rnd;

    @Override
    public ArrayList<EntityTypesDTO> getAllEntityTypes() {
        rnd = EnhancedRandomBuilder.aNewEnhancedRandom();
        ArrayList<EntityTypesDTO> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(rnd.nextObject(EntityTypesDTO.class));
        }
        return list;
    }

    @Override
    public ArrayList<TransactionTypesDTO> getAllTransactionTypes() {
        rnd = EnhancedRandomBuilder.aNewEnhancedRandom();
        ArrayList<TransactionTypesDTO> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(rnd.nextObject(TransactionTypesDTO.class));
        }
        return list;
    }

    @Override
    public ArrayList<TransactionStatusesDTO> getAllTransactionStatuses() {
        rnd = EnhancedRandomBuilder.aNewEnhancedRandom();
        ArrayList<TransactionStatusesDTO> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(rnd.nextObject(TransactionStatusesDTO.class));
        }
        return list;
    }

    @Override
    public ArrayList<BankDTO> getAllBankDTO() {
        rnd = EnhancedRandomBuilder.aNewEnhancedRandom();
        ArrayList<BankDTO> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(rnd.nextObject(BankDTO.class));
        }
        return list;
    }

    @Override
    public ArrayList<CategoryDTO> getAllCategories() {
        rnd = EnhancedRandomBuilder.aNewEnhancedRandom();
        ArrayList<CategoryDTO> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(rnd.nextObject(CategoryDTO.class));
        }
        return list;
    }

}
