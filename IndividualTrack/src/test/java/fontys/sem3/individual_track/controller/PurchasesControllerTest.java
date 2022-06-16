package fontys.sem3.individual_track.controller;

import fontys.sem3.individual_track.business.AccessTokenDecoder;
import fontys.sem3.individual_track.business.PurchaseService;
import fontys.sem3.individual_track.model.CreatePurchaseRequestDTO;
import fontys.sem3.individual_track.model.CreatePurchaseResponseDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PurchasesController.class)
class PurchasesControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PurchaseService purchaseService;

    @MockBean
    private AccessTokenDecoder accessTokenDecoder;

    @Test
    void createPurchase_shouldReturn201_whenRequestIsValid() throws Exception {
        CreatePurchaseRequestDTO createPurchaseRequestDTO = CreatePurchaseRequestDTO.builder()
                .gameId(1L)
                .ticketId(1L)
                .quantity(2)
                .userId(3L)
                .build();

        when(purchaseService.createPurchase(createPurchaseRequestDTO))
                .thenReturn(CreatePurchaseResponseDTO.builder().purchaseId(12L).build());

        mockMvc.perform(post("/purchases")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content("""
                                {
                                    "ticket_id": "1",
                                    "game_id": "1",
                                    "quantity": "2",
                                    "user_id": "3"
                                }
                                """))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json("""
                            { "purchaseId":  12 }
                        """));
    }
}