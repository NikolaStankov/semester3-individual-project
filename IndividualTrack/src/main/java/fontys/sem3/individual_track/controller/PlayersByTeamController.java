package fontys.sem3.individual_track.controller;

import fontys.sem3.individual_track.business.PlayersService;
import fontys.sem3.individual_track.business.TeamsService;
import fontys.sem3.individual_track.business.exception.InvalidTeamIdException;
import fontys.sem3.individual_track.model.PlayerDTO;
import fontys.sem3.individual_track.model.TeamDTO;
import fontys.sem3.individual_track.repository.entity.Team;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/teams/{teamId}/players")
@RequiredArgsConstructor
public class PlayersByTeamController {

    private final TeamsService teamsService;
    private final PlayersService playersService;

    @GetMapping
    public List<PlayerDTO> getPlayersByTeam(@PathVariable(value = "teamId") long teamId) {
        Optional<TeamDTO> optionalTeam = this.teamsService.getTeam(teamId);

        if (optionalTeam.isEmpty()) {
            throw new InvalidTeamIdException();
        }

        TeamDTO teamDTO = optionalTeam.get();
        Team teamToQueryOn = Team.builder()
                .id(teamDTO.getId())
                .abbreviation(teamDTO.getAbbreviation())
                .city(teamDTO.getCity())
                .conference(teamDTO.getConference())
                .division(teamDTO.getDivision())
                .fullName(teamDTO.getFullName())
                .name(teamDTO.getName())
                .build();

        return this.playersService.getPlayersByTeam(teamToQueryOn);
    }
}
