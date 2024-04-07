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
public class airplains {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_airplain")
    private int idairplain;
    @Column(name = "name_model")
    private String namemodel;
    @Column(name = "registration_number")
    private String registrationnumber;
    @Column(name = "passenger_capacity")
    private int passengercapacity;


}
