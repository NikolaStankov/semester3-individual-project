package fontys.sem3.individual_track.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import fontys.sem3.individual_track.business.GamesService;
import fontys.sem3.individual_track.model.GameDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/games")
public class GamesController {
    private final GamesService gamesService;

    @Autowired
    public GamesController(GamesService gamesService) {
        this.gamesService = gamesService;
    }

    @GetMapping
    public ResponseEntity<List<GameDTO>> getAllGames() throws JsonProcessingException {
        List<GameDTO> gameList = this.gamesService.getAllGames();

        if (!gameList.isEmpty()) {
            return ResponseEntity.ok().body(gameList);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("{gameId}")
    public ResponseEntity<GameDTO> getGameById(@PathVariable(value = "gameId") long gameId) {
        GameDTO game = this.gamesService.getGame(gameId);

        if (game != null) {
            return ResponseEntity.ok().body(game);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
