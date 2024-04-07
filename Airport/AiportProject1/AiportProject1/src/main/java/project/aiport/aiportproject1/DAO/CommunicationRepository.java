package project.aiport.aiportproject1.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import project.aiport.aiportproject1.Entity.Support.Communication;
import project.aiport.aiportproject1.Entity.users;

import java.util.List;

public interface CommunicationRepository extends JpaRepository<Communication, Integer> {
 List<Communication> findAllByUserId(users a);
 Communication findCommunicationByCommunicationId(int id);
 List<Communication> findByStatusAndModeratorIdIsNull(String status);
 List<Communication> findByStatusIs(String status);

}