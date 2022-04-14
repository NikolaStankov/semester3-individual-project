package fontys.sem3.individual_track.business;

import fontys.sem3.individual_track.model.CreateGameRequestDTO;
import fontys.sem3.individual_track.model.CreateGameResponseDTO;
import fontys.sem3.individual_track.model.GameDTO;

import java.util.List;
import java.util.Optional;

public interface GamesService {
    List<GameDTO> getAllGames();

    Optional<GameDTO> getGame(long gameId);

    CreateGameResponseDTO createGame(CreateGameRequestDTO gameRequest);

    void removeGame(long gameId);
}
