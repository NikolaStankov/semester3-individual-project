package fontys.sem3.individual_track.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TicketDTO {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("price")
    private double price;

    @JsonProperty("purchased_date")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate purchasedDate;

    @JsonProperty("game")
    private GameDTO game;
}
