package fontys.sem3.individual_track.repository.impl;

import fontys.sem3.individual_track.model.Ticket;
import fontys.sem3.individual_track.repository.TicketsRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository("nbaTickets")
@Primary
public class NBAGamesTicketsRepository implements TicketsRepository {

    private final List<Ticket> tickets;

    public NBAGamesTicketsRepository() {
        this.tickets = new ArrayList<>();
        this.tickets.add(new Ticket(1, 20, "Lakers vs Spurs"));
        this.tickets.add(new Ticket(1, 20, "Lakers vs GSW"));
        this.tickets.add(new Ticket(1, 25, "GSW vs Charlotte Hornets"));
        this.tickets.add(new Ticket(1, 15, "Orlando Magic vs Wizards"));
    }

    @Override
    public List<Ticket> selectAllTickets() {
        return this.tickets;
    }

    @Override
    public Ticket selectTicket(long ticketId) {
        for (Ticket ticket : this.tickets) {
            if (ticket.getTicketId() == ticketId)
                return ticket;
        }
        return null;
    }

    @Override
    public boolean insertTicket(Ticket ticket) {
        if (this.selectTicket(ticket.getTicketId()) != null){
            return false;
        }

        this.tickets.add(ticket);
        return true;
    }

    @Override
    public boolean deleteTicket(long tickedId) {
        if (this.selectTicket(tickedId) == null){
            return false;
        }

        Ticket ticket = this.selectTicket(tickedId);
        return this.tickets.remove(ticket);
    }
}
