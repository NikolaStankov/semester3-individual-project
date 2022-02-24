package fontys.sem3.individual_track.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Ticket {
    private long ticketId;
    private double price;
    private String game;

    public Ticket(@JsonProperty("ticketId") long ticketId,
                  @JsonProperty("price") double price,
                  @JsonProperty("game") String game) {
        this.ticketId = ticketId;
        this.price = price;
        this.game = game;
    }

}
