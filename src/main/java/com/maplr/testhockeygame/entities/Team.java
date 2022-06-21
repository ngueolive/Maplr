package com.maplr.testhockeygame.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
public class Team implements Serializable {

    @Id
    private long id;
    private String coach;
    private int year;
    @OneToMany
    private List<Player> players;
}
