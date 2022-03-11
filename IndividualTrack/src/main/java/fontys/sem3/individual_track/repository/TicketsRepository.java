package fontys.sem3.individual_track.repository;

import fontys.sem3.individual_track.model.Ticket;

import java.util.List;

public interface TicketsRepository {
    List<Ticket> selectAllTickets();
    Ticket selectTicket(long ticketId);
    boolean insertTicket(Ticket ticket);
    boolean deleteTicket(long tickedId);
}
