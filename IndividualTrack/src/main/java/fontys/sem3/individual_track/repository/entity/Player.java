package fontys.sem3.individual_track.repository.entity;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "player")
@Builder
@Data
@Generated
@AllArgsConstructor
@NoArgsConstructor
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Length(min = 2, max = 50)
    @Column(name = "first_name")
    private String firstName;

    @NotBlank
    @Length(min = 2, max = 50)
    @Column(name = "last_name")
    private String lastName;

    @NotBlank
    @Length(min = 1, max = 2)
    @Column(name = "position")
    private String position;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;
}
