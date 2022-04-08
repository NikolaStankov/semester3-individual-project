package fontys.sem3.individual_track.repository.entity;

import fontys.sem3.individual_track.model.TeamDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "game")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @NotBlank
    @Column(name = "date")
    private String date;

    @NotBlank
    @Length(min = 4)
    @Column(name = "season")
    private int season;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "home_team_id")
    private Team homeTeam;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "visitor_team_id")
    private Team visitorTeam;
}
