package project.aiport.aiportproject1.Entity;

import jakarta.persistence.*;

import java.util.Date;
import jakarta.persistence.*;
import lombok.*;

@Entity
@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class flights {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_flight")
    private int  idflight;
     @OneToOne
     @JoinColumn(name = "id_airplain",referencedColumnName = "id_airplain")
     private  airplains idairplain;
    @Column(name = "date_flight")
    private String dateflight;
    @Column(name = "departure_time")
    private String departuretime;
    @Column(name = "arrival_time")
    private String  arrivaltime;
    @Column(name = "departure_place")
    private String  departureplace;
    @Column(name = "arrival_place")
    private String arrivalplace;
    @Column(name = "ticket_price")
    private double  ticketprice;
    @Column(name = "remaining_seats")
    private int  remainingseats;
    @Column(name = "total_seats")
    private boolean totalseats;

}
