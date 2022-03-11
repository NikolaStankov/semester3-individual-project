package fontys.sem3.individual_track.business;

import fontys.sem3.individual_track.model.Ticket;

import java.util.List;

public interface TicketsService {
    List<Ticket> getAllTickets();
    Ticket getTicket(long ticketId);
    boolean addTicket(Ticket ticket);
    boolean removeTicket(long tickedId);
}
