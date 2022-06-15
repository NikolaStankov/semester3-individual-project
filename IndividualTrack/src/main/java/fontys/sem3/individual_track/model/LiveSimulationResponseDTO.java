package fontys.sem3.individual_track.model;

import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@Generated
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LiveSimulationResponseDTO {
    @NotNull
    private boolean team1;

    @NotNull
    private boolean team2;

    @NotNull
    private String player;

    @NotNull
    private int points;
}
