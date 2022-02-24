package fontys.sem3.individual_track.controller;

import fontys.sem3.individual_track.business.TeamsService;
import fontys.sem3.individual_track.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/teams")
public class TeamsController {

    private final TeamsService teamsService;

    @Autowired
    public TeamsController(TeamsService teamsService) {
        this.teamsService = teamsService;
    }

    @GetMapping
    public ResponseEntity<List<Team>> getAllTeams() {
        List<Team> teamList = this.teamsService.getAllTeams();

        if (teamList != null) {
            return ResponseEntity.ok().body(teamList);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("{teamId}")
    public ResponseEntity<Team> getTeamById(@PathVariable(value = "teamId") long teamId) {
        Team team = this.teamsService.getTeam(teamId);

        if (team != null) {
            return ResponseEntity.ok().body(team);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Team> addTeam(@RequestBody Team team) {
        if (!this.teamsService.addTeam(team)) {
            String entity = "A team with this id(" + team.getTeamId() + ") already exists";
            return new ResponseEntity(entity, HttpStatus.CONFLICT);
        } else {
            String url = "teams/" + team.getTeamId();
            URI uri = URI.create(url);
            return new ResponseEntity(uri, HttpStatus.CREATED);
        }
    }
}
