package com.yash.ecom.server.service.impl;

import com.yash.ecom.server.entity.Party;
import com.yash.ecom.server.repository.PartyRepository;
import com.yash.ecom.server.service.PartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PartyServiceImp implements PartyService {

    @Autowired
    PartyRepository partyRepository;

    @Override
    public void add(Party party) {
        partyRepository.save(party);
    }

    @Override
    public Iterable<Party> listParty() {
        return partyRepository.findAll();
    }
}
