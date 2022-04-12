package fontys.sem3.individual_track.business.impl;

import fontys.sem3.individual_track.business.TicketsService;
import fontys.sem3.individual_track.business.converter.TicketDTOConverter;
import fontys.sem3.individual_track.business.validator.GameIdValidator;
import fontys.sem3.individual_track.model.CreateTicketRequestDTO;
import fontys.sem3.individual_track.model.CreateTicketResponseDTO;
import fontys.sem3.individual_track.model.TicketDTO;
import fontys.sem3.individual_track.repository.TicketsRepository;
import fontys.sem3.individual_track.repository.entity.Game;
import fontys.sem3.individual_track.repository.entity.Ticket;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Primary
public class TicketsServiceImpl implements TicketsService {

    private final TicketsRepository ticketsRepository;
    private final GameIdValidator gameIdValidator;


    @Override
    public List<TicketDTO> getAllTickets() {
        List<Ticket> ticketList = this.ticketsRepository.findAll();
        List<TicketDTO> ticketDTOList = new ArrayList<>();

        for (Ticket ticket : ticketList) {
            ticketDTOList.add(TicketDTOConverter.convertToDTO(ticket));
        }

        return ticketDTOList;
    }

    @Override
    public Optional<TicketDTO> getTicket(long ticketId) {
        return this.ticketsRepository.findById(ticketId)
                .map(TicketDTOConverter::convertToDTO);
    }

    @Override
    public CreateTicketResponseDTO createTicket(CreateTicketRequestDTO ticketRequest) {
        gameIdValidator.validateGameId(ticketRequest.getGameId());

        Ticket ticketToSave = Ticket.builder()
                .price(ticketRequest.getPrice())
                .purchasedDate(ticketRequest.getPurchasedDate())
                .game(Game.builder().id(ticketRequest.getGameId()).build())
                .build();

        Ticket savedTicket = this.ticketsRepository.save(ticketToSave);

        return CreateTicketResponseDTO.builder()
                .ticketId(savedTicket.getId())
                .build();
    }

    @Override
    public void removeTicket(long tickedId) {
        this.ticketsRepository.deleteById(tickedId);
    }
}
