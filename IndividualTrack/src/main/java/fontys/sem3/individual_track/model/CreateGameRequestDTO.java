package fontys.sem3.individual_track.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateGameRequestDTO {

    @NotBlank
    private String date;

    @Min(value = 0L, message = "The value must be positive")
    private int season;

    @NotNull
    private Long homeTeamId;

    @NotNull
    private Long visitorTeamId;
}
