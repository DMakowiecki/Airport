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
public class authorities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idauthorities")
    private int id;
    @Column(name = "authority")
    private String authority;
    @Column(name = "username")
    private String username;

}