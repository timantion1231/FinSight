package com.finsight.FinSight;

import com.finsight.DTO.request.RegisterUserDTO;
import com.finsight.exceptions.EmailAlreadyExistsException;
import com.finsight.repository.UserRepository;
import com.finsight.service.Impl.AuthServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthServiceImpl authService;

    @Test
    void testRegister_success() {
        RegisterUserDTO dto = new RegisterUserDTO();
        dto.setEmail("test@example.com");
        dto.setPassword("123456");
        dto.setTin("123561616165");
        dto.setPhoneNumber("+79883777366");

        when(userRepository.existsByEmail("test@example.com")).thenReturn(false);
        authService.register(dto);

        verify(userRepository).save(argThat(user ->
                user.getEmail().equals("test@example.com") &&
                        user.getTin().equals("123561616165") &&
                        user.getPhone().equals("+79883777366")
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

