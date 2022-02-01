package com.soccer.service;

import com.soccer.exceptions.InformationExistException;
import com.soccer.exceptions.InformationNotFoundException;
import com.soccer.model.League;
import com.soccer.repository.LeagueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LeagueService {
    private LeagueRepository leagueRepository;
    @Autowired
    public void setLeagueRepository(LeagueRepository leagueRepository){
        this.leagueRepository = leagueRepository;
    }

    public List<League> getAllLeagues(){
        List<League> leagues = leagueRepository.findAll();
        if(leagues.isEmpty()){
            throw new InformationNotFoundException("no leagues found in the table");
        }
        return leagues;
    }
    public String deleteLeague(Long leagueId){
        Optional<League> league = leagueRepository.findById(leagueId);
        if(league.isEmpty()){
            throw new InformationNotFoundException("league with id " + leagueId + " does not exist");
        }else{
            leagueRepository.deleteById(leagueId);
            return "league with id " + leagueId + " has been successfully deleted";
        }
    }
    public League createLeague(League leagueObject){
        League league = leagueRepository.findByName(leagueObject.getName());
        if(league != null){
            throw new InformationExistException(" league with name" + leagueObject.getName() + " already exists");
        }
        else{
            return leagueRepository.save(leagueObject);
        }
    }
    public League updateLeague(Long leagueId, League leagueObject){
        Optional<League> league = leagueRepository.findById(leagueId);
        if(league.isEmpty()){
            throw new InformationNotFoundException("league with id " + leagueId + " not found");
        }else{
            league.get().setName(leagueObject.getName());
            league.get().setYear(leagueObject.getYear());
            return leagueRepository.save(leagueObject);
        }

    }

}
