package fontys.sem3.individual_track.repository.entity;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "team")
@Builder
@Data
@Generated
@AllArgsConstructor
@NoArgsConstructor
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Length(min = 2, max = 4)
    @Column(name = "abbreviation")
    private String abbreviation;

    @NotBlank
    @Length(min = 2, max = 50)
    @Column(name = "city")
    private String city;

    @NotBlank
    @Length(min = 4, max = 4)
    @Column(name = "conference")
    private String conference;

    @NotBlank
    @Length(min = 2, max = 50)
    @Column(name = "division")
    private String division;

    @NotBlank
    @Length(min = 2, max = 50)
    @Column(name = "full_name")
    private String fullName;

    @NotBlank
    @Length(min = 2, max = 50)
    @Column(name = "name")
    private String name;
}
