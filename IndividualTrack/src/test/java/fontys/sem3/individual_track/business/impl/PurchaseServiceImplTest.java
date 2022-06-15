package fontys.sem3.individual_track.business.impl;

import fontys.sem3.individual_track.business.converter.PurchaseDTOConverter;
import fontys.sem3.individual_track.business.converter.TeamDTOConverter;
import fontys.sem3.individual_track.business.validator.GameIdValidator;
import fontys.sem3.individual_track.business.validator.TicketIdValidator;
import fontys.sem3.individual_track.business.validator.UserIdValidator;
import fontys.sem3.individual_track.model.PurchaseDTO;
import fontys.sem3.individual_track.model.TeamDTO;
import fontys.sem3.individual_track.repository.PurchaseRepository;
import fontys.sem3.individual_track.repository.entity.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PurchaseServiceImplTest {
    @Mock
    private PurchaseRepository purchaseRepositoryMock;

    @Mock
    private UserIdValidator userIdValidator;

    @Mock
    private GameIdValidator gameIdValidator;

    @Mock
    private TicketIdValidator ticketIdValidator;

    @InjectMocks
    private PurchaseServiceImpl purchaseService;

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

        return fakeUser;
    }

    @Test
    void getAllPurchases_shouldReturnAllPurchasesFromRepository() {
        List<Purchase> purchaseList = List.of(Purchase.builder()
                .id(3L)
                .user(this.createFakeUser())
                .game(this.createFakeGame())
                .ticket(this.createFakeTicket())
                .quantity(4)
                .build());
        when(purchaseRepositoryMock.findAll()).thenReturn(purchaseList);

        List<PurchaseDTO> actualResponse = purchaseService.getAllPurchases();
        List<PurchaseDTO> expectedResponse = purchaseList.stream().map(PurchaseDTOConverter::convertToDTO).toList();

        assertEquals(expectedResponse, actualResponse);
        verify(purchaseRepositoryMock).findAll();
    }

    @Test
    void getPurchase_shouldReturnPurchaseWithGivenId() {
        Purchase purchase = Purchase.builder()
                .id(3L)
                .user(this.createFakeUser())
                .game(this.createFakeGame())
                .ticket(this.createFakeTicket())
                .quantity(4)
                .build();

        when(purchaseRepositoryMock.findById(3L)).thenReturn(Optional.of(purchase));

        Optional<PurchaseDTO> optionalPurchaseDTO = purchaseService.getPurchase(3L);

        PurchaseDTO actualPurchaseDTO = optionalPurchaseDTO.orElseThrow();
        PurchaseDTO expectedPurchaseDTO = PurchaseDTOConverter.convertToDTO(purchase);

        assertEquals(expectedPurchaseDTO, actualPurchaseDTO);
        verify(purchaseRepositoryMock).findById(3L);
    }

    @Test
    void createPurchase_shouldSavePurchaseWithAllField() {

    }

    @Test
    void removePurchase() {
    }

    @Test
    void getPurchasesByUser() {
    }
}