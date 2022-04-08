package fontys.sem3.individual_track.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import fontys.sem3.individual_track.model.GameDTO;

import java.util.List;

public interface GamesRepository {
    List<GameDTO> selectAllGames() throws JsonProcessingException;

    GameDTO selectGame(long gameId);

    boolean insertGame(GameDTO game);

    boolean deleteGame(long gameId);
}
