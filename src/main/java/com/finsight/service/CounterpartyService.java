package com.finsight.service;

import com.finsight.DTO.request.CounterpartyDTO;
import com.finsight.DTO.response.FullCounterpartyDTO;

import java.util.ArrayList;

public interface CounterpartyService {
     ArrayList<FullCounterpartyDTO> getAllCounterparties(int userId);

     FullCounterpartyDTO createCounterparty(CounterpartyDTO counterParty);

     FullCounterpartyDTO updateCounterparty(int id, CounterpartyDTO counterparty);

     String deleteCounterparty(int id);
}
