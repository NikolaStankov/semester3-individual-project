package fontys.sem3.individual_track.repository;

import fontys.sem3.individual_track.repository.entity.Team;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TeamsRepositoryTest {
    @Autowired
    private EntityManager entityManager;

    @Autowired
    private TeamsRepository teamsRepository;

    @Test
    void createTeam_shouldSaveTeamWithAllFields() {
        Team team = Team.builder()
                .abbreviation("TS")
                .city("Test")
                .conference("East")
                .division("Atlantic")
                .fullName("Testing Tests")
                .name("Tests")
                .build();

        Team savedTeam = teamsRepository.save(team);
        assertNotNull(savedTeam.getId());

        savedTeam = entityManager.find(Team.class,
                savedTeam.getId());

        Team expectedTeam = Team.builder()
                .id(savedTeam.getId())
                .abbreviation("TS")
                .city("Test")
                .conference("East")
                .division("Atlantic")
                .fullName("Testing Tests")
                .name("Tests")
                .build();

        assertEquals(expectedTeam, savedTeam);
    }

    @Test
    void existsById_shouldReturnTrue_whenIdFound() {
        Team team = Team.builder()
                .abbreviation("TS")
                .city("Test")
                .conference("East")
                .division("Atlantic")
                .fullName("Testing Tests")
                .name("Tests")
                .build();

        Team savedTeam = teamsRepository.save(team);

        boolean actual = teamsRepository.existsById(savedTeam.getId());
        assertTrue(actual);
    }

    @Test
    void existsById_shouldReturnFalse_whenIdNotFound() {
        boolean actual = teamsRepository.existsById(223L);
        assertFalse(actual);
    }
}