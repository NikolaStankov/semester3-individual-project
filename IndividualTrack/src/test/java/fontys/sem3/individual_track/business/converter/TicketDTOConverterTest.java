package fontys.sem3.individual_track.business.converter;

import fontys.sem3.individual_track.model.GameDTO;
import fontys.sem3.individual_track.model.TeamDTO;
import fontys.sem3.individual_track.model.TicketDTO;
import fontys.sem3.individual_track.repository.entity.Game;
import fontys.sem3.individual_track.repository.entity.Team;
import fontys.sem3.individual_track.repository.entity.Ticket;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TicketDTOConverterTest {

    @Test
    void convertToDTO_shouldConvertAllTicketFieldsToDTO() {
        Ticket ticketToBeConverted = Ticket.builder()
                .id(1L)
                .price(22)
                .purchasedDate(LocalDate.now())
                .game(Game.builder()
                        .id(1L)
                        .date("13/04/2022")
                        .season(2022)
                        .homeTeam(new Team(1L, "HT", "Home city",
                                "Home conf", "Home", "Home Homes", "Homes"))
                        .visitorTeam(new Team(2L, "VT", "Visitor city",
                                "Visitor conf", "Visitor", "Visitor Visitors", "Visitors"))
                        .build())
                .build();

        TicketDTO actualDTO = TicketDTOConverter.convertToDTO(ticketToBeConverted);

        TicketDTO expectedDTO = TicketDTO.builder()
                .id(1L)
                .price(22)
                .purchasedDate(LocalDate.now())
                .game(GameDTO.builder()
                        .id(1L)
                        .date("13/04/2022")
                        .season(2022)
                        .homeTeam(TeamDTO.builder()
                                .id(1L).abbreviation("HT").city("Home city").conference("Home conf")
                                .division("Home").fullName("Home Homes").name("Homes")
                                .build())
                        .visitorTeam(TeamDTO.builder()
                                .id(2L).abbreviation("VT").city("Visitor city").conference("Visitor conf")
                                .division("Visitor").fullName("Visitor Visitors").name("Visitors")
                                .build())
                        .build())
                .build();

        assertEquals(expectedDTO, actualDTO);
    }
}