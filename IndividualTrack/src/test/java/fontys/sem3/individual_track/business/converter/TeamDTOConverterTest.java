package fontys.sem3.individual_track.business.converter;

import fontys.sem3.individual_track.model.TeamDTO;
import fontys.sem3.individual_track.repository.entity.Team;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TeamDTOConverterTest {

    @Test
    void convertToDTO_shouldConvertAllTeamFieldsToDTO() {
        Team teamToBeConverted = Team.builder().id(1L).abbreviation("T1").city("City1").conference("West")
                .division("Atlantic").fullName("Team Teams1").name("Team1").build();

        TeamDTO actualDTO = TeamDTOConverter.convertToDTO(teamToBeConverted);

        TeamDTO expectedDTO = TeamDTO.builder()
                .id(1L).abbreviation("T1")
                .city("City1")
                .conference("West")
                .division("Atlantic")
                .fullName("Team Teams1")
                .name("Team1")
                .build();

        assertEquals(expectedDTO, actualDTO);
    }
}