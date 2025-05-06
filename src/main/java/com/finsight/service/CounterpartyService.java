package com.finsight.service;

import com.finsight.DTO.request.CounterPartyDTO;
import com.finsight.DTO.response.FullCounterpartyDTO;

import java.util.ArrayList;

public interface CounterpartyService {
    public ArrayList<FullCounterpartyDTO> getAllCounterparties();

    public FullCounterpartyDTO createCounterparty(CounterPartyDTO counterParty);

    public FullCounterpartyDTO updateCounterparty(FullCounterpartyDTO counterParty);

    public String deleteCounterparty(int id);
}
