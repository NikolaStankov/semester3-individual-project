package fontys.sem3.individual_track.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateTicketRequestDTO {

    @NotNull
    private double price;

    @NotNull
    @JsonProperty("purchased_date")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate purchasedDate;

    @NotNull
    private Long gameId;
}
