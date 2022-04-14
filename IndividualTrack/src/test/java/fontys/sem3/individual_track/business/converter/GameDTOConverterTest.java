package fontys.sem3.individual_track.business.converter;

import fontys.sem3.individual_track.model.GameDTO;
import fontys.sem3.individual_track.model.TeamDTO;
import fontys.sem3.individual_track.repository.entity.Game;
import fontys.sem3.individual_track.repository.entity.Team;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameDTOConverterTest {

    @Test
    void convertToDTO_shouldConvertAllGameFieldsToDTO() {
        Game gameToBeConverted = Game.builder()
                .id(1L)
                .date("13/04/2022")
                .season(2022)
                .homeTeam(new Team(1L, "HT", "Home city",
                        "Home conf", "Home", "Home Homes", "Homes"))
                .visitorTeam(new Team(2L, "VT", "Visitor city",
                        "Visitor conf", "Visitor", "Visitor Visitors", "Visitors"))
                .build();

        GameDTO actualDTO = GameDTOConverter.convertToDTO(gameToBeConverted);


        GameDTO expectedDTO = GameDTO.builder()
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
                .build();

        assertEquals(expectedDTO, actualDTO);
    }
}