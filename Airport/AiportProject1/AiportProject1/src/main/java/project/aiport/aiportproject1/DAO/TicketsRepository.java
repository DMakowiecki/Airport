package project.aiport.aiportproject1.DAO;

import jakarta.persistence.Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import project.aiport.aiportproject1.Entity.seats;
import project.aiport.aiportproject1.Entity.tickets;

import java.util.List;
import java.util.Optional;

public interface TicketsRepository extends JpaRepository<tickets, Integer> {
    tickets save(tickets ticket);



}
