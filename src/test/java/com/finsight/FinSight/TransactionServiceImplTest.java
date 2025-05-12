package com.finsight.FinSight;

import com.finsight.DTO.request.EditTransactionDTO;
import com.finsight.DTO.request.NewTransactionDTO;
import com.finsight.DTO.response.FullTransactionDTO;
import com.finsight.entity.*;
import com.finsight.exceptions.ResourceNotFoundException;
import com.finsight.repository.*;
import com.finsight.service.impl.TransactionServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransactionServiceImplTest {

    @Mock TransactionRepository transactionRepository;
    @Mock AccountRepository accountRepository;
    @Mock CounterpartyRepository counterpartyRepository;
    @Mock UserRepository userRepository;
    @Mock TransactionTypeRepository transactionTypeRepository;
    @Mock TransactionStatusRepository transactionStatusRepository;
    @Mock CategoryRepository categoryRepository;

    @InjectMocks
    TransactionServiceImpl transactionService;


    @Test
    void testGetTransaction_notFound() {
        when(transactionRepository.findById(100)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> transactionService.getTransaction(100));
    }

    @Test
    void testDeleteTransaction_success() {
        doNothing().when(transactionRepository).deleteById(1);
        String result = transactionService.deleteTransaction(1);
        assertEquals("Transaction removed successfully", result);
        verify(transactionRepository).deleteById(1);
    }

    @Test
    void testCreateTransaction_missingEntity_throws() {
        NewTransactionDTO dto = new NewTransactionDTO();
        dto.setTransactionTypeId(1);
        when(transactionTypeRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> transactionService.createTransaction(dto));
    }

    @Test
    void testEditTransaction_invalidFields_shouldThrow() {
        EditTransactionDTO dto = new EditTransactionDTO();
        assertThrows(IllegalArgumentException.class, () -> transactionService.editTransaction(1, dto));
    }

    @Test
    void testGetAllTransactions_returns() {
        Transaction tx = new Transaction();
        User user = new User(); user.setId(1);
        Account acc = new Account(); acc.setId(1);
        Counterparty cp = new Counterparty(); cp.setId(1);
        TransactionType type = new TransactionType(); type.setId(1);
        TransactionStatus status = new TransactionStatus(); status.setId(1);
        Categories cat = new Categories(); cat.setId(1);

        tx.setId(1);
        tx.setUser(user);
        tx.setAccount(acc);
        tx.setCounterparty(cp);
        tx.setTransactionType(type);
        tx.setTransactionStatus(status);
        tx.setCategories(cat);

        when(transactionRepository.getAllByUserId(1)).thenReturn(new ArrayList<>(List.of(tx)));

        var list = transactionService.getAllTransactions(1);
        assertEquals(1, list.size());
        assertEquals(1, list.get(0).getId());
    }

    @Test
    void testCreateTransaction_userNotFound_shouldThrow() {
        NewTransactionDTO dto = new NewTransactionDTO();
        dto.setUserId(1);
        dto.setTransactionTypeId(1);
        dto.setTransactionStatusId(1);
        dto.setAccountId(1);
        dto.setCounterpartyId(1);
        dto.setCategoryId(1);

        when(userRepository.findById(1)).thenReturn(Optional.empty());
        when(transactionTypeRepository.findById(1)).thenReturn(Optional.of(new TransactionType()));
        when(transactionStatusRepository.findById(1)).thenReturn(Optional.of(new TransactionStatus()));
        when(accountRepository.findById(1)).thenReturn(Optional.of(new Account()));
        when(counterpartyRepository.findById(1)).thenReturn(Optional.of(new Counterparty()));
        when(categoryRepository.findById(1)).thenReturn(Optional.of(new Categories()));

        assertThrows(ResourceNotFoundException.class, () -> transactionService.createTransaction(dto));
    }

    @Test
    void testCreateTransaction_success() {
        NewTransactionDTO dto = new NewTransactionDTO();
        dto.setUserId(1);
        dto.setTransactionTypeId(1);
        dto.setTransactionStatusId(2);
        dto.setAccountId(3);
        dto.setCounterpartyId(4);
        dto.setCategoryId(5);
        dto.setAmount(1000);

        User user = new User(); user.setId(1);
        Account account = new Account(); account.setId(3);
        Counterparty cp = new Counterparty(); cp.setId(4);
        TransactionType type = new TransactionType(); type.setId(1);
        TransactionStatus status = new TransactionStatus(); status.setId(2);
        Categories cat = new Categories(); cat.setId(5);

        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(transactionTypeRepository.findById(1)).thenReturn(Optional.of(type));
        when(transactionStatusRepository.findById(2)).thenReturn(Optional.of(status));
        when(accountRepository.findById(3)).thenReturn(Optional.of(account));
        when(counterpartyRepository.findById(4)).thenReturn(Optional.of(cp));
        when(categoryRepository.findById(5)).thenReturn(Optional.of(cat));
        when(transactionRepository.save(any(Transaction.class))).thenAnswer(i -> {
            Transaction saved = i.getArgument(0);
            saved.setId(99); // эмулируем сохранение с ID
            return saved;
        });

        FullTransactionDTO result = transactionService.createTransaction(dto);

        assertNotNull(result);
        assertEquals(99, result.getId());
        assertEquals(1000, result.getAmount());
        assertEquals(3, result.getAccountId());
        assertEquals(1, result.getUserId());
        assertEquals(4, result.getCounterpartyId());
        assertEquals(1, result.getTransactionTypeId());
        assertEquals(2, result.getTransactionStatusId());
        assertEquals(5, result.getCategoryId());
    }
}

