package fontys.sem3.individual_track.controller;

import fontys.sem3.individual_track.business.GamesService;
import fontys.sem3.individual_track.model.GameDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/teams/{teamId}/events")
@RequiredArgsConstructor
public class TeamEventsController {
    private final GamesService gamesService;

    @GetMapping
    public List<GameDTO> getAllGamesByTeamId(@PathVariable(value = "teamId") long teamId) {
        return this.gamesService.getGamesByTeamId(teamId);
    }
}
