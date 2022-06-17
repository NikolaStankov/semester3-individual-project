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
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TicketsServiceImplTest {

    @Mock
    private TicketsRepository ticketsRepositoryMock;

    @Mock
    private GameIdValidator gameIdValidator;

    @InjectMocks
    private TicketsServiceImpl ticketsService;

    @Test
    void getAllTickets_shouldReturnAllTicketsFromRepository() {
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
        verify(ticketsRepositoryMock).save(expectedTicketToSave);
    }

    @Test
    void getAllTicketTypes_shouldReturnAllValuesOfTheTicketTypeEnum(){
        TicketTypeEnum[] actualTicketTypes = TicketTypeEnum.values();
        TicketTypeEnum[] returnedTicketTypes = ticketsService.getAllTicketTypes();

        assertEquals(Arrays.stream(actualTicketTypes).toList(), Arrays.stream(returnedTicketTypes).toList());
    }
}