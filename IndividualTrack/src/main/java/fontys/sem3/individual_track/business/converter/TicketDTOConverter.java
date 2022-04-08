package fontys.sem3.individual_track.business.converter;

import fontys.sem3.individual_track.model.TicketDTO;
import fontys.sem3.individual_track.repository.entity.Ticket;

public class TicketDTOConverter {
    private TicketDTOConverter() {
    }

    public static TicketDTO convertToDTO(Ticket ticket) {
        return TicketDTO.builder()
                .id(ticket.getId())
                .price(ticket.getPrice())
                .purchasedDate(ticket.getPurchasedDate())
                .game(GameDTOConverter.convertToDTO(ticket.getGame()))
                .build();
    }
}
