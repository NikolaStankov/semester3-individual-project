package fontys.sem3.individual_track.repository.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "ticket")
@Builder
@Data
@Generated
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "ticket_type")
    @Enumerated(EnumType.STRING)
    private TicketTypeEnum ticketType;

    @NotNull
    @Column(name = "price")
    private double price;

    @NotNull
    @Column(name = "specification")
    private String specification;
}
