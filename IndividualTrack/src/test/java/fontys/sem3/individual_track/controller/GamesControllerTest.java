package fontys.sem3.individual_track.controller;

import fontys.sem3.individual_track.business.GamesService;
import fontys.sem3.individual_track.business.converter.GameDTOConverter;
import fontys.sem3.individual_track.model.*;
import fontys.sem3.individual_track.repository.entity.Game;
import fontys.sem3.individual_track.repository.entity.Team;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@ExtendWith(SpringExtension.class)
@WebMvcTest(GamesController.class)
class GamesControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GamesService gamesService;

    private Team createHomeTeam() {
        return new Team(1L, "HT", "Home city",
                "Home conf", "Home", "Home Homes", "Homes");
    }

    private Team createVisitorTeam() {
        return new Team(2L, "VT", "Visitor city",
                "Visitor conf", "Visitor", "Visitor Visitors", "Visitors");
    }

    @Test
    void getAllGames_shouldReturn200ResponseWithGamesJsonObject() throws Exception {
        Team homeTeam = createHomeTeam();
        Team visitorTeam = createVisitorTeam();

        Game game1 = Game.builder().id(1L).date("18/04/2022").season(2022)
                .homeTeam(homeTeam).visitorTeam(visitorTeam).build();
        Game game2 = Game.builder().id(2L).date("20/04/2022").season(2022)
                .homeTeam(homeTeam).visitorTeam(visitorTeam).build();

        GameDTO gameDTO1 = GameDTOConverter.convertToDTO(game1);
        GameDTO gameDTO2 = GameDTOConverter.convertToDTO(game2);

        List<GameDTO> responseGameDTOs = List.of(gameDTO1, gameDTO2);

        when(gamesService.getAllGames()).thenReturn(responseGameDTOs);

        mockMvc.perform(get("/games"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                        [{"id": 1, "date":  "18/04/2022", "season":  2022,
                         "home_team":  {"id":  1, "abbreviation": "HT", "city": "Home city",
                          "conference": "Home conf", "division":  "Home", "full_name":  "Home Homes", "name":  "Homes"},
                          "visitor_team":  {"id":  2, "abbreviation": "VT", "city": "Visitor city",
                          "conference": "Visitor conf", "division":  "Visitor", "full_name":  "Visitor Visitors", "name":  "Visitors"}},
                          {"id": 2, "date":  "20/04/2022", "season":  2022,
                         "home_team":  {"id":  1, "abbreviation": "HT", "city": "Home city",
                          "conference": "Home conf", "division":  "Home", "full_name":  "Home Homes", "name":  "Homes"},
                          "visitor_team":  {"id":  2, "abbreviation": "VT", "city": "Visitor city",
                          "conference": "Visitor conf", "division":  "Visitor", "full_name":  "Visitor Visitors", "name":  "Visitors"}}]
                          """));

        verify(gamesService).getAllGames();
    }

    @Test
    void getGameById_shouldReturn200WithGame_whenGameFound() throws Exception {
        Team homeTeam = createHomeTeam();
        Team visitorTeam = createVisitorTeam();

        Game game = Game.builder().id(12L).date("18/04/2022").season(2022)
                .homeTeam(homeTeam).visitorTeam(visitorTeam).build();

        GameDTO gameDTO = GameDTOConverter.convertToDTO(game);

        when(gamesService.getGame(12L)).thenReturn(Optional.of(gameDTO));

        mockMvc.perform(get("/games/12"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                            {"id": 12, "date":  "18/04/2022", "season":  2022,
                            "home_team":  {"id":  1, "abbreviation": "HT", "city": "Home city",
                             "conference": "Home conf", "division":  "Home", "full_name":  "Home Homes", "name":  "Homes"},
                             "visitor_team":  {"id":  2, "abbreviation": "VT", "city": "Visitor city",
                             "conference": "Visitor conf", "division":  "Visitor", "full_name":  "Visitor Visitors", "name":  "Visitors"}}
                        """));

        verify(gamesService).getGame(12L);
    }

    @Test
    void getGameById_shouldReturn404Error_whenGameNotFound() throws Exception {
        when(gamesService.getGame(12L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/games/12"))
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(gamesService).getGame(12L);
    }

    @Test
    void addGame_shouldReturn201_whenRequestIsValid() throws Exception {
        Team homeTeam = createHomeTeam();
        Team visitorTeam = createVisitorTeam();

        CreateGameRequestDTO expectedGameRequest = CreateGameRequestDTO.builder()
                .date("18/04/2022")
                .season(2022)
                .homeTeamId(homeTeam.getId())
                .visitorTeamId(visitorTeam.getId())
                .build();

        when(gamesService.createGame(expectedGameRequest))
                .thenReturn(CreateGameResponseDTO.builder()
                        .gameId(22L)
                        .build());

        mockMvc.perform(post("/games")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content("""
                                {
                                    "date": "18/04/2022",
                                    "season": "2022",
                                    "homeTeamId": "1",
                                    "visitorTeamId": "2"
                                }
                                """))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json("""
                            { "gameId":  22 }
                        """));

        verify(gamesService).createGame(expectedGameRequest);
    }
}