package fontys.sem3.individual_track.business.impl;

import fontys.sem3.individual_track.business.GamesService;
import fontys.sem3.individual_track.model.Game;
import fontys.sem3.individual_track.repository.GamesRepository;
import fontys.sem3.individual_track.repository.TeamsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NBAGamesService implements GamesService {

    private final GamesRepository gamesRepository;

    @Autowired
    public NBAGamesService(@Qualifier("nbaGames") GamesRepository gamesRepository) {
        this.gamesRepository = gamesRepository;
    }

    @Override
    public List<Game> getAllGames() {
        return this.gamesRepository.selectAllGames();
    }

    @Override
    public Game getGame(long gameId) {
        return this.gamesRepository.selectGame(gameId);
    }

    @Override
    public boolean addGame(Game game) {
       return this.gamesRepository.insertGame(game);
    }

    @Override
    public boolean removeGame(long gameId) {
        return  this.gamesRepository.deleteGame(gameId);
    }
}
