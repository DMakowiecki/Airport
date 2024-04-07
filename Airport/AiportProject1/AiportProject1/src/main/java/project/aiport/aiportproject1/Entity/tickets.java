package project.aiport.aiportproject1.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class tickets {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ticket")
    private int idTicket;

    @ManyToOne
    @JoinColumn(name = "id_passenger",referencedColumnName = "id_passenger")
    private passengers passenger;

    @ManyToOne
    @JoinColumn(name = "id_flight")
    private flights flight;

    @Column(name = "issuance_date")
    private String issuanceDate;

    @Column(name = "price")
    private double price;

    @ManyToOne
    @JoinColumn(name = "id_reservation")
    private reservations reservation;

}
