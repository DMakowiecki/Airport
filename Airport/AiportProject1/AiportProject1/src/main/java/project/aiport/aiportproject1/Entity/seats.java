package project.aiport.aiportproject1.Entity;
import jakarta.persistence.*;
import lombok.*;
import project.aiport.aiportproject1.Entity.flights;

@Entity
@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "seats")
public class seats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_seat")
    private int idSeat;

    @Column(name = "id_flight", nullable = false)
    private int idFlight;

    @Column(name = "seat_number", nullable = false, length = 10)
    private String seatNumber;

    @Column(name = "is_reserved", nullable = false, columnDefinition = "int default 0")
    private int isReserved;

    @ManyToOne
    @JoinColumn(name = "id_flight", referencedColumnName = "id_flight", insertable = false, updatable = false)
    private flights flight;


}
