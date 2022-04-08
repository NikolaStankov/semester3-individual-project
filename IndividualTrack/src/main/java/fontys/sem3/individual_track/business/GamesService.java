package fontys.sem3.individual_track.business;

import com.fasterxml.jackson.core.JsonProcessingException;
import fontys.sem3.individual_track.model.GameDTO;

import java.util.List;

public interface GamesService {
    List<GameDTO> getAllGames() throws JsonProcessingException;

    GameDTO getGame(long gameId);

    boolean addGame(GameDTO game);

    boolean removeGame(long gameId);
}
