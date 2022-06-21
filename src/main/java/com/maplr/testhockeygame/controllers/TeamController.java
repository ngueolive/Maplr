package com.maplr.testhockeygame.controllers;

import com.maplr.testhockeygame.entities.Player;
import com.maplr.testhockeygame.entities.Team;
import com.maplr.testhockeygame.repository.PlayerRepository;
import com.maplr.testhockeygame.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Transactional
@RequestMapping(value = "/api")
public class TeamController {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @GetMapping(value = "/team/{year}")
    public ResponseEntity<Team> getTeamByYear(@PathVariable int year) {

        Team teamByYear = teamRepository.getTeamByYear(year);
        ResponseEntity<Team> team = new ResponseEntity<>(teamByYear, HttpStatus.OK);
        return team;
    }

    @PostMapping(value = "/team/{year}", consumes = "application/json")
    public ResponseEntity<Player> addTeamMember(@RequestBody Player player, @PathVariable int year) {

        Player newPlayer = playerRepository.save(player);
        ResponseEntity<Player> playerCreated = new ResponseEntity<>(newPlayer, HttpStatus.CREATED);

        List<Team> allTeams = teamRepository.findAll();
        Team teamByYear = allTeams.stream().filter(t -> t.getYear() == (year)).findFirst().get();
        teamByYear.getPlayers().add(newPlayer);
        teamRepository.save(teamByYear);

        return playerCreated;
    }

    @PutMapping(value = "/player/captain/{id}")
    public ResponseEntity<Player> updateCaptain(@PathVariable long id) {
        Player currentCaptain = playerRepository.findAll().stream().filter(Player::isCaptain).findFirst().orElse(new Player());
        currentCaptain.setCaptain(false);
        playerRepository.save(currentCaptain);

        Player newCaptain = playerRepository.getById(id);
        newCaptain.setCaptain(true);

        ResponseEntity<Player> player = new ResponseEntity<>(playerRepository.save(newCaptain), HttpStatus.OK);
        return player;
    }
}


