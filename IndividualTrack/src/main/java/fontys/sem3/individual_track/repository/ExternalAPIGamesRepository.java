package fontys.sem3.individual_track.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import fontys.sem3.individual_track.repository.entity.Game;

import java.util.List;

public interface ExternalAPIGamesRepository {
    List<Game> fetchGamesFromAPI() throws JsonProcessingException;
}
