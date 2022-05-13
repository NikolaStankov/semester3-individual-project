package fontys.sem3.individual_track.business.impl;

import fontys.sem3.individual_track.business.converter.TicketDTOConverter;
import fontys.sem3.individual_track.business.validator.GameIdValidator;
import fontys.sem3.individual_track.model.*;
import fontys.sem3.individual_track.repository.TicketsRepository;
import fontys.sem3.individual_track.repository.entity.Game;
import fontys.sem3.individual_track.repository.entity.Team;
import fontys.sem3.individual_track.repository.entity.Ticket;
import fontys.sem3.individual_track.repository.entity.TicketTypeEnum;
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
        Team homeTeam = new Team(1L, "HT", "Home city",
                "Home conf", "Home", "Home Homes", "Homes");
        Team visitorTeam = new Team(2L, "VT", "Visitor city",
                "Visitor conf", "Visitor", "Visitor Visitors", "Visitors");

        return Game.builder().id(1L).date("18/04/2022").season(2022)
                .homeTeam(homeTeam).visitorTeam(visitorTeam).build();
    }

    @Test
    void getAllTickets_shouldReturnAllTicketsFromRepository() {
        Game game = createFakeGame();

        List<Ticket> ticketList = List.of(
                Ticket.builder().id(1L).ticketType(TicketTypeEnum.PREMIUM).price(50)
                        .specification("Premium ticket with seats in the middle of the arena").build(),
                Ticket.builder().id(2L).ticketType(TicketTypeEnum.STANDARD).price(20)
                        .specification("Standard ticket with seats in the back of the arena").build()
        );

        when(ticketsRepositoryMock.findAll()).thenReturn(ticketList);

        List<TicketDTO> actualResponse = ticketsService.getAllTickets();
        List<TicketDTO> expectedResponse = ticketList.stream().map(TicketDTOConverter::convertToDTO).toList();

        assertEquals(expectedResponse, actualResponse);
        verify(ticketsRepositoryMock).findAll();
    }

    @Test
    void getTicket_shouldReturnTicketWithGivenId() {
        Ticket ticket = Ticket.builder().id(22L).ticketType(TicketTypeEnum.STANDARD).price(20)
                .specification("Standard ticket with seats in the back of the arena").build();

        when(ticketsRepositoryMock.findById(22L)).thenReturn(Optional.of(ticket));

        Optional<TicketDTO> ticketOptional = ticketsService.getTicket(22L);

        TicketDTO actualTicketDTO = ticketOptional.orElseThrow();
        TicketDTO expectedTicketDTO = TicketDTOConverter.convertToDTO(ticket);

        assertEquals(expectedTicketDTO, actualTicketDTO);
        verify(ticketsRepositoryMock).findById(22L);
    }

    @Test
    void createTicket() {
        Game game = Game.builder().id(1L).build();

        Ticket expectedTicketToSave = Ticket.builder().ticketType(TicketTypeEnum.STANDARD).price(20)
                .specification("Standard ticket with seats in the back of the arena").build();

        Ticket savedTicket = Ticket.builder().id(1L).ticketType(TicketTypeEnum.STANDARD).price(20)
                .specification("Standard ticket with seats in the back of the arena").build();

        when(ticketsRepositoryMock.save(expectedTicketToSave)).thenReturn(savedTicket);

        CreateTicketRequestDTO ticketRequest = CreateTicketRequestDTO.builder()
                .ticketType("STANDARD")
                .price(20)
                .specification("Standard ticket with seats in the back of the arena")
                .build();

        CreateTicketResponseDTO actualTicketResponse = ticketsService.createTicket(ticketRequest);
        CreateTicketResponseDTO expectedTicketResponse = CreateTicketResponseDTO.builder().ticketId(1L).build();

        assertEquals(expectedTicketResponse, actualTicketResponse);
        verify(gameIdValidator).validateGameId(1L);
        verify(ticketsRepositoryMock).save(expectedTicketToSave);
    }
}