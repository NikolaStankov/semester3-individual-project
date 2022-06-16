package fontys.sem3.individual_track.business.impl;

import fontys.sem3.individual_track.business.converter.PurchaseDTOConverter;
import fontys.sem3.individual_track.business.converter.UserDTOConverter;
import fontys.sem3.individual_track.business.validator.GameIdValidator;
import fontys.sem3.individual_track.business.validator.TicketIdValidator;
import fontys.sem3.individual_track.business.validator.UserIdValidator;
import fontys.sem3.individual_track.model.CreatePurchaseRequestDTO;
import fontys.sem3.individual_track.model.CreatePurchaseResponseDTO;
import fontys.sem3.individual_track.model.PurchaseDTO;
import fontys.sem3.individual_track.model.UserDTO;
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
        Purchase purchaseToSave = Purchase.builder()
                .user(User.builder().id(3L).build())
                .game(Game.builder().id(1L).build())
                .ticket(Ticket.builder().id(1L).build())
                .quantity(4)
                .build();

        Purchase savedPurchase = Purchase.builder()
                .id(3L)
                .user(this.createFakeUser())
                .game(this.createFakeGame())
                .ticket(this.createFakeTicket())
                .quantity(4)
                .build();

        when(purchaseRepositoryMock.save(purchaseToSave)).thenReturn(savedPurchase);

        CreatePurchaseRequestDTO createPurchaseRequestDTO = CreatePurchaseRequestDTO.builder()
                .ticketId(purchaseToSave.getTicket().getId())
                .quantity(4)
                .userId(purchaseToSave.getUser().getId())
                .gameId(purchaseToSave.getGame().getId())
                .build();

        CreatePurchaseResponseDTO expectedResponse = CreatePurchaseResponseDTO.builder().purchaseId(3L).build();
        CreatePurchaseResponseDTO actualResponse = purchaseService.createPurchase(createPurchaseRequestDTO);

        assertEquals(expectedResponse, actualResponse);
        verify(purchaseRepositoryMock).save(purchaseToSave);
    }

    @Test
    void getPurchasesByUser_shouldReturnAllPurchasesMadeByGivenUser() {
        User userToQueryOn = User.builder()
                .id(3L)
                .username("test_username")
                .build();

        Purchase purchase = Purchase.builder()
                .id(3L)
                .user(this.createFakeUser())
                .game(this.createFakeGame())
                .ticket(this.createFakeTicket())
                .quantity(4)
                .build();

        List<Purchase> purchasesByUser = List.of(purchase);

        UserDTO userDTO = UserDTOConverter.convertToDTO(this.createFakeUser());

        when(purchaseRepositoryMock.findAllByUser(userToQueryOn)).thenReturn(purchasesByUser);

        List<PurchaseDTO> expectedDTOs = List.of(PurchaseDTOConverter.convertToDTO(purchase));
        List<PurchaseDTO> actualDTOs = purchaseService.getPurchasesByUser(userDTO);

        assertEquals(expectedDTOs, actualDTOs);
        verify(purchaseRepositoryMock).findAllByUser(userToQueryOn);
    }
}