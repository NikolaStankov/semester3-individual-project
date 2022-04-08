package fontys.sem3.individual_track.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerDTO {
    private long id;
    private String firstName;
    private String lastName;
    private String position;
    private TeamDTO team;

    public PlayerDTO(@JsonProperty("id") long id,
                     @JsonProperty("first_name") String firstName,
                     @JsonProperty("last_name") String lastName,
                     @JsonProperty("position") String position,
                     @JsonProperty("team") TeamDTO team) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.team = team;
    }
}
