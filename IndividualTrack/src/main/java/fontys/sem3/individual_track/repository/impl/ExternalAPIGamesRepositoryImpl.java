package fontys.sem3.individual_track.repository.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fontys.sem3.individual_track.repository.ExternalAPIGamesRepository;
import fontys.sem3.individual_track.repository.entity.Game;
import fontys.sem3.individual_track.repository.entity.Team;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import java.util.List;

@Repository
public class ExternalAPIGamesRepositoryImpl implements ExternalAPIGamesRepository {
    private final RestTemplate restTemplate;

    public ExternalAPIGamesRepositoryImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Game> fetchGamesFromAPI() throws JsonProcessingException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        String currDate = dtf.format(now);

        String externalURL = "https://www.balldontlie.io/api/v1/games?" + "start_date=" + currDate;

        String gamesResponse = this.restTemplate.getForObject(externalURL, String.class);
        JSONObject jsonObject = new JSONObject(gamesResponse);
        JSONArray jsonArray = jsonObject.getJSONArray("data");
        List<Game> gameList = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
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

        return gameList;
    }
}
