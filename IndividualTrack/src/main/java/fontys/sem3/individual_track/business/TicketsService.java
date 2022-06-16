package fontys.sem3.individual_track.business;

import fontys.sem3.individual_track.model.CreateTicketRequestDTO;
import fontys.sem3.individual_track.model.CreateTicketResponseDTO;
import fontys.sem3.individual_track.model.TicketDTO;
import fontys.sem3.individual_track.repository.entity.TicketTypeEnum;

import java.util.List;
import java.util.Optional;

public interface TicketsService {
    List<TicketDTO> getAllTickets();

    Optional<TicketDTO> getTicket(long ticketId);

    CreateTicketResponseDTO createTicket(CreateTicketRequestDTO ticketRequest);

    TicketTypeEnum[] getAllTicketTypes();
}
