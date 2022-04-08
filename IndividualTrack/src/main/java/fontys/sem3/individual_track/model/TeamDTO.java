package fontys.sem3.individual_track.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TeamDTO {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("abbreviation")
    private String abbreviation;

    @JsonProperty("city")
    private String city;

    @JsonProperty("conference")
    private String conference;

    @JsonProperty("division")
    private String division;

    @JsonProperty("full_name")
    private String fullName;

    @JsonProperty("name")
    private String name;
}
