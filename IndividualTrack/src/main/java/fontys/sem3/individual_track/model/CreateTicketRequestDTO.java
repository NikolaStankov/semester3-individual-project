package fontys.sem3.individual_track.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.*;

@Data
@Generated
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateTicketRequestDTO {

    @NotNull
    @JsonProperty("ticket_type")
    private String ticketType;

    @NotNull
    private double price;

    @NotNull
    private String specification;
}
