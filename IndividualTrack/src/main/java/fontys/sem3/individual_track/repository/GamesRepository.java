package fontys.sem3.individual_track.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import fontys.sem3.individual_track.model.Game;

import java.util.List;

public interface GamesRepository {
    List<Game> selectAllGames() throws JsonProcessingException;
    Game selectGame(long gameId);
    boolean insertGame(Game game);
    boolean deleteGame(long gameId);
}
