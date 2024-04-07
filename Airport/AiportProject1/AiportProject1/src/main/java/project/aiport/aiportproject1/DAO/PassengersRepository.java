package project.aiport.aiportproject1.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.aiport.aiportproject1.Entity.passengers;
import project.aiport.aiportproject1.Entity.users;

import java.util.List;

public interface PassengersRepository extends JpaRepository<passengers, Integer> {
    passengers findOneByIdpassenger(int idpassenger);
    passengers findByIduser(users iduser);
    List<passengers> findAllByIdpassenger(int idpassenger);
}