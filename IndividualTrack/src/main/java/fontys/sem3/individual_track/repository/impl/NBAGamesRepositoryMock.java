package fontys.sem3.individual_track.repository.impl;

import fontys.sem3.individual_track.model.GameDTO;
import fontys.sem3.individual_track.model.TeamDTO;
import fontys.sem3.individual_track.repository.GamesRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("gamesMock")
public class NBAGamesRepositoryMock implements GamesRepository {

    private final List<GameDTO> gamesList;

    public NBAGamesRepositoryMock() {
        this.gamesList = new ArrayList<>();

        this.gamesList.add(new GameDTO(1, "2018-10-16", 2022,
                new TeamDTO(1, "LAL","Los Angeles",
                        "Western","Pacific",
                        "Los Angeles Lakers", "Lakers"),
                new TeamDTO(2, "LAC","Los Angeles",
                        "Western", "Atlantic",
                        "Los Angeles Clippers", "Clippers")));

        this.gamesList.add(new GameDTO(2, "2018-10-16", 2022,
                new TeamDTO(3, "MIH", "Miami",
                        "Eastern", "Central",
                        "Miami Heat", "Heat"),
                new TeamDTO(4, "CHB", "Chicago",
                        "Eastern", "Southwest",
                        "Chicago Bulls", "Bulls")));
    }

    @Override
    public List<GameDTO> selectAllGames() {
        return this.gamesList;
    }

    @Override
    public GameDTO selectGame(long gameId) {
        for (GameDTO game: gamesList){
            if (game.getId() == gameId){
                return game;
            }
        }

        return null;
    }

    @Override
    public boolean insertGame(GameDTO game) {
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

        GameDTO game = this.selectGame(gameId);
        return this.gamesList.remove(game);
    }
}
