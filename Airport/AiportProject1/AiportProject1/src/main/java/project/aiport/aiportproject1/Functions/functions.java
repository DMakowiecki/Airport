package project.aiport.aiportproject1.Functions;

import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.UUID;
@Service
public class functions {
    public static String generateReservationNumber() {

        String uuid = UUID.randomUUID().toString();

        return "R" + uuid.substring(0, 8);
    }

    public static Timestamp getCurrentTimestamp() {
        Date currentDate = new Date();
        return new Timestamp(currentDate.getTime());
    }
}
