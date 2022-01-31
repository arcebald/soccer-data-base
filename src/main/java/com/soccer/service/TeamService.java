package com.soccer.service;

import com.soccer.exceptions.InformationExistException;
import com.soccer.exceptions.InformationNotFoundException;
import com.soccer.model.Team;
import com.soccer.repository.PlayerRepository;
import com.soccer.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Service
public class TeamService {
    private TeamRepository teamRepository;

    @Autowired
    public void setTeamRepository(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    private PlayerRepository playerRepository;

    @Autowired
    public void setPlayerRepository(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public Team getTeam(Long teamId) {
        Optional<Team> team = teamRepository.findById(teamId);
        if (team.isEmpty()) {
            throw new InformationNotFoundException("team with id " + teamId + " not found");
        } else {
            return team.get();
        }
    }


     public List<Team> getAllTeams(){

        List<Team> team = teamRepository.findAll();
        if(team.isEmpty()){
            throw new InformationNotFoundException("no team found in the table");
        }else {return team;}
     }

    public Team createTeam(Team teamObject){
        Team team = teamRepository.findByName(teamObject.getName());
        if(team != null){
            throw new InformationExistException("team with name " + team.getName() + " already exists");
        }else {
            return teamRepository.save(teamObject);
        }
    }
}
