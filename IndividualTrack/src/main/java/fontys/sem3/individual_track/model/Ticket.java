package fontys.sem3.individual_track.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Ticket {
    private long ticketId;
    private double price;
    private String game;

    public Ticket(long ticketId, double price, String game) {
        this.ticketId = ticketId;
        this.price = price;
        this.game = game;
    }
}
