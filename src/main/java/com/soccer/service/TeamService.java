package com.soccer.service;

import com.soccer.repository.PlayerRepository;
import com.soccer.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamService {
    private TeamRepository teamRepository;

    @Autowired
    public void setTeamRepository(TeamRepository teamRepository){
        this.teamRepository = teamRepository;
    }

    private PlayerRepository playerRepository;
    @Autowired
    public  void setPlayerRepository(PlayerRepository playerRepository){
        this.playerRepository = playerRepository;
    }
}
