package com.soccer.service;

import com.soccer.exceptions.InformationExistException;
import com.soccer.exceptions.InformationNotFoundException;
import com.soccer.model.TeamAddress;
import com.soccer.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {
    private AddressRepository addressRepository;

    @Autowired
    public void setAddressRepository(AddressRepository addressRepository){
        this.addressRepository = addressRepository;
    }
    public TeamAddress createTeamAddress(TeamAddress teamAddress){
        //TeamAddress teamAddress2 = addressRepository.findByCityAndStreet(teamAddress.getCity(), teamAddress.getStreet());
        TeamAddress teamAddress2 = addressRepository.findByStreet(teamAddress.getStreet());
        if(teamAddress2 != null)
        {
            throw new InformationExistException("address with " + teamAddress2.getStreet()+ " already exists");
        }else{
            System.out.println("here is the zipcode : "+ teamAddress.getZipcode());
            return addressRepository.save(teamAddress);
        }
    }
    public TeamAddress updateTeamAddress(Long id, TeamAddress teamAddressObject){
        Optional<TeamAddress> teamAddress = addressRepository.findById(id);
        if(teamAddress.isEmpty()){
            throw new InformationNotFoundException("address id " + id + " not found");
        }else{
            teamAddress.get().setCity(teamAddressObject.getCity());
            teamAddress.get().setZipcode(teamAddressObject.getZipcode());
            teamAddress.get().setState(teamAddressObject.getState());
            teamAddress.get().setStreet(teamAddressObject.getStreet());
            return addressRepository.save(teamAddress.get());
        }

    }
    public List<TeamAddress> getAllAddresses(){
        List<TeamAddress> teamAddresses = addressRepository.findAll();
        if(teamAddresses.isEmpty()){
            throw new InformationNotFoundException("no addresses found in the table");
        }
        return teamAddresses;
    }
}
