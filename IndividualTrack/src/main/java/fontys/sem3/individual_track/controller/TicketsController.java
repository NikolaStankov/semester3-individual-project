package fontys.sem3.individual_track.controller;

import fontys.sem3.individual_track.business.TicketsService;
import fontys.sem3.individual_track.configuration.security.isauthenticated.IsAuthenticated;
import fontys.sem3.individual_track.model.CreateTicketRequestDTO;
import fontys.sem3.individual_track.model.CreateTicketResponseDTO;
import fontys.sem3.individual_track.model.TicketDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/tickets")
@RequiredArgsConstructor
public class TicketsController {

    private final TicketsService ticketsService;

    @GetMapping
    public ResponseEntity<List<TicketDTO>> getAllTickets() {
        List<TicketDTO> ticketList = this.ticketsService.getAllTickets();

        if (ticketList != null) {
            return ResponseEntity.ok().body(ticketList);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("{ticketId}")
    public ResponseEntity<TicketDTO> getTicketById(@PathVariable(value = "ticketId") long ticketId) {
        Optional<TicketDTO> optionalTicketDTO = this.ticketsService.getTicket(ticketId);

        if (optionalTicketDTO.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(optionalTicketDTO.get());
        }
    }

    @PostMapping
    @IsAuthenticated
    public ResponseEntity<CreateTicketResponseDTO> addTicket(@RequestBody @Valid CreateTicketRequestDTO ticketRequest) {
        CreateTicketResponseDTO ticketResponse = this.ticketsService.createTicket(ticketRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(ticketResponse);
    }
}
