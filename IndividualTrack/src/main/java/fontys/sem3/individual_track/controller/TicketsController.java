package fontys.sem3.individual_track.controller;

import fontys.sem3.individual_track.business.TicketsService;
import fontys.sem3.individual_track.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
