package fontys.sem3.individual_track.repository.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "purchase")
@Builder
@Data
@Generated
@AllArgsConstructor
@NoArgsConstructor
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    @NotNull
    @Column(name = "quantity")
    private int quantity;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
