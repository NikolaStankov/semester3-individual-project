package fontys.sem3.individual_track.repository;

import fontys.sem3.individual_track.model.TicketDTO;

import java.util.List;

public interface TicketsRepository {
    List<TicketDTO> selectAllTickets();

    TicketDTO selectTicket(long ticketId);

    boolean insertTicket(TicketDTO ticket);

    boolean deleteTicket(long tickedId);
}
