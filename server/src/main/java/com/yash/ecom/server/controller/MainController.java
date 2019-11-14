package com.yash.ecom.server.controller;

import com.yash.ecom.server.entity.Party;
import com.yash.ecom.server.service.PartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/api")
public class MainController {

    @Autowired
    private PartyService partyService;

    @GetMapping(path = "/party/all")
    public @ResponseBody
    Iterable<Party> getAllParties() {
        return partyService.listParty();
    }
}
