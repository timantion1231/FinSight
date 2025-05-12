package com.finsight.FinSight;

import com.finsight.DTO.request.FullUserAccountDTO;
import com.finsight.entity.*;
import com.finsight.exceptions.ResourceNotFoundException;
import com.finsight.repository.*;
import com.finsight.service.impl.UserAccountServiceImpl;
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
class UserAccountServiceImplTest {

    @Mock
    private AccountRepository accountRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private BankRepository bankRepository;

    @InjectMocks
    private UserAccountServiceImpl service;

    @Test
    void testGetAllAccounts_returns() {
        Account acc = new Account();
        User user = new User(); user.setId(1);
        Bank bank = new Bank(); bank.setId(1);
        acc.setId(1);
        acc.setUser(user);
        acc.setBank(bank);
        acc.setAccountNumber("123");

        when(accountRepository.findByUserId(1)).thenReturn(new ArrayList<>(List.of(acc)));

        var result = service.getAllAccounts(1);
        assertEquals(1, result.size());
        assertEquals("123", result.get(0).getAccountNumber());
    }

    @Test
    void testGetAccount_notFound() {
        when(accountRepository.findById(5)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> service.getAccount(5));
    }

    @Test
    void testCreateAccount_missingFields_shouldThrow() {
        FullUserAccountDTO dto = new FullUserAccountDTO();
        assertThrows(IllegalArgumentException.class, () -> service.createAccount(dto));
    }

    @Test
    void testCreateAccount_success() {
        FullUserAccountDTO dto = new FullUserAccountDTO();
        dto.setUserId(1);
        dto.setBankId(2);
        dto.setAccountNumber("123");

        User user = new User(); user.setId(1);
        Bank bank = new Bank(); bank.setId(2);

        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(bankRepository.findById(2)).thenReturn(Optional.of(bank));
        when(accountRepository.save(any(Account.class))).thenAnswer(i -> i.getArguments()[0]);

        var result = service.createAccount(dto);
        assertEquals("123", result.getAccountNumber());
        assertEquals(1, result.getUserId());
        assertEquals(2, result.getBankId());
    }

    @Test
    void testEditAccount_invalidInput_shouldThrow() {
        FullUserAccountDTO dto = new FullUserAccountDTO();
        assertThrows(IllegalArgumentException.class, () -> service.editAccount(1, dto));
    }

    @Test
    void testDeleteAccount_success() {
        doNothing().when(accountRepository).deleteById(1);
        String result = service.deleteAccount(1);
        assertEquals("Account removed successfully", result);
        verify(accountRepository).deleteById(1);
    }
}
