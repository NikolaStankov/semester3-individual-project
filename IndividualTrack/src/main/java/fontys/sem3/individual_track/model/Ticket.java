package fontys.sem3.individual_track.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter

public class Ticket {
    private long id;
    private double price;
    private LocalDate purchasedDate;
    private String game;

    public Ticket(@JsonProperty("id") long id,
                  @JsonProperty("price") double price,
                  @JsonProperty("purchased_date") LocalDate purchasedDate,
                  @JsonProperty("game") String game) {
        this.id = id;
        this.price = price;
        this.purchasedDate = purchasedDate;
        this.game = game;
    }

}
