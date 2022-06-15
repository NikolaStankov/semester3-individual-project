package fontys.sem3.individual_track.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Generated
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GameDTO {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("date")
    private String date;

    @JsonProperty("season")
    private int season;

    @JsonProperty("home_team")
    private TeamDTO homeTeam;

    @JsonProperty("visitor_team")
    private TeamDTO visitorTeam;
}
