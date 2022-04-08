package fontys.sem3.individual_track.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "ticket")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @DecimalMin(value = "1")
    @Column(name = "price")
    private double price;

    @NotNull
    @Column(name = "purchased_date")
    private LocalDate purchasedDate;

    @NotBlank
    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;
}
