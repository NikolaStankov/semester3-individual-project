package fontys.sem3.individual_track.business;

import fontys.sem3.individual_track.model.GameDTO;
import fontys.sem3.individual_track.repository.entity.Game;

import java.util.List;
import java.util.Optional;

public interface GamesService {
    List<GameDTO> getAllGames();

    Optional<GameDTO> getGame(long gameId);

    boolean addGame(Game game);

    boolean removeGame(long gameId);
}
