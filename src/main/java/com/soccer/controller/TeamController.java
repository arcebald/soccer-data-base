package com.soccer.controller;

import com.soccer.model.Player;
import com.soccer.model.Team;
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

    @PostMapping("/teams/")
    public Team createTeam(@RequestBody Team teamObject) {
        return teamService.createTeam(teamObject);
    }
    @PutMapping("/teams/{teamId}/")
    public Team updateTeam(@PathVariable(value = "teamId") Long teamId, @RequestBody Team teamObject){
        return teamService.updateTeam(teamId, teamObject);
    }

    @DeleteMapping("/teams/{teamId}/")
    public String deleteTeam(@PathVariable(value = "teamId") Long teamId) {
        return teamService.deleteTeam(teamId);
    }
//    @PostMapping("/teams/{teamId}/players/")
//    public Player createTeamPlayer(@PathVariable(value = "teamId") Long teamId, @RequestBody Player playerObject){
//        return teamService.createTeamPlayer(teamId, playerObject);
//    }
//@PutMapping("/categories/{categoryId}/")
//public Category updateCategory(@PathVariable(value = "categoryId") Long categoryId, @RequestBody Category categoryObject) {
//    return categoryService.updateCategory(categoryId, categoryObject);
//}


}
