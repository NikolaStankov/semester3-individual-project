package fontys.sem3.individual_track.controller;

import fontys.sem3.individual_track.business.AccessTokenDecoder;
import fontys.sem3.individual_track.business.GamesService;
import fontys.sem3.individual_track.business.converter.TeamDTOConverter;
import fontys.sem3.individual_track.model.GameDTO;
import fontys.sem3.individual_track.repository.entity.Team;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@ExtendWith(SpringExtension.class)
@WebMvcTest(TeamEventsController.class)
class TeamEventsControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GamesService gamesService;

    @MockBean
    private AccessTokenDecoder accessTokenDecoder;

    private Team createHomeTeam() {
        return new Team(1L, "HT", "Home city",
                "Home conf", "Home", "Home Homes", "Homes");
    }

    private Team createVisitorTeam() {
        return new Team(2L, "VT", "Visitor city",
                "Visitor conf", "Visitor", "Visitor Visitors", "Visitors");
    }

    private List<GameDTO> createExpectedGames() {
        return List.of(GameDTO.builder()
                .id(23L)
                .date("06/16/2022")
                .season(2022)
                .homeTeam(TeamDTOConverter.convertToDTO(this.createHomeTeam()))
                .visitorTeam(TeamDTOConverter.convertToDTO(this.createVisitorTeam()))
                .build());
    }

    @Test
    void getAllGamesByTeamId_shouldReturnAllGamesPlayedByTeamWithGivenId() throws Exception {
        List<GameDTO> expectedGames = this.createExpectedGames();

        when(gamesService.getGamesByTeamId(1L)).thenReturn(expectedGames);

        mockMvc.perform(get("/teams/1/events"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                        [{"id": 23, "date":  "06/16/2022", "season":  2022,
                         "home_team":  {"id":  1, "abbreviation": "HT", "city": "Home city",
                          "conference": "Home conf", "division":  "Home", "full_name":  "Home Homes", "name":  "Homes"},
                          "visitor_team":  {"id":  2, "abbreviation": "VT", "city": "Visitor city",
                          "conference": "Visitor conf", "division":  "Visitor", "full_name":  "Visitor Visitors", "name":  "Visitors"}}]
                          """));

        verify(gamesService).getGamesByTeamId(1L);
    }
}