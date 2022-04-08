package fontys.sem3.individual_track.repository.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import fontys.sem3.individual_track.repository.ExternalAPITeamsRepository;
import fontys.sem3.individual_track.repository.entity.Team;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class ExternalAPITeamsRepositoryImpl implements ExternalAPITeamsRepository {
    private final RestTemplate restTemplate;

    public ExternalAPITeamsRepositoryImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Team> fetchTeamsFromAPI() throws JsonProcessingException {
        String externalURL = "https://www.balldontlie.io/api/v1/teams";

        String teamsResponse = this.restTemplate.getForObject(externalURL, String.class);
        JSONObject jsonObject = new JSONObject(teamsResponse);
        JSONArray jsonArray = jsonObject.getJSONArray("data");

        List<Team> teamList = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            long id = jsonArray.getJSONObject(i).getLong("id");
            String abbreviation = jsonArray.getJSONObject(i).getString("abbreviation");
            String city = jsonArray.getJSONObject(i).getString("city");
            String conference = jsonArray.getJSONObject(i).getString("conference");
            String division = jsonArray.getJSONObject(i).getString("division");
            String fullName = jsonArray.getJSONObject(i).getString("full_name");
            String name = jsonArray.getJSONObject(i).getString("name");

            Team team = new Team(id, abbreviation, city, conference,
                    division, fullName, name);
            teamList.add(team);
        }

        return teamList;
    }
}
