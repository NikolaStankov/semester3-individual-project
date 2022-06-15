package fontys.sem3.individual_track.business.impl;

import fontys.sem3.individual_track.business.GamesService;
import fontys.sem3.individual_track.business.converter.GameDTOConverter;
import fontys.sem3.individual_track.business.validator.TeamIdValidator;
import fontys.sem3.individual_track.model.CreateGameRequestDTO;
import fontys.sem3.individual_track.model.CreateGameResponseDTO;
import fontys.sem3.individual_track.model.GameDTO;
import fontys.sem3.individual_track.repository.GamesRepository;
import fontys.sem3.individual_track.repository.entity.Game;
import fontys.sem3.individual_track.repository.entity.Team;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Primary
public class GamesServiceImpl implements GamesService {

    private final GamesRepository gamesRepository;
    private final TeamIdValidator teamIdValidator;

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
    public CreateGameResponseDTO createGame(CreateGameRequestDTO gameRequest) {
        teamIdValidator.validateTeamId(gameRequest.getHomeTeamId());
        teamIdValidator.validateTeamId(gameRequest.getVisitorTeamId());

        Game gameToSave = Game.builder()
                .date(gameRequest.getDate())
                .season(gameRequest.getSeason())
                .homeTeam(Team.builder().id(gameRequest.getHomeTeamId()).build())
                .visitorTeam(Team.builder().id(gameRequest.getVisitorTeamId()).build())
                .build();

        Game savedGame = this.gamesRepository.save(gameToSave);

        return CreateGameResponseDTO.builder()
                .gameId(savedGame.getId())
                .build();
    }

    @Override
    public void removeGame(long gameId) {
        this.gamesRepository.deleteById(gameId);
    }

    @Override
    public List<GameDTO> getGamesByTeamId(long teamId) {

        List<GameDTO> gamesByTeamId = new ArrayList<>();

        List<GameDTO> homeGames = this.gamesRepository.findAllByHomeTeamId(teamId)
                .stream()
                .map(GameDTOConverter::convertToDTO)
                .toList();
        List<GameDTO> awayGames = this.gamesRepository.findAllByVisitorTeamId(teamId)
                .stream()
                .map(GameDTOConverter::convertToDTO)
                .toList();

        gamesByTeamId.addAll(homeGames);
        gamesByTeamId.addAll(awayGames);

        return gamesByTeamId;
    }
}
