package com.soccer.controller;

import com.soccer.model.TeamAddress;
import com.soccer.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class AddressController {
    private AddressService addressService;

    @Autowired
    public void setAddressService(AddressService addressService){
        this.addressService = addressService;
    }

    @PostMapping("/address/")
    public TeamAddress createAddress(@RequestBody TeamAddress addressObject){
        return addressService.createTeamAddress(addressObject);
    }
    @PutMapping("/address/{addressId}/")
    public TeamAddress updateTeamAddress(@PathVariable(value = "addressId") Long id, @RequestBody TeamAddress teamAddressObject){
        return addressService.updateTeamAddress(id, teamAddressObject);
    }
    @GetMapping("/address/")
    public List<TeamAddress> getAllAddresses(){
        return addressService.getAllAddresses();
    }

}
