package fontys.sem3.individual_track.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import fontys.sem3.individual_track.business.GamesService;
import fontys.sem3.individual_track.model.Game;
import fontys.sem3.individual_track.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin(origins ="*", allowedHeaders = "*")
@RequestMapping("/games")
public class GamesController {
    private final GamesService gamesService;
    private final RestTemplate restTemplate;
    private String externalURL = "https://www.balldontlie.io/api/v1/games";

    @Autowired
    public GamesController(GamesService gamesService,
                           RestTemplate restTemplate){
        this.gamesService = gamesService;
        this.restTemplate = restTemplate;
    }

    @GetMapping
    public ResponseEntity<List<Game>> getAllGames() {

        String gamesResponse = this.restTemplate.getForObject(externalURL, String.class);

        ObjectMapper mapper = new ObjectMapper();
        List<Game> gamesList = new ArrayList<>();

        try {
            gamesList = mapper.readValue(gamesResponse, new TypeReference<List<Game>>(){});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }

        if (gamesList != null) {
            return ResponseEntity.ok().body(gamesList);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("{gameId}")
    public ResponseEntity<Game> getGameById(@PathVariable(value = "gameId") long gameId) {
        Game game = this.gamesService.getGame(gameId);

        if (game != null) {
            return ResponseEntity.ok().body(game);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
