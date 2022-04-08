package fontys.sem3.individual_track.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import fontys.sem3.individual_track.business.TeamsService;
import fontys.sem3.individual_track.model.GameDTO;
import fontys.sem3.individual_track.model.TeamDTO;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/teams")
public class TeamsController {

    private final TeamsService teamsService;
    private final RestTemplate restTemplate;

    @Autowired
    public TeamsController(TeamsService teamsService, RestTemplate restTemplate) {
        this.teamsService = teamsService;
        this.restTemplate = restTemplate;
    }

    @GetMapping
    public ResponseEntity<List<TeamDTO>> getAllTeams() {
        List<TeamDTO> teamList = this.teamsService.getAllTeams();

        if (!teamList.isEmpty()) {
            return ResponseEntity.ok().body(teamList);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("{teamId}")
    public ResponseEntity<TeamDTO> getTeamById(@PathVariable(value = "teamId") long teamId) {
        Optional<TeamDTO> optionalTeamDTO = this.teamsService.getTeam(teamId);

        if (optionalTeamDTO.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(optionalTeamDTO.get());
        }
    }
}
