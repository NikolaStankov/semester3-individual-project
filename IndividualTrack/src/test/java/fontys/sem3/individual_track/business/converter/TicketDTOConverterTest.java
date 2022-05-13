package fontys.sem3.individual_track.business.converter;

import fontys.sem3.individual_track.model.GameDTO;
import fontys.sem3.individual_track.model.TeamDTO;
import fontys.sem3.individual_track.model.TicketDTO;
import fontys.sem3.individual_track.repository.entity.Game;
import fontys.sem3.individual_track.repository.entity.Team;
import fontys.sem3.individual_track.repository.entity.Ticket;
import fontys.sem3.individual_track.repository.entity.TicketTypeEnum;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TicketDTOConverterTest {

    @Test
    void convertToDTO_shouldConvertAllTicketFieldsToDTO() {
        Ticket ticketToBeConverted = Ticket.builder()
                .id(1L)
                .price(22)
                .ticketType(TicketTypeEnum.STANDARD)
                .specification("Test conversion")
                .build();

        TicketDTO actualDTO = TicketDTOConverter.convertToDTO(ticketToBeConverted);

        TicketDTO expectedDTO = TicketDTO.builder()
                .id(1L)
                .price(22)
                .ticketType("STANDARD")
                .specification("Test conversion")
                .build();

        assertEquals(expectedDTO, actualDTO);
    }
}