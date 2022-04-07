package fontys.sem3.individual_track.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Player {
    private long id;
    private String firstName;
    private String lastName;
    private String position;
    private Team team;

    public Player(@JsonProperty("id") long id,
                  @JsonProperty("first_name") String firstName,
                  @JsonProperty("last_name") String lastName,
                  @JsonProperty("position") String position,
                  @JsonProperty("team") Team team) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.team = team;
    }
}
