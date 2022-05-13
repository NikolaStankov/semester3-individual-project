package fontys.sem3.individual_track.business.converter;

import fontys.sem3.individual_track.model.PurchaseDTO;
import fontys.sem3.individual_track.repository.entity.Purchase;

public class PurchaseDTOConverter {
    private PurchaseDTOConverter() {

    }

    public static PurchaseDTO convertToDTO(Purchase purchase) {
        return PurchaseDTO.builder()
                .id(purchase.getId())
                .ticket(TicketDTOConverter.convertToDTO(purchase.getTicket()))
                .game(GameDTOConverter.convertToDTO(purchase.getGame()))
                .quantity(purchase.getQuantity())
                .user(UserDTOConverter.convertToDTO(purchase.getUser()))
                .build();
    }
}
