package project.aiport.aiportproject1.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import project.aiport.aiportproject1.Entity.airplains;
import project.aiport.aiportproject1.Entity.flights;
import project.aiport.aiportproject1.Entity.users;

import java.util.List;

public interface FlightsRepository extends JpaRepository<flights, Integer> {
flights findById(int a);

}
