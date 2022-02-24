package fontys.sem3.individual_track.business.impl;

import fontys.sem3.individual_track.business.TicketsService;
import fontys.sem3.individual_track.model.Ticket;
import fontys.sem3.individual_track.repository.TicketsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NBAGamesTicketsService implements TicketsService {

    private final TicketsRepository ticketsRepository;

    @Autowired
    public NBAGamesTicketsService(@Qualifier("nbaTickets") TicketsRepository ticketsRepository) {
        this.ticketsRepository = ticketsRepository;
    }

    @Override
    public List<Ticket> getAllTickets() {
        return this.ticketsRepository.selectAllTickets();
    }

    @Override
    public Ticket getTicket(long ticketId) {
        return this.ticketsRepository.selectTicket(ticketId);
    }

    @Override
    public boolean addTicket(Ticket ticket) {
        return this.ticketsRepository.insertTicket(ticket);
    }

    @Override
    public boolean removeTicket(long tickedId) {
        return this.ticketsRepository.deleteTicket(tickedId);
    }
}
