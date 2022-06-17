package fontys.sem3.individual_track.controller;

import fontys.sem3.individual_track.business.AccessTokenDecoder;
import fontys.sem3.individual_track.business.PurchaseService;
import fontys.sem3.individual_track.business.UsersService;
import fontys.sem3.individual_track.business.converter.GameDTOConverter;
import fontys.sem3.individual_track.business.converter.TeamDTOConverter;
import fontys.sem3.individual_track.business.converter.TicketDTOConverter;
import fontys.sem3.individual_track.business.converter.UserDTOConverter;
import fontys.sem3.individual_track.business.exception.InvalidUserIdException;
import fontys.sem3.individual_track.model.PurchaseDTO;
import fontys.sem3.individual_track.repository.entity.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PurchasesByUserController.class)
class PurchasesByUserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PurchaseService purchaseService;

    @MockBean
    private UsersService usersService;

    @MockBean
    private AccessTokenDecoder accessTokenDecoder;

    private User createFakeUser() {
        User fakeUser = User.builder()
                .id(3L)
                .username("test_username")
                .password("test_password")
                .userRoles(Set.of(UserRole.builder().build()))
                .build();

        fakeUser.setUserRoles(Set.of(new UserRole(null, RoleEnum.USER, fakeUser)));

        return fakeUser;
    }

    private Ticket createFakeTicket() {
        return Ticket.builder().id(22L).ticketType(TicketTypeEnum.STANDARD).price(20)
                .specification("Standard ticket with seats in the back of the arena").build();
    }

    private Team createHomeTeam() {
        return new Team(1L, "HT", "Home city",
                "Home conf", "Home", "Home Homes", "Homes");
    }

    private Team createVisitorTeam() {
        return new Team(2L, "VT", "Visitor city",
                "Visitor conf", "Visitor", "Visitor Visitors", "Visitors");
    }

    private Game createFakeGame() {
        return Game.builder().id(1L).date("18/04/2022").season(2022)
                .homeTeam(this.createHomeTeam()).visitorTeam(this.createVisitorTeam()).build();
    }

    private List<PurchaseDTO> createExpectedPurchases() {
        return List.of(PurchaseDTO.builder()
                .id(2L)
                .ticket(TicketDTOConverter.convertToDTO(this.createFakeTicket()))
                .game(GameDTOConverter.convertToDTO(this.createFakeGame()))
                .quantity(3)
                .user(UserDTOConverter.convertToDTO(this.createFakeUser()))
                .build());
    }

    @Test
    void getPurchasesByUser() throws Exception {
        User fakeUser = this.createFakeUser();
        List<PurchaseDTO> expectedPurchases = this.createExpectedPurchases();

        when(usersService.getUser(fakeUser.getId())).thenReturn(Optional.of(UserDTOConverter.convertToDTO(fakeUser)));
        when(purchaseService.getPurchasesByUser(UserDTOConverter.convertToDTO(fakeUser))).thenReturn(expectedPurchases);

        mockMvc.perform(get("/users/3/purchases"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", APPLICATION_JSON_VALUE))
                .andExpect(content().json("""
                        [{"id": 2,
                        "ticket":  {"id":  22, "ticket_type":  "STANDARD", "price":  20, "specification":  "Standard ticket with seats in the back of the arena"},
                        "game":  {"id": 1, "date":  "18/04/2022", "season":  2022,
                         "home_team":  {"id":  1, "abbreviation": "HT", "city": "Home city",
                          "conference": "Home conf", "division":  "Home", "full_name":  "Home Homes", "name":  "Homes"},
                          "visitor_team":  {"id":  2, "abbreviation": "VT", "city": "Visitor city",
                          "conference": "Visitor conf", "division":  "Visitor", "full_name":  "Visitor Visitors", "name":  "Visitors"}},
                          "quantity":  3, "user":  {"id":3,"username":  "test_username","role": ["USER"]}}]
                          """));

        verify(usersService).getUser(fakeUser.getId());
        verify(purchaseService).getPurchasesByUser(UserDTOConverter.convertToDTO(fakeUser));
    }

    @Test
    void getPurchasesByUser_shouldThrowInvalidUserIdException_whenUserNotFound() throws Exception {
        User fakeUser = this.createFakeUser();

        when(usersService.getUser(fakeUser.getId())).thenReturn(Optional.empty());

        String expectedMessage = "Invalid user id.";

        try {
            mockMvc.perform(get("/users/3/purchases"));
        } catch (InvalidUserIdException exception) {
            assertEquals(expectedMessage, exception.getMessage());
        }
    }
}