package com.maplr.testhockeygame.repository;

import com.maplr.testhockeygame.entities.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TeamRepository extends JpaRepository<Team, Long> {

    @Query("SELECT T FROM Team T WHERE T.year = ?1")
    Team getTeamByYear(int year);
}
