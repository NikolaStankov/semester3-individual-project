package fontys.sem3.individual_track.controller;

import fontys.sem3.individual_track.business.TicketsService;
import fontys.sem3.individual_track.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/tickets")
public class TicketsController {
    private final TicketsService ticketsService;

    @Autowired
    public TicketsController(TicketsService ticketsService) {
        this.ticketsService = ticketsService;
    }

    @GetMapping
    public ResponseEntity<List<Ticket>> getAllTickets() {
        List<Ticket> ticketList = this.ticketsService.getAllTickets();

        if (ticketList != null) {
            return ResponseEntity.ok().body(ticketList);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("{ticketId}")
    public ResponseEntity<Ticket> getTicketById(@PathVariable(value = "ticketId") long ticketId) {
        Ticket ticket = this.ticketsService.getTicket(ticketId);

        if (ticket != null) {
            return ResponseEntity.ok().body(ticket);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Ticket> addTicket(@RequestBody Ticket ticket) {
        if (!this.ticketsService.addTicket(ticket)) {
            String entity = "A ticket with this id(" + ticket.getTicketId() + ") already exists";
            return new ResponseEntity(entity, HttpStatus.CONFLICT);
        } else {
            String url = "tickets" + "/" + ticket.getTicketId();
            URI uri = URI.create(url);
            return new ResponseEntity(uri, HttpStatus.CREATED);
        }
    }
}
