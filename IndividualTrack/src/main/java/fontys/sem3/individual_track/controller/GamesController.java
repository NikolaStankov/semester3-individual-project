package fontys.sem3.individual_track.controller;

import fontys.sem3.individual_track.business.GamesService;
import fontys.sem3.individual_track.model.CreateGameRequestDTO;
import fontys.sem3.individual_track.model.CreateGameResponseDTO;
import fontys.sem3.individual_track.model.GameDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/games")
@RequiredArgsConstructor
public class GamesController {
    private final GamesService gamesService;

    @GetMapping
    public ResponseEntity<List<GameDTO>> getAllGames() {
        List<GameDTO> gameList = this.gamesService.getAllGames();

        if (!gameList.isEmpty()) {
            return ResponseEntity.ok().body(gameList);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("{gameId}")
    public ResponseEntity<GameDTO> getGameById(@PathVariable(value = "gameId") long gameId) {
        Optional<GameDTO> optionalGameDTO = this.gamesService.getGame(gameId);

        if (optionalGameDTO.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(optionalGameDTO.get());
        }
    }

    @PostMapping
    public ResponseEntity<CreateGameResponseDTO> addGame(@RequestBody @Valid CreateGameRequestDTO gameRequest) {
        CreateGameResponseDTO gameResponse = this.gamesService.createGame(gameRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(gameResponse);
    }
}
