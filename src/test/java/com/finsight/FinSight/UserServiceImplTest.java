package com.finsight.FinSight;

import com.finsight.DTO.request.UserUpdateDTO;
import com.finsight.DTO.response.FullUserClientDTO;
import com.finsight.DTO.response.ReportDTO;
import com.finsight.entity.User;
import com.finsight.exceptions.ResourceNotFoundException;
import com.finsight.repository.AccountRepository;
import com.finsight.repository.UserRepository;
import com.finsight.service.Impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void testGetBaseUserProfile_userExists() {
        User user = new User();
        user.setId(1);
        user.setName("Ivan Mohov");
        user.setPhone("+79828992475");
        user.setEmail("ivan@example.com");
        user.setTin("0000");

        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(accountRepository.findByUserId(1)).thenReturn(new ArrayList<>());

        FullUserClientDTO dto = userService.getBaseUserProfile(1);

        assertEquals(1, dto.getId());
        assertEquals("Ivan Mohov", dto.getName());
        assertEquals("+79828992475", dto.getPhoneNumber());
        assertEquals("ivan@example.com", dto.getEmail());
        assertEquals("0000", dto.getTin());

        verify(userRepository, times(2)).findById(1);
    }

    @Test
    void testGetBaseUserProfile_userNotFound() {
        when(userRepository.findById(999)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> userService.getBaseUserProfile(999));
    }

    @Test
    void testEditUserProfile_success() {
        int userId = 1;
        User existingUser = new User();
        existingUser.setId(userId);
        existingUser.setName("Sergey Ivanov");
        existingUser.setEmail("old@example.com");
        existingUser.setPhone("+79883177355");
        existingUser.setTin("1235614446165");

        UserUpdateDTO updateDTO = new UserUpdateDTO();
        updateDTO.setName("Aleksey Gorchakov");
        updateDTO.setEmail("new@example.com");
        updateDTO.setPhoneNumber("+79883777366");
        updateDTO.setTin("123561616165");

        when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));
        doNothing().when(userRepository).updateUserProfile(any(User.class));
        when(accountRepository.findByUserId(userId)).thenReturn(new ArrayList<>());

        FullUserClientDTO result = userService.editUserProfile(userId, updateDTO);

        assertEquals("Aleksey Gorchakov", result.getName());
        assertEquals("new@example.com", result.getEmail());
        assertEquals("+79883777366", result.getPhoneNumber());
        assertEquals("123561616165", result.getTin());
    }

    @Test
    void testEditUserProfile_missingFields_shouldThrow() {
        UserUpdateDTO updateDTO = new UserUpdateDTO();
        assertThrows(IllegalArgumentException.class, () -> userService.editUserProfile(1, updateDTO));
    }

    @Test
    void testGetReport_returnsEmptyReport() {
        ReportDTO report = userService.getReport(1);
        assertNotNull(report);
    }
}
