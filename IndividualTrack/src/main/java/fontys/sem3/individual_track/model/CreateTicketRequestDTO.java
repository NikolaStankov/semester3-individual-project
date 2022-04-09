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
    @Min(1)
    @Max(100000)
    private Long id;

    @NotNull
    @DecimalMin(value = "1")
    private double price;

    @NotNull
    private LocalDate purchasedDate;

    @NotNull
    private Long gameId;

}
