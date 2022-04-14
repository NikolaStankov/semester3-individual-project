package fontys.sem3.individual_track.controller;

import fontys.sem3.individual_track.business.TeamsService;
import fontys.sem3.individual_track.business.converter.TeamDTOConverter;
import fontys.sem3.individual_track.model.*;
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
@WebMvcTest(TeamsController.class)
class TeamsControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TeamsService teamsService;

    @Test
    void getAllTeams_shouldReturn200ResponseWithTeamsJsonObject() throws Exception {
        Team team1 = new Team(1L, "ET", "Example city",
                "Example conf", "Example", "Example Fakes", "Fakes");
        Team team2 = new Team(2L, "AT", "Example city2",
                "Fake conf", "Example2", "Fake Examples", "Examples");

        TeamDTO teamDTO1 = TeamDTOConverter.convertToDTO(team1);
        TeamDTO teamDTO2 = TeamDTOConverter.convertToDTO(team2);

        List<TeamDTO> responseTeamDTOs = List.of(teamDTO1, teamDTO2);

        when(teamsService.getAllTeams()).thenReturn(responseTeamDTOs);

        mockMvc.perform(get("/teams"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                        [{"id":1,"abbreviation":"ET","city":"Example city",
                        "conference":"Example conf","division":"Example","full_name":"Example Fakes","name":"Fakes"},
                        {"id":2,"abbreviation":"AT","city":"Example city2",
                        "conference":"Fake conf","division":"Example2","full_name":"Fake Examples","name":"Examples"}]
                                              """));

        verify(teamsService).getAllTeams();
    }

    @Test
    void getTeamById_shouldReturn200WithGame_whenGameFound() throws Exception {
        Team team = new Team(11L, "ET", "Example city",
                "Example conf", "Example", "Example Fakes", "Fakes");

        TeamDTO teamDTO = TeamDTOConverter.convertToDTO(team);

        when(teamsService.getTeam(11L)).thenReturn(Optional.of(teamDTO));

        mockMvc.perform(get("/teams/11"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                            {"id":11,"abbreviation":"ET","city":"Example city",
                        "conference":"Example conf","division":"Example","full_name":"Example Fakes","name":"Fakes"}
                        """));

        verify(teamsService).getTeam(11L);
    }

    @Test
    void getTeamById_shouldReturn404Error_whenTeamNotFound() throws Exception {
        when(teamsService.getTeam(11L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/teams/11"))
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(teamsService).getTeam(11L);
    }

    @Test
    void createTeam_shouldReturn201_whenRequestIsValid() throws Exception {
        CreateTeamRequestDTO expectedTeamRequest = CreateTeamRequestDTO.builder()
                .abbreviation("ET")
                .city("Example city")
                .conference("West")
                .division("Example")
                .fullName("Example Fakes")
                .name("Fakes")
                .build();

        when(teamsService.createTeam(expectedTeamRequest))
                .thenReturn(CreateTeamResponseDTO.builder()
                        .teamId(33L)
                        .build());

        mockMvc.perform(post("/teams")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content("""
                                {
                                    "abbreviation": "ET",
                                    "city": "Example city",
                                    "conference": "West",
                                    "division": "Example",
                                    "fullName": "Example Fakes",
                                    "name": "Fakes"
                                }
                                """))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json("""
                            { "teamId":  33 }
                        """));

        verify(teamsService).createTeam(expectedTeamRequest);
    }
}