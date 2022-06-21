package com.maplr.testhockeygame.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Entity
public class Player implements Serializable {

    @Id
    private long number;
    private String name;
    private String lastname;
    private String position;
    private boolean captain;

}
