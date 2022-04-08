package fontys.sem3.individual_track.business.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import fontys.sem3.individual_track.business.GamesService;
import fontys.sem3.individual_track.model.GameDTO;
import fontys.sem3.individual_track.repository.GamesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NBAGamesService implements GamesService {

    private final GamesRepository gamesRepository;

    @Autowired
    public NBAGamesService(GamesRepository gamesRepository) {
        this.gamesRepository = gamesRepository;
    }

    @Override
    public List<GameDTO> getAllGames() throws JsonProcessingException {
        return this.gamesRepository.selectAllGames();
    }

    @Override
    public GameDTO getGame(long gameId) {
        return this.gamesRepository.selectGame(gameId);
    }

    @Override
    public boolean addGame(GameDTO game) {
        return this.gamesRepository.insertGame(game);
    }

    @Override
    public boolean removeGame(long gameId) {
        return this.gamesRepository.deleteGame(gameId);
    }
}
