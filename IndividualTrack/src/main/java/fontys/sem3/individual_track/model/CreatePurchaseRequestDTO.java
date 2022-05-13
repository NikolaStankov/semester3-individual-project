package fontys.sem3.individual_track.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
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
