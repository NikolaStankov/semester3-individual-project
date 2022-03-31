package fontys.sem3.individual_track.repository.impl;

import fontys.sem3.individual_track.model.Game;
import fontys.sem3.individual_track.model.Team;
import fontys.sem3.individual_track.model.Ticket;
import fontys.sem3.individual_track.repository.GamesRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository("nbaGames")
@Primary
public class NBAGamesRepository implements GamesRepository {

    private final List<Game> gamesList;

    public NBAGamesRepository() {
        this.gamesList = new ArrayList<>();
        this.gamesList.add(new Game(1, LocalDate.now(), 2022,
                new Team(1, "LAL","Los Angeles", "Western", "Los Angeles Lakers"),
                new Team(2, "LAC","Los Angeles", "Western", "Los Angeles Clippers")));
        this.gamesList.add(new Game(2, LocalDate.now(), 2022,
                new Team(3, "MIH", "Miami", "Eastern", "Miami Heat"),
                new Team(4, "CHB", "Chicago", "Eastern", "Chicago Bulls")));
    }

    @Override
    public List<Game> selectAllGames() {
        return this.gamesList;
    }

    @Override
    public Game selectGame(long gameId) {
        for (Game game: gamesList){
            if (game.getId() == gameId){
                return game;
            }
        }

        return null;
    }

    @Override
    public boolean insertGame(Game game) {
        if (this.selectGame(game.getId()) != null){
            return false;
        }

        this.gamesList.add(game);
        return true;
    }

    @Override
    public boolean deleteGame(long gameId) {
        if (this.selectGame(gameId) == null){
            return false;
        }

        Game game = this.selectGame(gameId);
        return this.gamesList.remove(game);
    }
}
