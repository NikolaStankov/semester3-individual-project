package fontys.sem3.individual_track.business;

import fontys.sem3.individual_track.model.Game;

import java.util.List;

public interface GamesService {
    List<Game> getAllGames();
    Game getGame(long gameId);
    boolean addGame(Game game);
    boolean removeGame(long gameId);
}
