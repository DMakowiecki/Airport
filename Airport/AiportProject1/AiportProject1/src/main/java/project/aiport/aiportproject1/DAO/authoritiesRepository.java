package project.aiport.aiportproject1.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import project.aiport.aiportproject1.Entity.airplains;
import project.aiport.aiportproject1.Entity.authorities;

public interface authoritiesRepository extends JpaRepository<airplains, Integer> {
    authorities save(authorities authoritie);
}
