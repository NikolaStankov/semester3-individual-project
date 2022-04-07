package fontys.sem3.individual_track.repository.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fontys.sem3.individual_track.model.Player;
import fontys.sem3.individual_track.model.Team;
import fontys.sem3.individual_track.repository.PlayersRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Repository("nbaPlayers")
@Primary
public class NBAPlayersRepository implements PlayersRepository {

    private final RestTemplate restTemplate;
    private final List<Player> playerList;

    @Autowired
    public NBAPlayersRepository(RestTemplate restTemplate) throws JsonProcessingException {
        this.restTemplate = restTemplate;
        this.playerList = fetchPlayersFromAPI();
    }

    private List<Player> fetchPlayersFromAPI() throws JsonProcessingException {
        String externalURL = "https://www.balldontlie.io/api/v1/players";

        String playersResponse = this.restTemplate.getForObject(externalURL, String.class);
        JSONObject jsonObject = new JSONObject(playersResponse);
        JSONArray jsonArray = jsonObject.getJSONArray("data");

        List<Player> playerList = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            long id = jsonArray.getJSONObject(i).getLong("id");
            String firstName = jsonArray.getJSONObject(i).getString("first_name");
            String lastName = jsonArray.getJSONObject(i).getString("last_name");
            String position = jsonArray.getJSONObject(i).getString("position");

            String playerTeamData = jsonArray.getJSONObject(i).getJSONObject("team").toString();

            ObjectMapper objectMapper = new ObjectMapper();

            Team playerTeam = objectMapper.readValue(playerTeamData, Team.class);

            Player player = new Player(id, firstName, lastName, position, playerTeam);

            playerList.add(player);
        }

        return playerList;
    }

    @Override
    public List<Player> selectAllPlayers() {
        return this.playerList;
    }

    @Override
    public Player selectPlayer(long playerId) {
        for (Player player : playerList) {
            if (player.getId() == playerId) {
                return player;
            }
        }

        return null;
    }

    @Override
    public boolean insertPlayer(Player player) {
        if (this.selectPlayer(player.getId()) != null) {
            return false;
        }

        this.playerList.add(player);
        return true;
    }

    @Override
    public boolean deletePlayer(long playerId) {
        if (this.selectPlayer(playerId) == null) {
            return false;
        }

        Player player = this.selectPlayer(playerId);
        return this.playerList.remove(player);
    }
}
