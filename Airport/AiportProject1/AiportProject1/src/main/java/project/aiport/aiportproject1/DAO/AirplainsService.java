package project.aiport.aiportproject1.DAO;

import org.springframework.stereotype.Service;
import project.aiport.aiportproject1.Entity.airplains;

import java.util.List;


@Service
public class AirplainsService {

    public AirplainsService(AirplainsRepository AirplainsRepository) {
        this.AirplainsRepository = AirplainsRepository;
    }

    private AirplainsRepository AirplainsRepository;
  
    public List<airplains> findAll() {
        return AirplainsRepository.findAll();
    }

}
