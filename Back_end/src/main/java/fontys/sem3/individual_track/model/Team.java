package fontys.sem3.individual_track.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Team {
    private long teamId;
    private String name;
    private String conference;

    public Team(@JsonProperty("teamId") long teamId,
                @JsonProperty("name") String name,
                @JsonProperty("conference") String conference) {
        this.teamId = teamId;
        this.name = name;
        this.conference = conference;
    }
}
