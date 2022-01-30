package com.soccer.model;

import javax.persistence.*;

@Entity
@Table(name = "players") //create a new table called players
public class Player {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String dateOfBirth;

    @Column
    private String position;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;


}
