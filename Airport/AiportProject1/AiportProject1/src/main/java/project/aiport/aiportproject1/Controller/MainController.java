package project.aiport.aiportproject1.Controller;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import project.aiport.aiportproject1.DAO.*;
import project.aiport.aiportproject1.Entity.*;

import org.springframework.stereotype.Controller;

import project.aiport.aiportproject1.Entity.Support.Communication;
import project.aiport.aiportproject1.Entity.Support.CommunicationReply;
import project.aiport.aiportproject1.Functions.functions;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static project.aiport.aiportproject1.Functions.functions.getCurrentTimestamp;

@Controller
public class MainController {
    private final AirplainsService AirplainsService;
    private final FlightsRepository FlightsRepository;
    private final ReservationsRepository ReservationsRepository;
    private  final SeatsRepository SeatsRepository;
    private final PassengersRepository PassengersRepository;
    private final TicketsRepository TicketsRepository;
    private final CommunicationReplyRepository communicationReplyRepository;
    private final CommunicationRepository communicationRepository;
    private UsersRepository usersRepository;
    public MainController(AirplainsService airplainsService, project.aiport.aiportproject1.DAO.FlightsRepository flightsRepository, project.aiport.aiportproject1.DAO.ReservationsRepository reservationsRepository, project.aiport.aiportproject1.DAO.TicketsRepository ticketsRepository, project.aiport.aiportproject1.DAO.SeatsRepository seatsRepository, project.aiport.aiportproject1.DAO.PassengersRepository passengersRepository, CommunicationReplyRepository communicationReplyRepository, CommunicationRepository communicationRepository, UsersRepository usersRepository) {
        AirplainsService = airplainsService;
        FlightsRepository = flightsRepository;
        ReservationsRepository = reservationsRepository;
        SeatsRepository = seatsRepository;
        PassengersRepository = passengersRepository;
        this.communicationReplyRepository = communicationReplyRepository;
        this.communicationRepository = communicationRepository;
        this.usersRepository = usersRepository;
        TicketsRepository= ticketsRepository;
    }
    @GetMapping("/home")
    public void loty(Model model){
        List<flights> flightslist = FlightsRepository.findAll();

        model.addAttribute("flightslist",flightslist);
    }
    @GetMapping("/loty")
    public String loty(){
        List<flights> flightslist = FlightsRepository.findAll();

       return "flights";
    }

    @PostMapping("/reservationflight/{idflight}")
    public String reservation(@PathVariable int idflight, HttpServletRequest request) {
        flights flight = FlightsRepository.findById(idflight);
        if (flight != null) {
            HttpSession session = request.getSession();
            String username = (String) session.getAttribute("username");
            users user = usersRepository.findByUsername(username);
            passengers passenger = PassengersRepository.findByIduser(user);
            List<seats> availableSeats = SeatsRepository.findAvailableSeatsByIdFlight(idflight);
            if (!availableSeats.isEmpty()) {
                seats selectedSeat = availableSeats.get(0);
                if (selectedSeat.getIsReserved() == 0) {
                    selectedSeat.setIsReserved(1);
                    SeatsRepository.save(selectedSeat);


                    int remainingSeats = flight.getRemainingseats();
                    remainingSeats--;
                    flight.setRemainingseats(remainingSeats);
                    FlightsRepository.save(flight);

                    reservations reservation = new reservations();
                    reservation.setIdflight(flight);
                    reservation.setReservationnumber(functions.generateReservationNumber());
                    reservation.setReservationstatus("Oczekująca");
                    Timestamp timestamp = getCurrentTimestamp();
                    reservation.setIdpassenger(passenger);
                    reservation.setReservationdate(String.valueOf(timestamp));
                    reservation.setTicketprice(flight.getTicketprice());
                    String seatNumberString = selectedSeat.getSeatNumber();
                    int seatNumber = Integer.parseInt(seatNumberString);
                    reservation.setSeatnumber(seatNumber);
                    ReservationsRepository.save(reservation);

                    return "redirect:/home";
                } else {
                    return "redirect:/errorPage";
                }
            } else {
                return "redirect:/errorPage";
            }
        } else {
            return "redirect:/errorPage";
        }
    }


    @PostMapping("/cancelReservation/{idreservations}")
    public String cancelReservation(@PathVariable int idreservations, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        reservations reservation = ReservationsRepository.findByIdreservations(idreservations);
        if (reservation != null) {
            users userId = usersRepository.findByUsername(username);
            if (userId != null) {
                passengers passenger = PassengersRepository.findByIduser(userId);
                if (passenger != null && reservation.getIdpassenger().getIdpassenger() == passenger.getIdpassenger()) {
                    reservation.setReservationstatus("Anulowana");
                    ReservationsRepository.save(reservation);

                    int seatNumber = reservation.getSeatnumber();
                    List<seats> reservedSeats = SeatsRepository.findBySeatNumberAndIdFlight(reservation.getIdflight().getIdflight(), String.valueOf(seatNumber));

                    if (!reservedSeats.isEmpty()) {
                        seats reservedSeat = reservedSeats.get(0);
                        reservedSeat.setIsReserved(0);
                        SeatsRepository.save(reservedSeat);
                    }


                    reservation.setSeatnumber(0);
                    ReservationsRepository.save(reservation);

                    return "redirect:/home";
                }
            }
        }

        return "/error";
    }


    @GetMapping("/reservations")
    public String myReservations(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        users userId = usersRepository.findByUsername(username);
        passengers passenger = PassengersRepository.findByIduser(userId);
        List<reservations> userReservations = ReservationsRepository.findByIdpassenger(passenger.getIdpassenger());

        if (userReservations == null) {
            userReservations = new ArrayList<>();
        }

        model.addAttribute("userReservations", userReservations);
        return "myreservations";
    }
    @PostMapping("/buyticket/{idreservations}")
    public String buyticket(Model model, @PathVariable int idreservations, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        reservations reservation = ReservationsRepository.findByIdreservations(idreservations);
        if (reservation != null) {
            users loggedInUser = usersRepository.findByUsername(username);

            if (loggedInUser != null) {
                passengers passenger = PassengersRepository.findByIduser(loggedInUser);

                if (passenger != null && reservation.getIdpassenger().getIdpassenger() == passenger.getIdpassenger()) {
                    tickets ticket = new tickets();
                    ticket.setFlight(reservation.getIdflight());
                    ticket.setPassenger(reservation.getIdpassenger());
                    ticket.setReservation(reservation);
                    Timestamp timestamp = getCurrentTimestamp();
                    ticket.setIssuanceDate(String.valueOf(timestamp));
                    ticket.setPrice(reservation.getTicketprice());
                    reservation.setReservationstatus("Zakończona");

                    ReservationsRepository.save(reservation);
                    TicketsRepository.save(ticket);

                    return "home";
                }
            }
        }

        return "error";
    }
    @GetMapping("/support")
    public String showSupportPage(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        users userId = usersRepository.findByUsername(username);
        List<Communication> communicationList = communicationRepository.findAllByUserId(userId);


        List<List<CommunicationReply>> repliesList = new ArrayList<>();
        for (Communication communication : communicationList) {
            List<CommunicationReply> replies = communicationReplyRepository.findByCommunicationId(communication);
            repliesList.add(replies);
            System.out.println(replies);
        }

        model.addAttribute("communicationList", communicationList);
        model.addAttribute("repliesList", repliesList);
        Communication newCommunication = new Communication();
        model.addAttribute("communication", newCommunication);

        return "support";
    }




    @PostMapping("/create")
    public String createCommunication(@ModelAttribute("communication") Communication communication, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        users iduser = usersRepository.findByUsername(username);
        communication.setUserId(iduser);
        communication.setStatus("Nowy");
        communication.setContent(communication.getContent());
        communication.setSubject(communication.getSubject());
        communicationRepository.save(communication);
        return "redirect:/support";
    }


}
