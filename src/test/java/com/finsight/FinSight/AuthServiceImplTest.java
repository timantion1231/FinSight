package com.finsight.FinSight;

import com.finsight.DTO.request.RegisterUserDTO;
import com.finsight.entity.Account;
import com.finsight.entity.Bank;
import com.finsight.entity.User;
import com.finsight.exceptions.EmailAlreadyExistsException;
import com.finsight.repository.AccountRepository;
import com.finsight.repository.BankRepository;
import com.finsight.repository.UserRepository;
import com.finsight.service.Impl.AuthServiceImpl;
import com.finsight.service.UserAccountService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UserAccountService userAccountService;

    @Mock
    private BankRepository bankRepository;

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AuthServiceImpl authService;

    @Test
    void testRegister_success() {
        RegisterUserDTO dto = new RegisterUserDTO();
        dto.setEmail("vanya@gmail.com");
        dto.setPassword("123456");
        dto.setTin("123561616165");
        dto.setPhoneNumber("+79883777366");
        dto.setName("Ivan Ivanov");

        dto.setAccountNumber("13512511");
        dto.setBankId(1);

        when(userRepository.save(any(User.class))).thenAnswer(invocation -> {
            User user = invocation.getArgument(0);
            user.setId(1);
            return user;
        });

        when(userRepository.existsByEmail("vanya@gmail.com")).thenReturn(false);
        when(userRepository.findById(1)).thenReturn(Optional.of(new User()));
        when(bankRepository.findById(1)).thenReturn(Optional.of(new Bank()));
        when(accountRepository.save(any(Account.class))).thenReturn(new Account());

        authService.register(dto);

        verify(userRepository).save(argThat(user ->
                user.getEmail().equals("vanya@gmail.com") &&
                        user.getTin().equals("123561616165") &&
                        user.getPhone().equals("+79883777366") &&
                        user.getName().equals("Ivan Ivanov")
        ));
    }

    @Test
    void testRegister_emailExists_shouldThrow() {
        RegisterUserDTO dto = new RegisterUserDTO();
        dto.setEmail("existing@example.com");

        when(userRepository.existsByEmail("existing@example.com")).thenReturn(true);

        assertThrows(EmailAlreadyExistsException.class, () -> authService.register(dto));
        verify(userRepository, never()).save(any());
    }
}

