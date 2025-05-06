package com.finsight.service.Impl;

import com.finsight.DTO.request.CounterPartyDTO;
import com.finsight.DTO.response.FullCounterpartyDTO;
import com.finsight.Randomizer;
import com.finsight.service.CounterpartyService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CounterpartyServiceImpl implements CounterpartyService {
    @Override
    public ArrayList<FullCounterpartyDTO> getAllCounterparties() {
        ArrayList<FullCounterpartyDTO> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(Randomizer.randomize(FullCounterpartyDTO.class));
        }
        return list;
    }

    @Override
    public FullCounterpartyDTO createCounterparty(CounterPartyDTO counterParty) {
        return Randomizer.randomize(FullCounterpartyDTO.class);
    }

    @Override
    public FullCounterpartyDTO updateCounterparty(FullCounterpartyDTO counterParty) {
        return Randomizer.randomize(FullCounterpartyDTO.class);
    }

    @Override
    public String deleteCounterparty(int id) {
        return "Removed successfully";
    }
}
