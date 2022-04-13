package fontys.sem3.individual_track.business.impl;

import fontys.sem3.individual_track.business.converter.TeamDTOConverter;
import fontys.sem3.individual_track.model.TeamDTO;
import fontys.sem3.individual_track.repository.TeamsRepository;
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
class TeamsServiceImplTest {

    @Mock
    private TeamsRepository teamsRepositoryMock;

    @InjectMocks
    private TeamsServiceImpl teamsService;

    @Test
    void getAllTeams_shouldReturnAllTeamsFromRepository() {
        List<Team> teamList = List.of(
                Team.builder().id(1L).abbreviation("T1").city("City1").conference("West")
                        .division("Atlantic").fullName("Team Teams1").name("Team1").build(),
                Team.builder().id(2L).abbreviation("T2").city("City2").conference("East")
                        .division("Pacific").fullName("Team Teams2").name("Team2").build()
        );

        when(teamsRepositoryMock.findAll()).thenReturn(teamList);

        List<TeamDTO> actualResponse = teamsService.getAllTeams();
        List<TeamDTO> expectedResponse = teamList.stream().map(TeamDTOConverter::convertToDTO).toList();

        assertEquals(expectedResponse, actualResponse);
        verify(teamsRepositoryMock).findAll();
    }

    @Test
    void getTeam_shouldReturnTeamWithTheGivenId() {
        Team team = Team.builder().id(1L).abbreviation("T1").city("City1").conference("West")
                .division("Atlantic").fullName("Team Teams1").name("Team1").build();

        when(teamsRepositoryMock.findById(1L)).thenReturn(Optional.of(team));

        Optional<TeamDTO> optionalTeam = teamsService.getTeam(1L);

        TeamDTO actualTeamDTO = optionalTeam.orElseThrow();
        TeamDTO expectedTeamDTO = TeamDTOConverter.convertToDTO(team);

        assertEquals(expectedTeamDTO, actualTeamDTO);
        verify(teamsRepositoryMock).findById(1L);
    }

    @Test
    void createTeam() {
    }
}