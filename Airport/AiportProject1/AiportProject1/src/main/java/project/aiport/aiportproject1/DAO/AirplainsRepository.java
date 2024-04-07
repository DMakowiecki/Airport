package project.aiport.aiportproject1.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import project.aiport.aiportproject1.Entity.airplains;

public interface AirplainsRepository extends JpaRepository<airplains , Integer> {

}
