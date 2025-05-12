package com.finsight.FinSight;

import com.finsight.DTO.request.CounterpartyDTO;
import com.finsight.entity.Bank;
import com.finsight.entity.Counterparty;
import com.finsight.entity.EntityType;
import com.finsight.entity.User;
import com.finsight.exceptions.ResourceNotFoundException;
import com.finsight.repository.*;
import com.finsight.service.Impl.CounterpartyServiceImpl;
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
class CounterpartyServiceImplTest {

    @Mock
    private CounterpartyRepository counterpartyRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private EntityTypeRepository entityTypeRepository;

    @Mock
    private BankRepository bankRepository;

    @InjectMocks
    private CounterpartyServiceImpl counterpartyService;

    @Test
    void testGetAllCounterparties_success() {
        Counterparty cp = new Counterparty();
        cp.setId(1);
        cp.setName("Ivan Mohov");
        cp.setTin("123561616165");
        cp.setPhoneNumber("+79998882905");
        cp.setAccountNumber("98765");

        Bank bank = new Bank(); bank.setId(1);
        cp.setBank(bank);
        EntityType type = new EntityType(); type.setId(1);
        cp.setEntityType(type);

        when(counterpartyRepository.findByUserId(1)).thenReturn(new ArrayList<>(List.of(cp)));

        var result = counterpartyService.getAllCounterparties(1);
        assertEquals(1, result.size());
        assertEquals("Ivan Mohov", result.get(0).getName());
    }

    @Test
    void testCreateCounterparty_success() {
        CounterpartyDTO dto = new CounterpartyDTO();
        dto.setName("Petr Sergeev");
        dto.setTin("123561616165");
        dto.setPhoneNumber("+79998882905");
        dto.setAccountNumber("98765");
        dto.setUserId(1);
        dto.setEntityTypeId(1);
        dto.setBankId(2);

        User user = new User(); user.setId(1);
        Bank bank = new Bank(); bank.setId(2);
        EntityType type = new EntityType(); type.setId(1);

        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(bankRepository.findById(2)).thenReturn(Optional.of(bank));
        when(entityTypeRepository.findById(1)).thenReturn(Optional.of(type));
        when(counterpartyRepository.save(any(Counterparty.class))).thenAnswer(i -> i.getArguments()[0]);

        var result = counterpartyService.createCounterparty(dto);

        assertEquals("Petr Sergeev", result.getName());
        assertEquals("123561616165", result.getTin());
        assertEquals(2, result.getBankId());
        assertEquals(1, result.getEntityTypeId());
    }

    @Test
    void testUpdateCounterparty_notFound() {
        when(counterpartyRepository.findById(10)).thenReturn(Optional.empty());

        CounterpartyDTO dto = new CounterpartyDTO();
        dto.setUserId(1);
        dto.setBankId(1);
        dto.setEntityTypeId(1);

        assertThrows(ResourceNotFoundException.class,
                () -> counterpartyService.updateCounterparty(10, dto));
    }

    @Test
    void testDeleteCounterparty_success() {
        doNothing().when(counterpartyRepository).deleteById(1);
        String result = counterpartyService.deleteCounterparty(1);
        assertEquals("Removed successfully", result);
        verify(counterpartyRepository).deleteById(1);
    }

    @Test
    void testCreateCounterparty_userNotFound_shouldThrow() {
        CounterpartyDTO dto = new CounterpartyDTO();
        dto.setUserId(1);
        dto.setBankId(1);
        dto.setEntityTypeId(1);

        when(userRepository.findById(1)).thenReturn(Optional.empty());
        when(bankRepository.findById(1)).thenReturn(Optional.of(new Bank()));
        when(entityTypeRepository.findById(1)).thenReturn(Optional.of(new EntityType()));
        assertThrows(ResourceNotFoundException.class, () -> counterpartyService.createCounterparty(dto));
    }

    @Test
    void testUpdateCounterparty_counterpartyNotFound_shouldThrow() {
        when(counterpartyRepository.findById(999)).thenReturn(Optional.empty());

        CounterpartyDTO dto = new CounterpartyDTO();
        dto.setUserId(1);
        dto.setBankId(1);
        dto.setEntityTypeId(1);

        assertThrows(ResourceNotFoundException.class, () -> counterpartyService.updateCounterparty(999, dto));
    }
}
