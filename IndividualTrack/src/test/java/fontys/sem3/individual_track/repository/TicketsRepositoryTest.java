package fontys.sem3.individual_track.repository;

import fontys.sem3.individual_track.repository.entity.Game;
import fontys.sem3.individual_track.repository.entity.Team;
import fontys.sem3.individual_track.repository.entity.Ticket;
import fontys.sem3.individual_track.repository.entity.TicketTypeEnum;
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
class TicketsRepositoryTest {
    @Autowired
    private EntityManager entityManager;

    @Autowired
    private TicketsRepository ticketsRepository;

    private Game saveGame(String date, int season) {

        Team homeTeam = Team.builder()
                .abbreviation("HTS").city("Home Test").conference("West")
                .division("Atlantic").fullName("Home Tests").name("HTests").build();

        Team visitorTeam = Team.builder()
                .abbreviation("VTS").city("Visitor Test").conference("East")
                .division("Pacific").fullName("Visitor Tests").name("VTests").build();

        entityManager.persist(homeTeam);
        entityManager.persist(visitorTeam);

        Game game = Game.builder()
                .date(date)
                .season(season)
                .homeTeam(homeTeam)
                .visitorTeam(visitorTeam)
                .build();

        entityManager.persist(game);
        return game;
    }

    @Test
    void createTicket_shouldSaveTicketWithAllFields() {
        Game game = saveGame("22/07/2022", 2022);

        Ticket ticket = Ticket.builder()
                .ticketType(TicketTypeEnum.STANDARD)
                .price(22)
                .specification("This is a standard ticket for testing")
                .build();

        Ticket savedTicket = ticketsRepository.save(ticket);
        assertNotNull(savedTicket.getId());

        savedTicket = entityManager.find(Ticket.class,
                savedTicket.getId());

        Ticket expectedTicket = Ticket.builder()
                .id(savedTicket.getId())
                .ticketType(TicketTypeEnum.STANDARD)
                .price(22)
                .specification("This is a standard ticket for testing")
                .build();

        assertEquals(expectedTicket, savedTicket);
    }

    @Test
    void existsById_shouldReturnTrue_whenIdFound() {
        Game game = saveGame("11/04/2023", 2023);

        Ticket ticket = Ticket.builder()
                .ticketType(TicketTypeEnum.STANDARD)
                .price(20)
                .specification("This is a standard ticket for testing")
                .build();

        Ticket savedTicket = ticketsRepository.save(ticket);

        boolean actual = ticketsRepository.existsById(savedTicket.getId());
        assertTrue(actual);
    }

    @Test
    void existsById_shouldReturnFalse_whenIdNotFound() {
        boolean actual = ticketsRepository.existsById(2223L);
        assertFalse(actual);
    }
}