package fontys.sem3.individual_track.repository;

import fontys.sem3.individual_track.business.converter.GameDTOConverter;
import fontys.sem3.individual_track.model.GameDTO;
import fontys.sem3.individual_track.model.TicketDTO;
import fontys.sem3.individual_track.repository.entity.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PurchaseRepositoryTest {
    @Autowired
    private PurchaseRepository purchaseRepository;

    private Game createFakeGame() {
        Team homeTeam = new Team(1L, "HT", "Home city",
                "Home conf", "Home", "Home Homes", "Homes");
        Team visitorTeam = new Team(2L, "VT", "Visitor city",
                "Visitor conf", "Visitor", "Visitor Visitors", "Visitors");
        Game game = Game.builder().id(1L).date("18/04/2022").season(2022)
                .homeTeam(homeTeam).visitorTeam(visitorTeam).build();

        return game;
    }

    private Ticket createFakeTicket() {
        return Ticket.builder().id(1L).price(22)
                .ticketType(TicketTypeEnum.PREMIUM)
                .specification("Test fake ticket")
                .build();
    }

    private User createFakeUser() {
        User fakeUser = User.builder()
                .id(3L)
                .username("test_username")
                .password("test_password")
                .build();

        fakeUser.setUserRoles(Set.of(new UserRole(2l, RoleEnum.USER, fakeUser)));

        return  fakeUser;
    }

    @Test
    void existsById_shouldReturnPurchaseWhenFound() {
        Purchase purchase = Purchase.builder()
                .game(this.createFakeGame())
                .quantity(2)
                .ticket(this.createFakeTicket())
                .user(this.createFakeUser())
                .build();

        Purchase savedPurchase = purchaseRepository.save(purchase);

        boolean actual = purchaseRepository.existsById(savedPurchase.getId());

        assertTrue(actual);
    }

    @Test
    void findAllByUser() {
        //todo
    }
}