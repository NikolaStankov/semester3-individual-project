package fontys.sem3.individual_track.repository.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "game")
@Builder
@Data
@Generated
@AllArgsConstructor
@NoArgsConstructor
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Column(name = "date")
    private String date;

    @NotNull
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
