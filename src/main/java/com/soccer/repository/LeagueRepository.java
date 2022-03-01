package com.soccer.repository;

import com.soccer.model.League;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface LeagueRepository extends JpaRepository<League, Long> {
    League findByName(String name);
}
