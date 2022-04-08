package fontys.sem3.individual_track.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import fontys.sem3.individual_track.repository.entity.Team;

import java.util.List;

public interface ExternalAPITeamsRepository {
    List<Team> fetchTeamsFromAPI() throws JsonProcessingException;
}
