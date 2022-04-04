package fontys.sem3.individual_track.business;

import com.fasterxml.jackson.core.JsonProcessingException;
import fontys.sem3.individual_track.model.Game;

import java.util.List;

public interface GamesService {
    List<Game> getAllGames() throws JsonProcessingException;
    Game getGame(long gameId);
    boolean addGame(Game game);
    boolean removeGame(long gameId);
}
