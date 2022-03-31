package fontys.sem3.individual_track.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Player {
    private long id;
    private String firstName;
    private String lastName;
    private String position;
    private int heightFeet;
    private int heightInches;
    private int weightPounds;

    public Player (@JsonProperty("id") long id,
                   @JsonProperty("first_name") String firstName,
                   @JsonProperty("last_name") String lastName,
                   @JsonProperty("position") String position,
                   @JsonProperty("height_feet") int heightFeet,
                   @JsonProperty("height_inches") int heightInches,
                   @JsonProperty("weight_pounds") int weightPounds){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.heightFeet = heightFeet;
        this.heightInches = heightInches;
        this.weightPounds = weightPounds;
    }
}
