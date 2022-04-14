package fontys.sem3.individual_track.business.impl;

import fontys.sem3.individual_track.business.converter.TicketDTOConverter;
import fontys.sem3.individual_track.business.validator.GameIdValidator;
import fontys.sem3.individual_track.model.*;
import fontys.sem3.individual_track.repository.TicketsRepository;
import fontys.sem3.individual_track.repository.entity.Game;
import fontys.sem3.individual_track.repository.entity.Team;
import fontys.sem3.individual_track.repository.entity.Ticket;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TicketsServiceImplTest {

    @Mock
    TicketsRepository ticketsRepositoryMock;

    @Mock
    GameIdValidator gameIdValidator;

    @InjectMocks
    TicketsServiceImpl ticketsService;

    private Game createFakeGame() {
        Team homeTeam = new Team(1l, "HT", "Home city",
                "Home conf", "Home", "Home Homes", "Homes");
        Team visitorTeam = new Team(2l, "VT", "Visitor city",
                "Visitor conf", "Visitor", "Visitor Visitors", "Visitors");

        return Game.builder().id(1L).date("18/04/2022").season(2022)
                .homeTeam(homeTeam).visitorTeam(visitorTeam).build();
    }

    @Test
    void getAllTickets_shouldReturnAllTicketsFromRepository() {
        Game game = createFakeGame();

        List<Ticket> ticketList = List.of(
                Ticket.builder().id(1L).price(22)
                        .purchasedDate(LocalDate.now()).game(game).build(),
                Ticket.builder().id(2L).price(32)
                        .purchasedDate(LocalDate.now().plusDays(2)).game(game).build()
        );

        when(ticketsRepositoryMock.findAll()).thenReturn(ticketList);

        List<TicketDTO> actualResponse = ticketsService.getAllTickets();
        List<TicketDTO> expectedResponse = ticketList.stream().map(TicketDTOConverter::convertToDTO).toList();

        assertEquals(expectedResponse, actualResponse);
        verify(ticketsRepositoryMock).findAll();
    }

    @Test
    void getTicket_shouldReturnTicketWithGivenId() {
        Game game = createFakeGame();

        Ticket ticket = Ticket.builder().id(12L).price(22)
                .purchasedDate(LocalDate.now()).game(game).build();

        when(ticketsRepositoryMock.findById(12L)).thenReturn(Optional.of(ticket));

        Optional<TicketDTO> ticketOptional = ticketsService.getTicket(12L);

        TicketDTO actualTicketDTO = ticketOptional.orElseThrow();
        TicketDTO expectedTicketDTO = TicketDTOConverter.convertToDTO(ticket);

        assertEquals(expectedTicketDTO, actualTicketDTO);
        verify(ticketsRepositoryMock).findById(12L);
    }

    @Test
    void createTicket() {
        Game game = Game.builder().id(1L).build();

        Ticket expectedTicketToSave = Ticket.builder().price(22)
                .purchasedDate(LocalDate.now())
                .game(game).build();

        Ticket savedTicket = Ticket.builder().id(1L).price(22)
                .purchasedDate(LocalDate.now())
                .game(game).build();

        when(ticketsRepositoryMock.save(expectedTicketToSave)).thenReturn(savedTicket);

        CreateTicketRequestDTO ticketRequest = CreateTicketRequestDTO.builder()
                .price(22)
                .purchasedDate(LocalDate.now())
                .gameId(game.getId())
                .build();

        CreateTicketResponseDTO actualTicketResponse = ticketsService.createTicket(ticketRequest);
        CreateTicketResponseDTO expectedTicketResponse = CreateTicketResponseDTO.builder().ticketId(1L).build();

        assertEquals(expectedTicketResponse, actualTicketResponse);
        verify(gameIdValidator).validateGameId(1L);
        verify(ticketsRepositoryMock).save(expectedTicketToSave);
    }
}