package com.yash.ecom.server.service;

import com.yash.ecom.server.entity.Party;

public interface PartyService {
    void add(Party party);

    Iterable<Party> listParty();
}
