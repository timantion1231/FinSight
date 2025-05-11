package com.finsight.service;

import com.finsight.DTO.request.CounterpartyDTO;
import com.finsight.DTO.response.FullCounterpartyDTO;

import java.util.ArrayList;

public interface CounterpartyService {
    public ArrayList<FullCounterpartyDTO> getAllCounterparties(int userId);

    public FullCounterpartyDTO createCounterparty(CounterpartyDTO counterParty);

    public FullCounterpartyDTO updateCounterparty(int id, CounterpartyDTO counterparty);

    public String deleteCounterparty(int id);
}
