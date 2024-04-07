package project.aiport.aiportproject1.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import project.aiport.aiportproject1.Entity.users;

public interface UsersRepository extends JpaRepository<users, Integer> {
 users findById(int a);


 users save(users user);

 users findByToken(String token);
 users findByUsername(String username);


}
