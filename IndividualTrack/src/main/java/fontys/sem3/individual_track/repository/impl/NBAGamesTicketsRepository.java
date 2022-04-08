package fontys.sem3.individual_track.repository.impl;

import fontys.sem3.individual_track.model.TicketDTO;
import fontys.sem3.individual_track.repository.TicketsRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Repository("nbaTickets")
@Primary
public class NBAGamesTicketsRepository implements TicketsRepository {

    private final List<TicketDTO> tickets;

    public NBAGamesTicketsRepository() {
        this.tickets = new ArrayList<>();
        this.tickets.add(new TicketDTO(1, 20, LocalDate.now(), "Clippers vs Lakers"));
        this.tickets.add(new TicketDTO(2, 20, LocalDate.now(), "Bulls vs Wizards"));
        this.tickets.add(new TicketDTO(3, 25, LocalDate.now(), "Minnesota vs Celtics"));
        this.tickets.add(new TicketDTO(4, 15, LocalDate.now(), "Raptors vs Suns"));
    }

    @Override
    public List<TicketDTO> selectAllTickets() {
        return this.tickets;
    }

    @Override
    public TicketDTO selectTicket(long ticketId) {
        for (TicketDTO ticket : this.tickets) {
            if (ticket.getId() == ticketId)
                return ticket;
        }

        return null;
    }

    @Override
    public boolean insertTicket(TicketDTO ticket) {
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

        TicketDTO ticket = this.selectTicket(tickedId);
        return this.tickets.remove(ticket);
    }
}
