package fontys.sem3.individual_track.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import fontys.sem3.individual_track.business.TeamsService;
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

@RestController
@CrossOrigin(origins ="*", allowedHeaders = "*")
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
    public ResponseEntity<List<TeamDTO>> getAllTeams() throws JsonProcessingException {
        String externalURL = "https://www.balldontlie.io/api/v1/teams";

        String teamsResponse = this.restTemplate.getForObject(externalURL, String.class);
        JSONObject jsonObject = new JSONObject(teamsResponse);
        JSONArray jsonArray = jsonObject.getJSONArray("data");

        List<TeamDTO> teamList = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++){
            long id = jsonArray.getJSONObject(i).getLong("id");
            String abbreviation = jsonArray.getJSONObject(i).getString("abbreviation");
            String city = jsonArray.getJSONObject(i).getString("city");
            String conference = jsonArray.getJSONObject(i).getString("conference");
            String division = jsonArray.getJSONObject(i).getString("division");
            String fullName = jsonArray.getJSONObject(i).getString("full_name");
            String name = jsonArray.getJSONObject(i).getString("name");

            TeamDTO team = new TeamDTO(id, abbreviation, city, conference,
                    division, fullName, name);
            teamList.add(team);

        }

        if (!teamList.isEmpty()) {
            return ResponseEntity.ok().body(teamList);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("{teamId}")
    public ResponseEntity<TeamDTO> getTeamById(@PathVariable(value = "teamId") long teamId) {
        TeamDTO team = this.teamsService.getTeam(teamId);

        if (team != null) {
            return ResponseEntity.ok().body(team);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<TeamDTO> addTeam(@RequestBody TeamDTO team) {
        if (!this.teamsService.addTeam(team)) {
            String entity = "A team with this id(" + team.getId() + ") already exists";
            return new ResponseEntity(entity, HttpStatus.CONFLICT);
        } else {
            String url = "teams/" + team.getId();
            URI uri = URI.create(url);
            return new ResponseEntity(uri, HttpStatus.CREATED);
        }
    }
}
