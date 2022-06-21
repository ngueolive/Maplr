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
        if (teamByYear == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(teamByYear, HttpStatus.OK);
    }

    @PostMapping(value = "/team/{year}", consumes = "application/json")
    public ResponseEntity<Player> addTeamMember(@RequestBody Player player, @PathVariable int year) {

        ResponseEntity<Player> playerCreated = new ResponseEntity<>(playerRepository.save(player), HttpStatus.CREATED);

        Team teamByYear = teamRepository.getTeamByYear(year);
        teamByYear.getPlayers().add(playerCreated.getBody());
        teamRepository.save(teamByYear);

        return playerCreated;
    }

    @PutMapping(value = "/player/captain/{id}")
    public ResponseEntity<Player> updateCaptain(@PathVariable long id) {
        Player currentCaptain = playerRepository.findAll().stream().filter(Player::isCaptain).findFirst().get();
        if (currentCaptain != null) {
            currentCaptain.setCaptain(false);
            playerRepository.save(currentCaptain);
        }

        Player newCaptain = playerRepository.getById(id);
        newCaptain.setCaptain(true);

        return new ResponseEntity<>(playerRepository.save(newCaptain), HttpStatus.OK);
    }
}


