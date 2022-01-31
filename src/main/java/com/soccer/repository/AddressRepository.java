package com.soccer.repository;

import com.soccer.model.TeamAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<TeamAddress, Long> {
}
