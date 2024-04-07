package project.aiport.aiportproject1.Entity;

import jakarta.persistence.*;

import jakarta.persistence.*;
import lombok.*;

@Entity
@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class passengers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_passenger")
    private int idpassenger;
    @OneToOne
    @JoinColumn(name="id_user",referencedColumnName = "id")
    private users iduser;

    @Column(name = "email")
    private String email;
    @Column(name = "phone_number")
    private String phone_number;


}
