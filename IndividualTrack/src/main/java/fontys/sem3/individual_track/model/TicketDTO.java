package fontys.sem3.individual_track.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TicketDTO {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("ticket_type")
    private String ticketType;

    @JsonProperty("price")
    private double price;

    @JsonProperty("specification")
    private String specification;
}
