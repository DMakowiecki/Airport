package project.aiport.aiportproject1.Entity.Support;


import jakarta.persistence.*;
import lombok.*;
import project.aiport.aiportproject1.Entity.users;


@Entity
@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class CommunicationReply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reply_id")
    private int replyId;

    @ManyToOne
    @JoinColumn(name = "communication_id")
    private Communication communicationId;

    @ManyToOne
    @JoinColumn(name="user_id")
    private users userId;

    @Column(name = "moderator_id")
    private int moderatorId;

    @Column(name = "content")
    private String content;

    @Column(name = "status")
    private String status;
}
