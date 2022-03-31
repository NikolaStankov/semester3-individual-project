package fontys.sem3.individual_track.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Team {
    private long id;
    private String abbreviation;
    private String city;
    private String conference;
    private String fullName;

    public Team(@JsonProperty("id") long id,
                @JsonProperty("abbreviation") String abbreviation,
                @JsonProperty("city") String city,
                @JsonProperty("conference") String conference,
                @JsonProperty("full_name") String fullName
                ) {
        this.id = id;
        this.abbreviation = abbreviation;
        this.city = city;
        this.conference = conference;
        this.fullName = fullName;
    }

}
