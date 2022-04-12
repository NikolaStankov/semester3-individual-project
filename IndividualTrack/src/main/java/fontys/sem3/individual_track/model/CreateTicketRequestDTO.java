package fontys.sem3.individual_track.model;

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
    private LocalDate purchasedDate;

    @NotNull
    private Long gameId;
}
