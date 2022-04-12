package fontys.sem3.individual_track.repository;

import fontys.sem3.individual_track.repository.entity.Game;
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
class GamesRepositoryTest {
    @Autowired
    private EntityManager entityManager;

    @Autowired
    private GamesRepository gamesRepository;

    private Team saveTeam(String abbreviation,
                          String city,
                          String conference,
                          String division,
                          String fullName,
                          String name) {
        Team team = Team.builder()
                .abbreviation(abbreviation)
                .city(city)
                .conference(conference)
                .division(division)
                .fullName(fullName)
                .name(name)
                .build();

        entityManager.persist(team);
        return team;
    }


    @Test
    void createTeam_shouldSaveGameWithAllFields() {

        Team homeTeam = saveTeam("HTS", "Home Test", "West",
                "Atlantic", "Home Tests", "HTests");
        Team visitorTeam = saveTeam("VTS", "Visitor Test", "East",
                "Pacific", "Visitor Tests", "VTests");

        Game game = Game.builder()
                .date("04/12/2022")
                .season(2022)
                .homeTeam(homeTeam)
                .visitorTeam(visitorTeam)
                .build();

        Game savedGame = gamesRepository.save(game);
        assertNotNull(savedGame.getId());

        savedGame = entityManager.find(Game.class,
                savedGame.getId());

        Game expectedGame = Game.builder()
                .id(savedGame.getId())
                .date("04/12/2022")
                .season(2022)
                .homeTeam(homeTeam)
                .visitorTeam(visitorTeam)
                .build();

        assertEquals(expectedGame, savedGame);
    }

    @Test
    void existsById_shouldReturnTrue_whenIdFound() {
        Team homeTeam = saveTeam("HTS", "Home Test", "West",
                "Atlantic", "Home Tests", "HTests");
        Team visitorTeam = saveTeam("VTS", "Visitor Test", "East",
                "Pacific", "Visitor Tests", "VTests");

        Game game = Game.builder()
                .date("04/12/2022")
                .season(2022)
                .homeTeam(homeTeam)
                .visitorTeam(visitorTeam)
                .build();

        Game savedGame = gamesRepository.save(game);

        boolean actual = gamesRepository.existsById(savedGame.getId());
        assertTrue(actual);
    }

    @Test
    void existsById_shouldReturnFalse_whenIdNotFound() {
        boolean actual = gamesRepository.existsById(223L);
        assertFalse(actual);
    }
}