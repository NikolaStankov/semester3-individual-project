package fontys.sem3.individual_track.repository.impl;

import fontys.sem3.individual_track.model.Ticket;
import fontys.sem3.individual_track.repository.TicketsRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Repository("nbaTickets")
@Primary
public class NBAGamesTicketsRepository implements TicketsRepository {

    private final List<Ticket> tickets;

    public NBAGamesTicketsRepository() {
        this.tickets = new ArrayList<>();
        this.tickets.add(new Ticket(1, 20, LocalDate.now(), "Clippers vs Lakers"));
        this.tickets.add(new Ticket(2, 20, LocalDate.now(), "Bulls vs Wizards"));
        this.tickets.add(new Ticket(3, 25, LocalDate.now(), "Minnesota vs Celtics"));
        this.tickets.add(new Ticket(4, 15, LocalDate.now(), "Raptors vs Suns"));
    }

    @Override
    public List<Ticket> selectAllTickets() {
        return this.tickets;
    }

    @Override
    public Ticket selectTicket(long ticketId) {
        for (Ticket ticket : this.tickets) {
            if (ticket.getId() == ticketId)
                return ticket;
        }

        return null;
    }

    @Override
    public boolean insertTicket(Ticket ticket) {
        if (this.selectTicket(ticket.getId()) != null){
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
