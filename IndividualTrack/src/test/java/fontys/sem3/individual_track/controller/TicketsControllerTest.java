package fontys.sem3.individual_track.controller;

import fontys.sem3.individual_track.business.TicketsService;
import fontys.sem3.individual_track.business.converter.GameDTOConverter;
import fontys.sem3.individual_track.model.CreateTicketRequestDTO;
import fontys.sem3.individual_track.model.CreateTicketResponseDTO;
import fontys.sem3.individual_track.model.GameDTO;
import fontys.sem3.individual_track.model.TicketDTO;
import fontys.sem3.individual_track.repository.entity.Game;
import fontys.sem3.individual_track.repository.entity.Team;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
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
@WebMvcTest(TicketsController.class)
class TicketsControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TicketsService ticketsService;

    private GameDTO createFakeGame() {
        Team homeTeam = new Team(1L, "HT", "Home city",
                "Home conf", "Home", "Home Homes", "Homes");
        Team visitorTeam = new Team(2L, "VT", "Visitor city",
                "Visitor conf", "Visitor", "Visitor Visitors", "Visitors");
        Game game = Game.builder().id(1L).date("18/04/2022").season(2022)
                .homeTeam(homeTeam).visitorTeam(visitorTeam).build();

        return GameDTOConverter.convertToDTO(game);
    }

    @Test
    void getAllTickets_shouldReturn200ResponseWithTicketsJsonObject() throws Exception {
        GameDTO gameDTO = createFakeGame();

        List<TicketDTO> responseTicketDTOs = List.of(
                TicketDTO.builder().id(1L).price(22)
                        .purchasedDate(LocalDate.now()).game(gameDTO)
                        .build(),
                TicketDTO.builder().id(2L).price(24)
                        .purchasedDate(LocalDate.now().plusDays(1)).game(gameDTO)
                        .build()
        );

        when(ticketsService.getAllTickets()).thenReturn(responseTicketDTOs);

        mockMvc.perform(get("/tickets"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                           [{"id":1,"price":22.0,"purchased_date":"14/04/2022",
                            "game": {"id": 1, "date":  "18/04/2022", "season":  2022,
                            "home_team":  {"id":  1, "abbreviation": "HT", "city": "Home city",
                             "conference": "Home conf", "division":  "Home", "full_name":  "Home Homes", "name":  "Homes"},
                             "visitor_team":  {"id":  2, "abbreviation": "VT", "city": "Visitor city",
                             "conference": "Visitor conf", "division":  "Visitor", "full_name":  "Visitor Visitors", "name":  "Visitors"}}},
                             {"id":2,"price":24.0,"purchased_date":"15/04/2022",
                            "game": {"id": 1, "date":  "18/04/2022", "season":  2022,
                            "home_team":  {"id":  1, "abbreviation": "HT", "city": "Home city",
                             "conference": "Home conf", "division":  "Home", "full_name":  "Home Homes", "name":  "Homes"},
                             "visitor_team":  {"id":  2, "abbreviation": "VT", "city": "Visitor city",
                             "conference": "Visitor conf", "division":  "Visitor", "full_name":  "Visitor Visitors", "name":  "Visitors"}}}]
                             
                                  
                        """));

        verify(ticketsService).getAllTickets();
    }

    @Test
    void getTicketById_shouldReturn200WithTicket_whenTicketFound() throws Exception {
        GameDTO gameDTO = createFakeGame();

        TicketDTO ticketDTO = TicketDTO.builder()
                .id(4L).price(22)
                .purchasedDate(LocalDate.now())
                .game(gameDTO)
                .build();

        when(ticketsService.getTicket(4L)).thenReturn(Optional.of(ticketDTO));

        mockMvc.perform(get("/tickets/4"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                            {"id":4,"price":22.0,"purchased_date":"14/04/2022",
                            "game": {"id": 1, "date":  "18/04/2022", "season":  2022,
                            "home_team":  {"id":  1, "abbreviation": "HT", "city": "Home city",
                             "conference": "Home conf", "division":  "Home", "full_name":  "Home Homes", "name":  "Homes"},
                             "visitor_team":  {"id":  2, "abbreviation": "VT", "city": "Visitor city",
                             "conference": "Visitor conf", "division":  "Visitor", "full_name":  "Visitor Visitors", "name":  "Visitors"}}}
                        """));

        verify(ticketsService).getTicket(4L);
    }

    @Test
    void getTicket_shouldReturn404Error_whenTicketNotFound() throws Exception {
        when(ticketsService.getTicket(4L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/tickets/4"))
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(ticketsService).getTicket(4L);
    }

    @Test
    void addTicket_shouldReturn201_whenRequestIsValid() throws Exception {
        GameDTO gameDTO = createFakeGame();

        CreateTicketRequestDTO expectedTicketRequest = CreateTicketRequestDTO.builder()
                .price(22)
                .purchasedDate(LocalDate.of(2022, 4, 14)).
                gameId(gameDTO.getId()).
                build();

        when(ticketsService.createTicket(expectedTicketRequest))
                .thenReturn(CreateTicketResponseDTO.builder()
                        .ticketId(14L)
                        .build());

        mockMvc.perform(post("/tickets")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content("""
                                {
                                    "price": "22",
                                    "purchased_date": "14/04/2022",
                                    "gameId": "1"
                                }
                                """))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json("""
                            { "ticketId":  14 }
                        """));

        verify(ticketsService).createTicket(expectedTicketRequest);
    }
}