package com.soccer.repository;

import com.soccer.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    List<Player> findByTeamId(Long teamId);
//    Player findByTeamIdAndPlayerLastName(Long id, String lastName);
}
