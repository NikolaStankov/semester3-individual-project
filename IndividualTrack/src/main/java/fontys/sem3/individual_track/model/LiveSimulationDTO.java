package fontys.sem3.individual_track.model;

import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@Generated
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LiveSimulationDTO {

    @NotNull
    private String team1;
    @NotNull
    private String team2;
    @NotNull
    private int score;
}
