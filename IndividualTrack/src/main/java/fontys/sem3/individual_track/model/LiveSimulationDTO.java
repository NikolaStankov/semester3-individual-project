package fontys.sem3.individual_track.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
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
