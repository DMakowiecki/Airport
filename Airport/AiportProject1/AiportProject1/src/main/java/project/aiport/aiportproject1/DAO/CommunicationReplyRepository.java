package project.aiport.aiportproject1.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import project.aiport.aiportproject1.Entity.Support.Communication;
import project.aiport.aiportproject1.Entity.Support.CommunicationReply;

import java.util.List;

public interface CommunicationReplyRepository extends JpaRepository<CommunicationReply, Integer> {
    List<CommunicationReply> findByCommunicationId(Communication communication);


}
