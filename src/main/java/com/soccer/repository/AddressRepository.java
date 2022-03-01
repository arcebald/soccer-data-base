package com.soccer.repository;

import com.soccer.model.TeamAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface AddressRepository extends JpaRepository<TeamAddress, Long> {
    TeamAddress findByStreet(String street);

}
