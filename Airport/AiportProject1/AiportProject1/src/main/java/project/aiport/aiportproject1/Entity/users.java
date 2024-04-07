package project.aiport.aiportproject1.Entity;

import jakarta.persistence.*;

import jakarta.validation.constraints.NotBlank;
import lombok.*;


@Entity
@EqualsAndHashCode
@Getter
@Setter
//@NoArgsConstructor
@AllArgsConstructor
@Builder
public class users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "username")
    @NotBlank(message = "Name is mandatory")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "enabled")
    private int enabled;

    public users() {
        super();
        this.enabled=0;
    }
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @NotBlank(message = "Email is mandatory")
    @Column(name = "email")
    private String email;
    @Column(name = "phone_number")
    private String phone_number;
    @Column(name = "token")
    private String  token;



}

