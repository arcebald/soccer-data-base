package com.soccer.repository;

import com.soccer.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    List<Player> findByTeamId(Long playerId);

    Player findByFirstName(String firstName);

    Player findByLastName(String lastName);
}
