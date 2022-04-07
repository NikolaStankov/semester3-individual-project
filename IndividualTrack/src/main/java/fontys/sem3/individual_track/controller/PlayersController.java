package fontys.sem3.individual_track.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import fontys.sem3.individual_track.business.PlayersService;
import fontys.sem3.individual_track.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/players")
public class PlayersController {
    private final PlayersService playersService;

    @Autowired
    public PlayersController(PlayersService playersService) {
        this.playersService = playersService;
    }

    @GetMapping
    public ResponseEntity<List<Player>> getAllPlayers() throws JsonProcessingException {
        List<Player> playerList = this.playersService.getAllPlayers();

        if (!playerList.isEmpty()) {
            return ResponseEntity.ok().body(playerList);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
