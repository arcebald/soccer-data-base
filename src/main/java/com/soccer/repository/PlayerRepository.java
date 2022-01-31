package com.soccer.repository;

import com.soccer.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    List<Player> findByTeamId(Long teamId);

    List<Player> findByPlayerId(Long playerId);

    Player findByNameAndIsNot(String firstName, Long playerId);
}
