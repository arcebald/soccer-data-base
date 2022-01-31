package com.soccer.repository;

import com.soccer.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {
//    Player findByTeamIdAndPlayerLastName(Long id, String lastName);
}
