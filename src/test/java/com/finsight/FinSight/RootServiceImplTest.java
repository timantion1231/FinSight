package com.finsight.FinSight;

import com.finsight.entity.*;
import com.finsight.exceptions.ResourceNotFoundException;
import com.finsight.repository.*;
import com.finsight.service.Impl.RootServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RootServiceImplTest {

    @Mock TransactionTypeRepository transactionTypeRepository;
    @Mock TransactionStatusRepository transactionStatusRepository;
    @Mock BankRepository bankRepository;
    @Mock CategoryRepository categoryRepository;
    @Mock EntityTypeRepository entityTypeRepository;

    @InjectMocks
    RootServiceImpl rootService;

    @Test
    void testGetAllEntityTypes_success() {
        EntityType type = new EntityType(); type.setId(1); type.setName("Fizik");
        when(entityTypeRepository.findAll()).thenReturn(List.of(type));

        var result = rootService.getAllEntityTypes();
        assertEquals(1, result.size());
        assertEquals("Fizik", result.get(0).getName());
    }

    @Test
    void testGetAllEntityTypes_empty_shouldThrow() {
        when(entityTypeRepository.findAll()).thenReturn(List.of());
        assertThrows(ResourceNotFoundException.class, () -> rootService.getAllEntityTypes());
    }

    @Test
    void testGetAllTransactionTypes() {
        TransactionType type = new TransactionType(); type.setId(1); type.setName("Income");
        when(transactionTypeRepository.findAll()).thenReturn(List.of(type));

        var result = rootService.getAllTransactionTypes();
        assertEquals(1, result.size());
        assertEquals("Income", result.get(0).getName());
    }

    @Test
    void testGetAllTransactionStatuses() {
        TransactionStatus status = new TransactionStatus(); status.setId(1); status.setName("New");
        when(transactionStatusRepository.findAll()).thenReturn(List.of(status));

        var result = rootService.getAllTransactionStatuses();
        assertEquals(1, result.size());
        assertEquals("New", result.get(0).getName());
    }

    @Test
    void testGetAllBanks() {
        Bank bank = new Bank(); bank.setId(1); bank.setName("Sber");
        when(bankRepository.findAll()).thenReturn(List.of(bank));

        var result = rootService.getAllBankDTO();
        assertEquals(1, result.size());
        assertEquals("Sber", result.get(0).getName());
    }

    @Test
    void testGetAllCategories() {
        Categories category = new Categories(); category.setId(1); category.setName("Food");
        when(categoryRepository.findAll()).thenReturn(List.of(category));

        var result = rootService.getAllCategories();
        assertEquals(1, result.size());
        assertEquals("Food", result.get(0).getName());
    }
}
