package fontys.sem3.individual_track.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
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
