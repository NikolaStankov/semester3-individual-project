package fontys.sem3.individual_track.business.impl;

import fontys.sem3.individual_track.business.converter.GameDTOConverter;
import fontys.sem3.individual_track.business.converter.TeamDTOConverter;
import fontys.sem3.individual_track.business.validator.TeamIdValidator;
import fontys.sem3.individual_track.model.CreateGameRequestDTO;
import fontys.sem3.individual_track.model.CreateGameResponseDTO;
import fontys.sem3.individual_track.model.GameDTO;
import fontys.sem3.individual_track.repository.GamesRepository;
import fontys.sem3.individual_track.repository.entity.Game;
import fontys.sem3.individual_track.repository.entity.Team;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GamesServiceImplTest {

    @Mock
    private GamesRepository gamesRepositoryMock;

    @Mock
    private TeamIdValidator teamIdValidator;

    @InjectMocks
    private GamesServiceImpl gamesService;

    private Team createFakeHomeTeam() {
        return new Team(1L, "HT", "Home city",
                "Home conf", "Home", "Home Homes", "Homes");
    }

    private Team createFakeVisitorTeam() {
        return new Team(2L, "VT", "Visitor city",
                "Visitor conf", "Visitor", "Visitor Visitors", "Visitors");
    }

    @Test
    void getAllGames_shouldReturnAllGamesFromRepository() {

        Team homeTeam = createFakeHomeTeam();
        Team visitorTeam = createFakeVisitorTeam();

        List<Game> gameList = List.of(
                Game.builder().id(1L).date("13/04/2022").season(2022)
                        .homeTeam(homeTeam).visitorTeam(visitorTeam).build(),
                Game.builder().id(2L).date("13/04/2023").season(2023)
                        .homeTeam(homeTeam).visitorTeam(visitorTeam).build()
        );

        when(gamesRepositoryMock.findAll()).thenReturn(gameList);

        List<GameDTO> actualResponse = gamesService.getAllGames();

        List<GameDTO> expectedResponse = List.of(
                GameDTO.builder().id(1L).date("13/04/2022").season(2022)
                        .homeTeam(TeamDTOConverter.convertToDTO(homeTeam))
                        .visitorTeam(TeamDTOConverter.convertToDTO(visitorTeam)).build(),
                GameDTO.builder().id(2L).date("13/04/2023").season(2023)
                        .homeTeam(TeamDTOConverter.convertToDTO(homeTeam))
                        .visitorTeam(TeamDTOConverter.convertToDTO(visitorTeam)).build()
        );

        assertEquals(expectedResponse, actualResponse);
        verify(gamesRepositoryMock).findAll();
    }

    @Test
    void getGame_shouldReturnGameWithTheGivenId() {
        Team homeTeam = createFakeHomeTeam();
        Team visitorTeam = createFakeVisitorTeam();

        Game game = Game.builder().id(20L).date("13/04/2022").season(2022)
                .homeTeam(homeTeam).visitorTeam(visitorTeam).build();

        when(gamesRepositoryMock.findById(20L)).thenReturn(Optional.of(game));

        Optional<GameDTO> gameOptional = gamesService.getGame(20L);

        GameDTO actualGameDTO = gameOptional.orElseThrow();
        GameDTO expectedGameDTO = GameDTOConverter.convertToDTO(game);

        assertEquals(expectedGameDTO, actualGameDTO);
        verify(gamesRepositoryMock).findById(20L);
    }

    @Test
    void createGame_shouldSaveNewGameWithAllFields() {
        Team homeTeam = Team.builder().id(1L).build();
        Team visitorTeam = Team.builder().id(2L).build();

        Game expectedGameToSave = Game.builder().date("13/04/2022").season(2022)
                .homeTeam(homeTeam).visitorTeam(visitorTeam).build();

        Game savedGame = Game.builder().id(20L).date("13/04/2022").season(2022)
                .homeTeam(homeTeam).visitorTeam(visitorTeam).build();

        when(gamesRepositoryMock.save(expectedGameToSave)).thenReturn(savedGame);

        CreateGameRequestDTO gameRequest = CreateGameRequestDTO.builder()
                .date("13/04/2022")
                .season(2022)
                .homeTeamId(homeTeam.getId())
                .visitorTeamId(visitorTeam.getId())
                .build();

        CreateGameResponseDTO actualGameResponse = gamesService.createGame(gameRequest);
        CreateGameResponseDTO expectedGameResponse = CreateGameResponseDTO.builder().gameId(20L).build();

        assertEquals(expectedGameResponse, actualGameResponse);
        verify(teamIdValidator).validateTeamId(1L);
        verify(teamIdValidator).validateTeamId(2L);
        verify(gamesRepositoryMock).save(expectedGameToSave);
    }

    @Test
    void getGamesByTeamId_shouldReturnAllGamesPlayedByTeam() {
        List<Game> homeGames = List.of(Game.builder()
                .id(12L)
                .date("22/02/2022")
                .visitorTeam(this.createFakeVisitorTeam())
                .homeTeam(this.createFakeHomeTeam())
                .build());

        List<Game> visitorGames = List.of(Game.builder()
                .id(24L)
                .date("02/12/2022")
                .homeTeam(this.createFakeVisitorTeam())
                .visitorTeam(this.createFakeHomeTeam())
                .build());

        when(gamesRepositoryMock.findAllByHomeTeamId(1L)).thenReturn(homeGames);
        when(gamesRepositoryMock.findAllByVisitorTeamId(1L)).thenReturn(visitorGames);

        List<GameDTO> expectedResponse = new ArrayList<>();
        expectedResponse.addAll(homeGames.stream().map(GameDTOConverter::convertToDTO).toList());
        expectedResponse.addAll(visitorGames.stream().map(GameDTOConverter::convertToDTO).toList());

        List<GameDTO> actualResponse = this.gamesService.getGamesByTeamId(1L);

        assertEquals(expectedResponse, actualResponse);

        verify(gamesRepositoryMock).findAllByHomeTeamId(1L);
        verify(gamesRepositoryMock).findAllByVisitorTeamId(1L);
    }
}