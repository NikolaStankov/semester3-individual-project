package fontys.sem3.individual_track.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateTeamRequestDTO {
    @NotBlank
    @Length(min = 2, max = 4)
    private String abbreviation;

    @NotBlank
    @Length(min = 2, max = 50)
    private String city;

    @NotBlank
    @Length(min = 4, max = 4)
    private String conference;

    @NotBlank
    @Length(min = 2, max = 50)
    private String division;

    @NotBlank
    @Length(min = 2, max = 50)
    private String fullName;

    @NotBlank
    @Length(min = 2, max = 50)
    private String name;
}
