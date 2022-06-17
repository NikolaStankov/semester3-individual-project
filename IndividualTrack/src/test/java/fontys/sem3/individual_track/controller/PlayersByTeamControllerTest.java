package fontys.sem3.individual_track.controller;

import fontys.sem3.individual_track.business.AccessTokenDecoder;
import fontys.sem3.individual_track.business.PlayersService;
import fontys.sem3.individual_track.business.TeamsService;
import fontys.sem3.individual_track.business.converter.TeamDTOConverter;
import fontys.sem3.individual_track.business.exception.CustomExceptionHandler;
import fontys.sem3.individual_track.business.exception.InvalidTeamIdException;
import fontys.sem3.individual_track.business.exception.InvalidUserIdException;
import fontys.sem3.individual_track.model.PlayerDTO;
import fontys.sem3.individual_track.repository.entity.Team;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PlayersByTeamController.class)
class PlayersByTeamControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PlayersService playersService;

    @MockBean
    private TeamsService teamsService;

    @MockBean
    private AccessTokenDecoder accessTokenDecoder;

    private Team createFakeTeam() {
        return new Team(1L, "HT", "Home city",
                "Home conf", "Home", "Home Homes", "Homes");
    }

    private List<PlayerDTO> createExpectedPlayers() {
        return List.of(PlayerDTO.builder()
                .id(12L)
                .firstName("Test")
                .lastName("Testing")
                .position("C")
                .team(TeamDTOConverter.convertToDTO(this.createFakeTeam()))
                .build());
    }

    @Test
    void getPlayersByTeam_shouldReturnAllPlayersFromGivenTeam() throws Exception {
        Team fakeTeam = this.createFakeTeam();
        List<PlayerDTO> expectedPlayers = this.createExpectedPlayers();

        when(teamsService.getTeam(fakeTeam.getId())).thenReturn(Optional.of(TeamDTOConverter.convertToDTO(fakeTeam)));
        when(playersService.getPlayersByTeam(fakeTeam)).thenReturn(expectedPlayers);

        mockMvc.perform(get("/teams/1/players"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                        [{"id": 12, "first_name":  "Test", "last_name":  "Testing", "position":  "C",
                        "team": {"id":  1, "abbreviation": "HT", "city": "Home city",
                          "conference": "Home conf", "division":  "Home", "full_name":  "Home Homes", "name":  "Homes"}}]
                          """));

        verify(teamsService).getTeam(1L);
        verify(playersService).getPlayersByTeam(fakeTeam);
    }

    @Test
    void getPlayersByTeam_shouldThrowInvalidTeamIdException_whenTeamNotFound() throws Exception {
        when(teamsService.getTeam(11L)).thenReturn(Optional.empty());

        String expectedMessage = "Invalid team id.";

        try {
            mockMvc.perform(get("/teams/11"));
        } catch (InvalidTeamIdException exception) {
            assertEquals(expectedMessage, exception.getMessage());

            verify(teamsService).getTeam(11L);
        }
    }
}