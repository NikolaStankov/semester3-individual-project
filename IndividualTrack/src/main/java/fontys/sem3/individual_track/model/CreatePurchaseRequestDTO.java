package fontys.sem3.individual_track.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@Generated
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreatePurchaseRequestDTO {
    @NotNull
    @JsonProperty("ticket_id")
    private Long ticketId;

    @NotNull
    @JsonProperty("game_id")
    private Long gameId;

    @NotNull
    private int quantity;

    @NotNull
    @JsonProperty("user_id")
    private Long userId;
}
