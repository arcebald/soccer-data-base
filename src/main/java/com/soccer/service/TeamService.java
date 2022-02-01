package com.soccer.service;

import com.soccer.exceptions.InformationExistException;
import com.soccer.exceptions.InformationNotFoundException;
import com.soccer.model.League;
import com.soccer.model.Player;
import com.soccer.model.Team;
import com.soccer.model.TeamAddress;
import com.soccer.repository.AddressRepository;
import com.soccer.repository.LeagueRepository;
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
    private AddressRepository addressRepository;
    @Autowired
    public void setAddressRepository(AddressRepository addressRepository){
        this.addressRepository = addressRepository;
    }

    private PlayerRepository playerRepository;

    @Autowired
    public void setPlayerRepository(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }
    private LeagueRepository leagueRepository;
    @Autowired
    public void setLeagueRepository(LeagueRepository leagueRepository){
        this.leagueRepository = leagueRepository;
    }

    public Team getTeam(Long teamId) {
        Optional<Team> team = teamRepository.findById(teamId);
        if (team.isEmpty()) {
            throw new InformationNotFoundException("team with id " + teamId + " not found");
        } else {
            return team.get();
        }
    }


    public List<Team> getAllTeams() {

        List<Team> team = teamRepository.findAll();
        if (team.isEmpty()) {
            throw new InformationNotFoundException("no team found in the table");
        } else {
            return team;
        }
    }

    public Team createTeam(Team teamObject) {
        Team team = teamRepository.findByName(teamObject.getName());
        if (team != null) {
            throw new InformationExistException("team with name " + team.getName() + " already exists");
        } else {
            return teamRepository.save(teamObject);
        }
    }

    public String deleteTeam(Long teamId) {
        Team team = teamRepository.getById(teamId);
        if (team == null) {
            throw new InformationNotFoundException("team with id " + teamId + " not found");
        } else {
            teamRepository.deleteById(teamId);
            return "team with id " + teamId + " has been successfully deleted";
        }
    }

    public Player createTeamPlayer(Long teamId, Player playerObject) {
        Optional<Team> team = teamRepository.findById(teamId);

        if (team.isEmpty()) {
            throw new InformationNotFoundException("team with id " + teamId + " does not exist");
        }

        Player player = playerRepository.findByFirstName(playerObject.getFirstName());
        if (player != null) {
            throw new InformationExistException("player with firstName" + player.getFirstName() + " and team id " + teamId + " already exist");

        }
        playerObject.setTeam(team.get());

        return playerRepository.save(playerObject);
    }


    public Team updateTeam(Long teamId, Team teamObject) {
        Optional<Team> team = teamRepository.findById(teamId);
        if (team.isEmpty()) {
            throw new InformationNotFoundException("team with id " + teamId + " not found");
        } else {
            team.get().setName(teamObject.getName());
            team.get().setClubValue(teamObject.getClubValue());
            return teamRepository.save(team.get());
        }
    }


    public List<Player> getTeamPlayers(Long teamId) {
        Optional<Team> team = teamRepository.findById(teamId);
        if (team.isEmpty()) {
            throw new InformationNotFoundException("team with id " + teamId + " does not exist");
        } else {
            return team.get().getPlayersList();
        }
    }

    public Player getTeamPlayer(Long teamId, Long playerId) {
        Optional<Team> team = teamRepository.findById(teamId);
        if (team.isEmpty()) {
            throw new InformationNotFoundException("team with id " + teamId + " does not exist ");
        }
        Optional<Player> player = playerRepository.findByTeamId(teamId).stream().filter(p -> p.getId().equals(teamId)).findFirst();
        if (!player.isPresent()) {
            throw new InformationNotFoundException("player with id " + playerId + " does not exist");
        }
        return player.get();
    }

    public Player updateTeamPlayer(Long teamId, Long playerId, Player playerObject) {
        Optional<Team> team = teamRepository.findById(teamId);
        if (team.isEmpty()) {
            throw new InformationNotFoundException("team with id " + teamId + " does not exist");
        }
        Optional<Player> player = playerRepository.findById(playerId).stream().filter(p -> p.getId().equals(playerId)).findFirst();
        if (!player.isPresent()) {
            throw new InformationNotFoundException("player with id " + playerId + " does not exist");
        }
        Player oldPlayer = playerRepository.findByFirstName(playerObject.getFirstName());
        if (oldPlayer != null) {
            throw new InformationNotFoundException("player with name " + oldPlayer.getFirstName() + " already exists");
        }
        player.get().setFirstName(playerObject.getFirstName());
        player.get().setLastName(playerObject.getLastName());
        player.get().setPosition(playerObject.getPosition());
        player.get().setSalary(playerObject.getSalary());
        player.get().setDateOfBirth(playerObject.getDateOfBirth());
        return playerRepository.save(player.get());
    }

    public void deleteTeamPlayer(Long teamId, Long playerId) {
        Optional<Team> team = teamRepository.findById(teamId);
        if (team.isEmpty()) {
            throw new InformationNotFoundException("team with id " + teamId + " does not exist");
        }
        Optional<Player> player = playerRepository.findById(teamId).stream().filter(p -> p.getId().equals(playerId)).findFirst();
        if (!player.isPresent()) {
            throw new InformationNotFoundException(" player with id " + playerId + " does not exist");
        }
        playerRepository.deleteById(player.get().getId());
    }
    public TeamAddress createTeamAddress(TeamAddress teamAddress){
        TeamAddress teamAddress1 = addressRepository.findByStreet(teamAddress.getStreet());
        if(teamAddress1 != null){
            throw new InformationExistException("address with " + teamAddress1.getStreet()+ " already exists");
        }else{
            System.out.println("here is the zipcode : "+ teamAddress.getZipcode());
            return addressRepository.save(teamAddress);
        }
    }
    public TeamAddress updateTeamAddress(Long id, TeamAddress teamAddressObject){
        Optional<TeamAddress> teamAddress = addressRepository.findById(id);
        if(teamAddress.isEmpty()){
            throw new InformationNotFoundException("address id " + id + " not found");
        }else{
            teamAddress.get().setCity(teamAddressObject.getCity());
            teamAddress.get().setZipcode(teamAddressObject.getZipcode());
            teamAddress.get().setState(teamAddressObject.getState());
            teamAddress.get().setStreet(teamAddressObject.getStreet());
            return addressRepository.save(teamAddress.get());
        }

    }
    public List<TeamAddress> getAllAddresses(){
        List<TeamAddress> teamAddresses = addressRepository.findAll();
        if(teamAddresses.isEmpty()){
            throw new InformationNotFoundException("no addresses found in the table");
        }
        return teamAddresses;
    }
    public List<League> getAllLeagues(){
        List<League> leagues = leagueRepository.findAll();
        if(leagues.isEmpty()){
            throw new InformationNotFoundException("no leagues found in the table");
        }
        return leagues;
    }

}
