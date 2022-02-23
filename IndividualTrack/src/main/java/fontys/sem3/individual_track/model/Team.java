package fontys.sem3.individual_track.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Team {
    private long teamId;
    private String name;
    private String conference;

    public Team(long teamId, String name, String conference) {
        this.teamId = teamId;
        this.name = name;
        this.conference = conference;
    }
}
