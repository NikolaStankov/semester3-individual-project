package fontys.sem3.individual_track.model;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Generated
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreatePlayerRequestDTO {

    @NotBlank
    @Length(min = 2, max = 50)
    private String firstName;

    @NotBlank
    @Length(min = 2, max = 50)
    private String lastName;

    @NotBlank
    @Length(min = 1, max = 2)
    private String position;

    @NotNull
    private Long teamId;
}
