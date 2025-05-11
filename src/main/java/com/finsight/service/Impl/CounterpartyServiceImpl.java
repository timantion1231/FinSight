package com.finsight.service.Impl;

import com.finsight.DTO.request.CounterpartyDTO;
import com.finsight.DTO.response.FullCounterpartyDTO;
import com.finsight.entity.Counterparty;
import com.finsight.repository.*;
import com.finsight.service.CounterpartyService;
import com.finsight.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CounterpartyServiceImpl implements CounterpartyService {

    private final CounterpartyRepository counterpartyRepository;
    private final UserRepository userRepository;
    private final EntityTypeRepository entityTypeRepository;
    private final BankRepository bankRepository;

    @Autowired
    public CounterpartyServiceImpl(CounterpartyRepository counterpartyRepository,
                                   UserRepository userRepository,
                                   EntityTypeRepository entityTypeRepository,
                                   BankRepository bankRepository) {
        this.counterpartyRepository = counterpartyRepository;
        this.userRepository = userRepository;
        this.entityTypeRepository = entityTypeRepository;
        this.bankRepository = bankRepository;
    }

    @Override
    public ArrayList<FullCounterpartyDTO> getAllCounterparties(int userId) {
        ArrayList<FullCounterpartyDTO> list = new ArrayList<>();
        ArrayList<Counterparty> counterparties = (ArrayList<Counterparty>)
                counterpartyRepository.findByUserId(userId);
        for (Counterparty counterparty : counterparties) {
            list.add(mapCounterpartyToDTO(counterparty));
        }
        return list;
    }

    private FullCounterpartyDTO mapCounterpartyToDTO(Counterparty counterparty) {
        FullCounterpartyDTO dto = new FullCounterpartyDTO();
        dto.setId(counterparty.getId());
        dto.setEntityTypeId(counterparty.getEntityType().getId());
        dto.setTin(counterparty.getTin());
        dto.setName(counterparty.getName());
        dto.setPhoneNumber(counterparty.getPhoneNumber());
        dto.setAccountNumber(counterparty.getAccountNumber());
        dto.setBankId(counterparty.getBank().getId());
        return dto;
    }

    @Override
    public FullCounterpartyDTO createCounterparty(CounterpartyDTO counterparty) {
        Counterparty newCounterparty = new Counterparty();
        newCounterparty.setName(counterparty.getName());
        newCounterparty.setTin(counterparty.getTin());
        newCounterparty.setPhoneNumber(counterparty.getPhoneNumber());
        newCounterparty.setAccountNumber(counterparty.getAccountNumber());
        newCounterparty.setEntityType(entityTypeRepository.findById(counterparty.getEntityTypeId())
                .orElseThrow(() -> new ResourceNotFoundException("Entity type not found with id: " + counterparty.getEntityTypeId())));
        newCounterparty.setBank(bankRepository.findById(counterparty.getBankId())
                .orElseThrow(() -> new ResourceNotFoundException("Bank not found with id: " + counterparty.getBankId())));
        newCounterparty.setUser(userRepository.findById(counterparty.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + counterparty.getUserId())));
        counterpartyRepository.save(newCounterparty);
        return mapCounterpartyToDTO(newCounterparty);
    }

    @Override
    public FullCounterpartyDTO updateCounterparty(int id, CounterpartyDTO dto) {
        Counterparty counterparty = counterpartyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Counterparty not found with id: " + id));
        counterparty.setEntityType(entityTypeRepository.findById(dto.getEntityTypeId())
                .orElseThrow(() -> new ResourceNotFoundException("Entity type not found with id: " + dto.getEntityTypeId())));
        counterparty.setTin(dto.getTin());
        counterparty.setName(dto.getName());
        counterparty.setPhoneNumber(dto.getPhoneNumber());
        counterparty.setAccountNumber(dto.getAccountNumber());
        counterparty.setBank(bankRepository.findById(dto.getBankId())
                .orElseThrow(() -> new ResourceNotFoundException("Bank not found with id: " + dto.getBankId())));
        counterparty.setUser(userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + dto.getUserId())));
        counterpartyRepository.save(counterparty);
        return mapCounterpartyToDTO(counterparty);
    }

    @Override
    public String deleteCounterparty(int id) {
        counterpartyRepository.deleteById(id);
        return "Removed successfully";
    }
}
