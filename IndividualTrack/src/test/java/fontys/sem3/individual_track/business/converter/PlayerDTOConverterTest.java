package fontys.sem3.individual_track.business.converter;

import fontys.sem3.individual_track.model.PlayerDTO;
import fontys.sem3.individual_track.model.TeamDTO;
import fontys.sem3.individual_track.repository.entity.Player;
import fontys.sem3.individual_track.repository.entity.Team;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerDTOConverterTest {

    @Test
    void convertToDTO_shouldConvertAllPlayerFieldsToDTO() {
        Player playerToBeConverted = Player.builder()
                .id(1L)
                .firstName("Nikola")
                .lastName("Stankovic")
                .position("SG")
                .team(new Team(2L, "VT", "Visitor city",
                        "Visitor conf", "Visitor", "Visitor Visitors", "Visitors"))
                .build();

        PlayerDTO actualDTO = PlayerDTOConverter.convertToDTO(playerToBeConverted);


        PlayerDTO expectedDTO = PlayerDTO.builder()
                .id(1L)
                .firstName("Nikola")
                .lastName("Stankovic")
                .position("SG")
                .team(TeamDTO.builder()
                        .id(2L).abbreviation("VT").city("Visitor city").conference("Visitor conf")
                        .division("Visitor").fullName("Visitor Visitors").name("Visitors")
                        .build())
                .build();

        assertEquals(expectedDTO, actualDTO);
    }
}