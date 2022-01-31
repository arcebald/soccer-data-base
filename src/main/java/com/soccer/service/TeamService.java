package com.soccer.service;

import com.soccer.exceptions.InformationExistException;
import com.soccer.exceptions.InformationNotFoundException;
import com.soccer.model.Player;
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

        public String deleteTeam(Long teamId){
        Team team = teamRepository.getById(teamId);
        if(team == null){
            throw new InformationNotFoundException("team with id " + teamId + " not found");
        }else {
            teamRepository.deleteById(teamId);
            return "team with id " + teamId + " has been successfully deleted";
        }
        }
//    public Recipe createCategoryRecipe(Long categoryId, Recipe recipeObject) {
//        System.out.println("service calling createCategoryRecipe ==>");
//        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication()
//                .getPrincipal();
//        Category category = categoryRepository.findByIdAndUserId(categoryId, userDetails.getUser().getId());
//        if (category == null) {
//            throw new InformationNotFoundException(
//                    "category with id " + categoryId + " not belongs to this user or category does not exist");
//        }
//        Recipe recipe = recipeRepository.findByNameAndUserId(recipeObject.getName(), userDetails.getUser().getId());
//        if (recipe != null) {
//            throw new InformationExistException("recipe with name " + recipe.getName() + " already exists");
//        }
//        recipeObject.setUser(userDetails.getUser());
//        recipeObject.setCategory(category);
//        return recipeRepository.save(recipeObject);
//    }
//    public Player createTeamPlayer(Long teamId, Player playerObject){
//        Optional<Team> team = teamRepository.findById(teamId);
//
//        if(team.isEmpty()){
//            throw new InformationNotFoundException("team with id " + teamId + " does not exist");
//        }
//
//        Player player = playerRepository.findByTeamIdAndPlayerLastName(teamId, playerObject.getLastName());
//        if(player != null){
//            throw new InformationExistException("player with lastName" + player.getLastName() + " and team id " +teamId+ " already exist");
//
//        }
//        playerObject.setTeam(team.get());
//
//        return playerRepository.save(playerObject);
//    }

//public Category updateCategory(Long categoryId, Category categoryObject){
//    MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication()
//            .getPrincipal();
//    Category category = categoryRepository.findByIdAndUserId(categoryId, userDetails.getUser().getId());
//    if (category == null) {
//        throw new InformationNotFoundException("category with id " + categoryId + " not found");
//    } else {
//        category.setDescription(categoryObject.getDescription());
//        category.setName(categoryObject.getName());
//        category.setUser(userDetails.getUser());
//        return categoryRepository.save(category);
//    }

//}

    public Team updateTeam(Long teamId, Team teamObject){
        Optional<Team> team = teamRepository.findById(teamId);
        if(team.isEmpty()){
            throw new InformationNotFoundException("team with id " + teamId + " not found");
        }else{
            team.get().setName(teamObject.getName());
            team.get().setClubValue(teamObject.getClubValue());
            return teamRepository.save(team.get());
        }
    }


    public List<Player> getTeamPlayers(Long teamId){
        Optional<Team> team = teamRepository.findById(teamId);
        if(team.isEmpty()){
            throw new InformationNotFoundException("team with id " + teamId + " does not exist");
        }else{
            return team.get().getPlayersList();
        }
    }

    public Player getTeamPlayer(Long teamId, Long playerId){
        Optional<Team> team = teamRepository.findById(teamId);
        if(team.isEmpty()){
            throw new InformationNotFoundException("team with id " +teamId+ " does not exist ");
        }
        Optional<Player> player = playerRepository.findByTeamId(teamId).stream().filter(p -> p.getId().equals(teamId)).findFirst();
        if (!player.isPresent()){
            throw new InformationNotFoundException("player with id " + playerId + " does not exist");
        }
        return player.get();
    }

//    public Player updateTeamPlayer(Long teamId, Long playerId, Player playerObject){
//        Optional<Team> team = teamRepository.findById(teamId);
//        if(team.isEmpty()){
//            throw new InformationNotFoundException("team with id " + teamId+ " does not exist");
//        }
//        Optional<Player> player = playerRepository.findByPlayerId(playerId).stream().filter(p -> p.getId().equals(playerId)).findFirst();
//        if(!player.isPresent()){
//            throw new InformationNotFoundException("player with id " + playerId + " does not exist");
//        }
//        Player oldPlayer = playerRepository.findByNameAndIsNot(playerObject.getFirstName(), playerId);
//        if(oldPlayer != null){
//            throw new InformationNotFoundException("player with name " + oldPlayer.getFirstName()+ " already exists");
//        }
//        player.get().setFirstName(playerObject.getFirstName());
//        player.get().setLastName(playerObject.getLastName());
//        player.get().setPosition(playerObject.getPosition());
//        player.get().setSalary(playerObject.getSalary());
//        player.get().setDateOfBirth(playerObject.getDateOfBirth());
//        return playerRepository.save(player.get());
//    }
    public void deleteTeamPlayer(Long teamId, Long playerId){
        Optional<Team> team = teamRepository.findById(teamId);
        if(team.isEmpty()){
            throw new InformationNotFoundException("team with id " + teamId+ " does not exist");
        }
        Optional<Player> player = playerRepository.findByTeamId(teamId).stream().filter(p -> p.getId().equals(playerId)).findFirst();
        if(!player.isPresent()){
            throw new InformationNotFoundException(" player with id " + playerId + " does not exist");
        }
        playerRepository.deleteById(player.get().getId());
    }

}
