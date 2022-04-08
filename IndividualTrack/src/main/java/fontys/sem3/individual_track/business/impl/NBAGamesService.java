package fontys.sem3.individual_track.business.impl;

import fontys.sem3.individual_track.business.GamesService;
import fontys.sem3.individual_track.business.converter.GameDTOConverter;
import fontys.sem3.individual_track.model.GameDTO;
import fontys.sem3.individual_track.repository.GamesRepository;
import fontys.sem3.individual_track.repository.entity.Game;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Primary
public class NBAGamesService implements GamesService {

    private final GamesRepository gamesRepository;

    @Override
    public List<GameDTO> getAllGames() {
        List<Game> gameList = this.gamesRepository.findAll();
        List<GameDTO> gameDTOList = new ArrayList<>();

        for (Game game : gameList) {
            gameDTOList.add(GameDTOConverter.convertToDTO(game));
        }

        return gameDTOList;
    }

    @Override
    public Optional<GameDTO> getGame(long gameId) {

        return this.gamesRepository.findById(gameId)
                .map(GameDTOConverter::convertToDTO);
    }

    @Override
    public boolean addGame(Game game) {
        return false;
    }

    @Override
    public boolean removeGame(long gameId) {
        return false;
    }
}
