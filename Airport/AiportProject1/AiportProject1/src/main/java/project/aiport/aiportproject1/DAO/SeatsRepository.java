package project.aiport.aiportproject1.DAO;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project.aiport.aiportproject1.Entity.seats;

import java.util.List;

public interface SeatsRepository extends JpaRepository<seats, Integer> {
    @Query("SELECT s FROM seats s WHERE s.idFlight = :idFlight AND s.seatNumber = :seatNumber")
    List<seats> findBySeatNumberAndIdFlight(@Param("idFlight") int idFlight, @Param("seatNumber") String seatNumber);

    @Query("SELECT s FROM seats s WHERE s.idFlight = :idFlight AND s.isReserved = 0")
    List<seats> findAvailableSeatsByIdFlight(int idFlight);

    seats save(seats seat);
}
