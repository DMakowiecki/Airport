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
public class reservations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_reservations")
    private int idreservations;
    @OneToOne
    @JoinColumn(name ="id_passenger",referencedColumnName = "id_passenger")
    private passengers idpassenger;
    @ManyToOne
    @JoinColumn(name ="id_flight",referencedColumnName = "id_flight")
   private flights idflight;
    @Column(name="reservation_number")
     private String reservationnumber;
    @Column(name="reservation_date")
    private String reservationdate;
    @Column(name="reservation_status")
    private String reservationstatus;
    @Column(name="seat_number")
    private Integer seatnumber;
    @Column(name="ticket_price")
    private double ticketprice;




}
