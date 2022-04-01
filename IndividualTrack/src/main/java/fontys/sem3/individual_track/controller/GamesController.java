package fontys.sem3.individual_track.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fontys.sem3.individual_track.business.GamesService;
import fontys.sem3.individual_track.model.Game;
import fontys.sem3.individual_track.model.Team;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins ="*", allowedHeaders = "*")
@RequestMapping("/games")
public class GamesController {
    private final GamesService gamesService;
    private final RestTemplate restTemplate;


    @Autowired
    public GamesController(GamesService gamesService,
                           RestTemplate restTemplate){
        this.gamesService = gamesService;
        this.restTemplate = restTemplate;
    }

    @GetMapping
    public ResponseEntity<List<Game>> getAllGames() throws JsonProcessingException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("YYYY-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        String currDate = dtf.format(now);

        String externalURL = "https://www.balldontlie.io/api/v1/games?"+"start_date="+currDate;
        System.out.println(externalURL);

        String gamesResponse = this.restTemplate.getForObject(externalURL, String.class);
        JSONObject jsonObject = new JSONObject(gamesResponse);
        JSONArray jsonArray = jsonObject.getJSONArray("data");
        List<Game> gameList = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++){
            long id = jsonArray.getJSONObject(i).getLong("id");
            String date = jsonArray.getJSONObject(i).getString("date");
            int season = jsonArray.getJSONObject(i).getInt("season");

            String homeTeamData = jsonArray.getJSONObject(i).getJSONObject("home_team").toString();
            String visitorTeamData = jsonArray.getJSONObject(i).getJSONObject("visitor_team").toString();

            ObjectMapper objectMapper = new ObjectMapper();

            Team homeTeam = objectMapper.readValue(homeTeamData, Team.class);
            Team visitorTeam = objectMapper.readValue(visitorTeamData, Team.class);

            Game game = new Game(id, date, season, homeTeam, visitorTeam);

            gameList.add(game);
        }

        if (!gameList.isEmpty()) {
            return ResponseEntity.ok().body(gameList);
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
