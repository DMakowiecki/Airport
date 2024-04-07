package project.aiport.aiportproject1.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import project.aiport.aiportproject1.Entity.flights;
import project.aiport.aiportproject1.Entity.reservations;
import project.aiport.aiportproject1.Entity.users;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public interface  ReservationsRepository extends JpaRepository<reservations, Integer> {
    reservations save(reservations reservation);
    @Query("SELECT r FROM reservations r WHERE r.idpassenger.idpassenger = :xd")
    List<reservations> findByIdpassenger(@Param("xd") int xd);
    reservations findByIdreservations(int id);

}


