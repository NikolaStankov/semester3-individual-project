package fontys.sem3.individual_track.controller;

import fontys.sem3.individual_track.business.PlayersService;
import fontys.sem3.individual_track.business.converter.PlayerDTOConverter;
import fontys.sem3.individual_track.model.*;
import fontys.sem3.individual_track.repository.entity.Player;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PlayersController.class)
class PlayersControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PlayersService playersService;

    private Team createFakeTeam() {
        return new Team(1L, "ET", "Example city",
                "Example conf", "Example", "Example Fakes", "Fakes");
    }

    @Test
    void getAllPlayers_shouldReturn200ResponseWithPlayersJsonObject() throws Exception {
        Team team = createFakeTeam();

        Player player1 = Player.builder().id(1L).firstName("Player")
                .lastName("One").position("PF").team(team)
                .build();
        Player player2 = Player.builder().id(2L).firstName("Player")
                .lastName("Two").position("PG").team(team)
                .build();

        PlayerDTO playerDTO1 = PlayerDTOConverter.convertToDTO(player1);
        PlayerDTO playerDTO2 = PlayerDTOConverter.convertToDTO(player2);

        List<PlayerDTO> responsePlayerDTOs = List.of(playerDTO1, playerDTO2);

        when(playersService.getAllPlayers()).thenReturn(responsePlayerDTOs);

        mockMvc.perform(get("/players"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                        [{"id":1,"first_name":"Player","last_name":"One","position":"PF",
                        "team":{"id":1,"abbreviation":"ET","city":"Example city",
                        "conference":"Example conf","division":"Example","full_name":"Example Fakes","name":"Fakes"}},
                        {"id":2,"first_name":"Player","last_name":"Two","position":"PG",
                        "team":{"id":1,"abbreviation":"ET","city":"Example city",
                        "conference":"Example conf","division":"Example","full_name":"Example Fakes","name":"Fakes"}}]
                                              """));

        verify(playersService).getAllPlayers();
    }

    @Test
    void addPlayer_shouldReturn201_whenRequestIsValid() throws Exception {
        Team team = createFakeTeam();

        CreatePlayerRequestDTO expectedPlayerRequest = CreatePlayerRequestDTO.builder()
                .firstName("Expected")
                .lastName("Player")
                .position("C")
                .teamId(team.getId())
                .build();

        when(playersService.createPlayer(expectedPlayerRequest))
                .thenReturn(CreatePlayerResponseDTO.builder()
                        .playerId(18L)
                        .build());

        mockMvc.perform(post("/players")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content("""
                                {
                                    "firstName": "Expected",
                                    "lastName": "Player",
                                    "position": "C",
                                    "teamId": "1"
                                }
                                """))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json("""
                            { "playerId":  18 }
                        """));

        verify(playersService).createPlayer(expectedPlayerRequest);
    }
}