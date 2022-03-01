package com.soccer.controller;

import com.soccer.model.League;
import com.soccer.service.LeagueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class LeagueController {

    private LeagueService leagueService;
    @Autowired
    public void setLeagueService(LeagueService leagueService){
        this.leagueService = leagueService;
    }

    @GetMapping("/leagues/")
    public List<League> getAllLeagues(){
        return leagueService.getAllLeagues();
    }

    @DeleteMapping("/leagues/{leagueId}/")
    public String deleteLeague(@PathVariable(value = "leagueId") Long leagueId){
        return leagueService.deleteLeague(leagueId);
    }
    @PostMapping("/leagues/")
    public League createLeague(@RequestBody League leagueObject){
        return leagueService.createLeague(leagueObject);
    }
    @PutMapping("/leagues/{leagueId}")
    public League updateLeague(@PathVariable(value = "leagueId") Long leagueId, @RequestBody League leagueObject){
        return leagueService.updateLeague(leagueId, leagueObject);
    }
}
