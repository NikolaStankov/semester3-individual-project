package fontys.sem3.individual_track.business.impl;

import fontys.sem3.individual_track.business.converter.PlayerDTOConverter;
import fontys.sem3.individual_track.business.converter.TeamDTOConverter;
import fontys.sem3.individual_track.business.validator.TeamIdValidator;
import fontys.sem3.individual_track.model.*;
import fontys.sem3.individual_track.repository.PlayersRepository;
import fontys.sem3.individual_track.repository.entity.Player;
import fontys.sem3.individual_track.repository.entity.Team;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PlayersServiceImplTest {

    @Mock
    private PlayersRepository playersRepositoryMock;

    @Mock
    private TeamIdValidator teamIdValidator;

    @InjectMocks
    private PlayersServiceImpl playersService;

    @Test
    void getAllPlayers_shouldReturnAllPlayersFromRepository() {
        Team team = Team.builder().id(1L).abbreviation("T1").city("City1").conference("West")
                .division("Atlantic").fullName("Team Teams1").name("Team1").build();

        List<Player> playerList = List.of(
                Player.builder().id(1L).firstName("Player").lastName("1")
                        .position("SF").team(team).build(),
                Player.builder().id(2L).firstName("Player").lastName("2")
                        .position("C").team(team).build()
        );

        when(playersRepositoryMock.findAll()).thenReturn(playerList);

        List<PlayerDTO> actualResponse = playersService.getAllPlayers();

        List<PlayerDTO> expectedResponse = List.of(
                PlayerDTO.builder().id(1L).firstName("Player").lastName("1")
                        .position("SF").team(TeamDTOConverter.convertToDTO(team))
                        .build(),
                PlayerDTO.builder().id(2L).firstName("Player").lastName("2")
                        .position("C").team(TeamDTOConverter.convertToDTO(team))
                        .build()
        );

        assertEquals(expectedResponse, actualResponse);
        verify(playersRepositoryMock).findAll();
    }

    @Test
    void getPlayer_shouldReturnPlayerWithGivenId() {
        Team team = Team.builder().id(1L).abbreviation("T1").city("City1").conference("West")
                .division("Atlantic").fullName("Team Teams1").name("Team1").build();

        Player player = Player.builder().id(1L).firstName("Player").lastName("1")
                .position("SF").team(team).build();

        when(playersRepositoryMock.findById(1L)).thenReturn(Optional.of(player));

        Optional<PlayerDTO> playerOptional = playersService.getPlayer(1L);

        PlayerDTO actualPlayerDTO = playerOptional.orElseThrow();
        PlayerDTO expectedPlayerDTO = PlayerDTOConverter.convertToDTO(player);

        assertEquals(expectedPlayerDTO, actualPlayerDTO);
        verify(playersRepositoryMock).findById(1L);
    }

    @Test
    void createPlayer_shouldSavePlayerWithAllFields() {
        Team team = Team.builder().id(1L).build();

        Player expectedPlayerToSave = Player.builder().firstName("Player").lastName("1")
                .position("SF").team(team).build();

        Player savedPlayer = Player.builder().id(1L).firstName("Player").lastName("1")
                .position("SF").team(team).build();

        when(playersRepositoryMock.save(expectedPlayerToSave)).thenReturn(savedPlayer);

        CreatePlayerRequestDTO playerRequest = CreatePlayerRequestDTO.builder()
                .firstName("Player")
                .lastName("1")
                .position("SF")
                .teamId(team.getId())
                .build();

        CreatePlayerResponseDTO actualPlayerResponse = playersService.createPlayer(playerRequest);
        CreatePlayerResponseDTO expectedPlayerResponse = CreatePlayerResponseDTO.builder().playerId(1L).build();

        assertEquals(expectedPlayerResponse, actualPlayerResponse);
        verify(teamIdValidator).validateTeamId(1L);
        verify(playersRepositoryMock).save(expectedPlayerToSave);
    }
}