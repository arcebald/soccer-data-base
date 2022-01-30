package com.soccer.model;

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

}
