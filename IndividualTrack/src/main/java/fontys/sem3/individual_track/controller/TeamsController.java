package fontys.sem3.individual_track.controller;

import fontys.sem3.individual_track.business.TeamsService;
import fontys.sem3.individual_track.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/teams")
@RequiredArgsConstructor
public class TeamsController {

    private final TeamsService teamsService;

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

    @PostMapping
    public ResponseEntity<CreateTeamResponseDTO> createTeam(@RequestBody @Valid CreateTeamRequestDTO teamRequest) {
        CreateTeamResponseDTO teamResponse = this.teamsService.createTeam(teamRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(teamResponse);
    }
}
