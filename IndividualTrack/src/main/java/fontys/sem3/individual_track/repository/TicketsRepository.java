package fontys.sem3.individual_track.repository;

import fontys.sem3.individual_track.model.Ticket;

import java.util.List;

public interface TicketsRepository {
    List<Ticket> getTickets();
    Ticket getTicket(long ticketId);
    boolean saveTicket(Ticket ticket);
    boolean deleteTicket(long tickedId);
}
