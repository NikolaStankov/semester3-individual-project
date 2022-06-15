package fontys.sem3.individual_track.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Generated
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseDTO {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("ticket")
    private TicketDTO ticket;

    @JsonProperty("game")
    private GameDTO game;

    @JsonProperty("quantity")
    private int quantity;

    @JsonProperty("user")
    private UserDTO user;
}
