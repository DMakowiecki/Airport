package project.aiport.aiportproject1.Entity.Support;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import project.aiport.aiportproject1.Entity.users;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Communication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "communication_id")
    private int communicationId;

    @ManyToOne
    @JoinColumn(name ="user_id")
    private users userId;
    @OneToOne
    @JoinColumn(name = "moderator_id")
    private users moderatorId;

    @Column(name = "subject", length = 255)
    private String subject;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @Column(name = "status", nullable = false, length = 50)
    private String status = "Nowy";
}
