package fontys.sem3.individual_track.repository.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fontys.sem3.individual_track.model.GameDTO;
import fontys.sem3.individual_track.model.TeamDTO;
import fontys.sem3.individual_track.repository.GamesRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Repository
@Primary
public class NBAGamesRepository implements GamesRepository {
    private final RestTemplate restTemplate;
    private final List<GameDTO> gameList;

    @Autowired
    public NBAGamesRepository(RestTemplate restTemplate) throws JsonProcessingException {
        this.restTemplate = restTemplate;
        this.gameList = fetchGamesFromAPI();
    }

    private List<GameDTO> fetchGamesFromAPI() throws JsonProcessingException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        String currDate = dtf.format(now);

        String externalURL = "https://www.balldontlie.io/api/v1/games?"+"start_date="+currDate;

        String gamesResponse = this.restTemplate.getForObject(externalURL, String.class);
        JSONObject jsonObject = new JSONObject(gamesResponse);
        JSONArray jsonArray = jsonObject.getJSONArray("data");
        List<GameDTO> gameList = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++){
            long id = jsonArray.getJSONObject(i).getLong("id");
            String date = jsonArray.getJSONObject(i).getString("date");
            int season = jsonArray.getJSONObject(i).getInt("season");

            String homeTeamData = jsonArray.getJSONObject(i).getJSONObject("home_team").toString();
            String visitorTeamData = jsonArray.getJSONObject(i).getJSONObject("visitor_team").toString();

            ObjectMapper objectMapper = new ObjectMapper();

            TeamDTO homeTeam = objectMapper.readValue(homeTeamData, TeamDTO.class);
            TeamDTO visitorTeam = objectMapper.readValue(visitorTeamData, TeamDTO.class);

            GameDTO game = new GameDTO(id, date, season, homeTeam, visitorTeam);

            gameList.add(game);
        }

        return gameList;
    }

    @Override
    public List<GameDTO> selectAllGames() {
        return this.gameList;
    }

    @Override
    public GameDTO selectGame(long gameId) {
        for (GameDTO game: gameList){
            if (game.getId() == gameId){
                return game;
            }
        }

        return null;
    }

    @Override
    public boolean insertGame(GameDTO game) {
        if (this.selectGame(game.getId()) != null){
            return false;
        }

        this.gameList.add(game);
        return true;
    }

    @Override
    public boolean deleteGame(long gameId) {
        if (this.selectGame(gameId) == null){
            return false;
        }

        GameDTO game = this.selectGame(gameId);
        return this.gameList.remove(game);
    }
}
