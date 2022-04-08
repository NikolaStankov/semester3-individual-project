package fontys.sem3.individual_track.business.impl;

import fontys.sem3.individual_track.business.TicketsService;
import fontys.sem3.individual_track.model.TicketDTO;
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
    public List<TicketDTO> getAllTickets() {
        return this.ticketsRepository.selectAllTickets();
    }

    @Override
    public TicketDTO getTicket(long ticketId) {
        return this.ticketsRepository.selectTicket(ticketId);
    }

    @Override
    public boolean addTicket(TicketDTO ticket) {
        return this.ticketsRepository.insertTicket(ticket);
    }

    @Override
    public boolean removeTicket(long tickedId) {
        return this.ticketsRepository.deleteTicket(tickedId);
    }
}
