package fontys.sem3.individual_track.repository;

import fontys.sem3.individual_track.model.Game;

import java.util.List;

public interface GamesRepository {
    List<Game> selectAllGames();
    Game selectGame(long gameId);
    boolean insertGame(Game game);
    boolean deleteGame(long gameId);
}
