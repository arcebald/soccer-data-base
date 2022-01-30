package com.soccer.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "teams") //create a new table called teams
public class Team {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY) //equivalent with Serial in SQL
    private Long id;

    @Column
    private String name;

    @Column
    private Integer clubValue;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private TeamAddress teamAddress;

    @OneToMany(mappedBy = "team", orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Player> playersList;

    @ManyToOne
    @JoinColumn(name = "league_id")
    @JsonIgnore
    private League league;

    public Team() {
    }

    public Team(Long id, String name, Integer clubValue) {
        this.id = id;
        this.name = name;
        this.clubValue = clubValue;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getClubValue() {
        return clubValue;
    }

    public void setClubValue(Integer clubValue) {
        this.clubValue = clubValue;
    }

    public TeamAddress getTeamAddress() {
        return teamAddress;
    }

    public void setTeamAddress(TeamAddress teamAddress) {
        this.teamAddress = teamAddress;
    }

    public List<Player> getPlayersList() {
        return playersList;
    }

    public void setPlayersList(List<Player> playersList) {
        this.playersList = playersList;
    }

    public League getLeague() {
        return league;
    }

    public void setLeague(League league) {
        this.league = league;
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", clubValue=" + clubValue +
                ", teamAddress=" + teamAddress +
                ", playersList=" + playersList +
                ", league=" + league +
                '}';
    }
}
