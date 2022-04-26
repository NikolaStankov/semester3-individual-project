package fontys.sem3.individual_track.controller;

import fontys.sem3.individual_track.business.PlayersService;
import fontys.sem3.individual_track.model.CreatePlayerRequestDTO;
import fontys.sem3.individual_track.model.CreatePlayerResponseDTO;
import fontys.sem3.individual_track.model.PlayerDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/players")
@RequiredArgsConstructor
public class PlayersController {

    private final PlayersService playersService;

    @GetMapping
    public ResponseEntity<List<PlayerDTO>> getAllPlayers() {
        List<PlayerDTO> playerList = this.playersService.getAllPlayers();

        if (!playerList.isEmpty()) {
            return ResponseEntity.ok().body(playerList);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<CreatePlayerResponseDTO> addPlayer(@RequestBody @Valid CreatePlayerRequestDTO playerRequest) {
        CreatePlayerResponseDTO playerResponse = this.playersService.createPlayer(playerRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(playerResponse);
    }
}
