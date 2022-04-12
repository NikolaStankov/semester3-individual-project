package fontys.sem3.individual_track.repository;

import fontys.sem3.individual_track.repository.entity.Player;
import fontys.sem3.individual_track.repository.entity.Team;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class PlayersRepositoryTest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private PlayersRepository playersRepository;

    @Test
    void createPlayer_shouldSavePlayerWithAllFields() {

        Team team = Team.builder()
                .abbreviation("TS")
                .city("Test")
                .conference("East")
                .division("Atlantic")
                .fullName("Testing Tests")
                .name("Tests")
                .build();

        entityManager.persist(team);

        Player player = Player.builder()
                .firstName("Test")
                .lastName("Testov")
                .position("SF")
                .team(team)
                .build();

        Player savedPlayer = playersRepository.save(player);
        assertNotNull(savedPlayer.getId());

        savedPlayer = entityManager.find(Player.class,
                savedPlayer.getId());

        Player expectedPlayer = Player.builder()
                .id(savedPlayer.getId())
                .firstName("Test")
                .lastName("Testov")
                .position("SF")
                .team(team)
                .build();

        assertEquals(expectedPlayer, savedPlayer);
    }
}