package com.soccer.controller;
import com.soccer.model.Player;
import com.soccer.model.Team;
import com.soccer.model.TeamAddress;
import com.soccer.service.AddressService;
import com.soccer.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class TeamController {

    private TeamService teamService;

    @Autowired
    public void setTeamRepository(TeamService teamService){
        this.teamService = teamService;
    }


    @GetMapping(path = "/hello-world/")
    public String getHelloWorld(){
        return "Hello World";
    }
    @GetMapping("/teams/")
    public List<Team> getAllTeams(){
        return teamService.getAllTeams();
    }
    @GetMapping("/teams/{teamId}/")
    public Team getTeam(@PathVariable Long teamId){
        return teamService.getTeam(teamId);
    }

    @PostMapping("/leagues/{leagueId}/address/{addressId}/teams/")
    public Team createTeam(@RequestBody Team teamObject, @PathVariable(value = "leagueId") Long leagueId, @PathVariable(value = "addressId") Long addressId) {
        return teamService.createTeam(teamObject, leagueId, addressId);
    }
    @PutMapping("/address/{teamAddressId}/teams/{teamId}/")
    public Team updateTeam(@PathVariable(value = "teamId") Long teamId, @RequestBody Team teamObject, @PathVariable(value = "teamAddressId") TeamAddress teamAddress){
        return teamService.updateTeam(teamId, teamObject, teamAddress);
    }

    @DeleteMapping("/teams/{teamId}/")
    public String deleteTeam(@PathVariable(value = "teamId") Long teamId) {
        return teamService.deleteTeam(teamId);
    }
    @PostMapping("/teams/{teamId}/players/")
    public Player createTeamPlayer(@PathVariable(value = "teamId") Long teamId, @RequestBody Player playerObject){
        return teamService.createTeamPlayer(teamId, playerObject);
    }

    @GetMapping("/teams/{teamId}/players/")
    public List<Player> getTeamPlayers(@PathVariable(value = "teamId") Long teamId){
        return teamService.getTeamPlayers(teamId);
    }

    @GetMapping("/teams/{teamId}/players/{playerId}/")
    public Player getTeamPlayer(@PathVariable(value = "teamId") Long teamId, @PathVariable(value = "playerId") Long playerId){
        return teamService.getTeamPlayer(teamId, playerId);
    }

    @PutMapping("/teams/{teamId}/players/{playerId}")
    public Player updateTeamPlayer(@PathVariable(value = "teamId") Long teamId,
                                   @PathVariable(value = "playerId") Long playerId,
                                   @RequestBody Player playerObject){
        return teamService.updateTeamPlayer(teamId, playerId, playerObject);
    }
    @DeleteMapping("/teams/{teamId}/players/{playerId}/")
    public void deleteTeamPlayer(@PathVariable(value = "teamId") Long teamId,
                                 @PathVariable(value = "playerId") Long playerId)
    {
        teamService.deleteTeamPlayer(teamId, playerId);
    }



}
