package fontys.sem3.individual_track.business;

import fontys.sem3.individual_track.model.TicketDTO;

import java.util.List;

public interface TicketsService {
    List<TicketDTO> getAllTickets();

    TicketDTO getTicket(long ticketId);

    boolean addTicket(TicketDTO ticket);

    boolean removeTicket(long tickedId);
}
