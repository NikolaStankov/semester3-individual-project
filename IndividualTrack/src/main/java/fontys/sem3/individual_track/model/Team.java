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
    private String division;
    private String fullName;
    private String name;

    public Team(@JsonProperty("id") long id,
                @JsonProperty("abbreviation") String abbreviation,
                @JsonProperty("city") String city,
                @JsonProperty("conference") String conference,
                @JsonProperty("division") String division,
                @JsonProperty("full_name") String fullName,
                @JsonProperty("name") String name
                ) {
        this.id = id;
        this.abbreviation = abbreviation;
        this.city = city;
        this.conference = conference;
        this.division = division;
        this.fullName = fullName;
        this.name = name;
    }
}
