package project.aiport.aiportproject1.Controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.aiport.aiportproject1.DAO.CommunicationReplyRepository;
import project.aiport.aiportproject1.DAO.CommunicationRepository;
import project.aiport.aiportproject1.DAO.UsersRepository;
import project.aiport.aiportproject1.Entity.Support.Communication;
import project.aiport.aiportproject1.Entity.Support.CommunicationReply;
import project.aiport.aiportproject1.Entity.users;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api/communication")
public class CommunicationController {
    private final CommunicationRepository communicationRepository;
    private final CommunicationReplyRepository communicationReplyRepository;
    private final UsersRepository usersRepository;

    private CommunicationReply communicationReply;

    public CommunicationController(CommunicationRepository communicationRepository, CommunicationReplyRepository communicationReplyRepository, UsersRepository usersRepository) {
        this.communicationRepository = communicationRepository;
        this.communicationReplyRepository = communicationReplyRepository;
        this.usersRepository = usersRepository;
    }


    @GetMapping("/reports")
    public String reports(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        int moderatorid = usersRepository.findByUsername(username).getId();
        model.addAttribute("moderatorid", moderatorid);
        List<Communication> newReports = communicationRepository.findByStatusAndModeratorIdIsNull("Nowy");
        model.addAttribute("newReports", newReports);
        List<Communication> duringReports = communicationRepository.findByStatusIs("W trakcie");
        model.addAttribute("duringReports", duringReports);
        List<Communication> endedReports = communicationRepository.findByStatusIs("Zakończone");
        model.addAttribute("endedReports", endedReports);
        return "/supportanswer";
    }


    @PostMapping("/assign/{moderatorid}")
    public String assignCommunication(@PathVariable("moderatorid") int moderatorId, @RequestParam("communicationId") int communicationId) {

        Communication comm = communicationRepository.findCommunicationByCommunicationId(communicationId);
        users id=usersRepository.findById(moderatorId);
        comm.setModeratorId(id);
        comm.setStatus("W trakcie");
        communicationRepository.save(comm);
        return "redirect:/api/communication/reports";
    }

    @PostMapping("/close/")
    public String endCommunication(@RequestParam("communicationId") int communicationId){
        Communication comm=communicationRepository.findCommunicationByCommunicationId(communicationId);

        comm.setStatus("W trakcie");
        comm.setStatus("Zakończone");
        communicationRepository.save(comm);
        return "redirect:/api/communication/reports";
    }
    @PostMapping("/respond")
    public String answerCommunication(@RequestParam("communicationId") int communicationId, @RequestParam("responseContent") String responseContent,HttpServletRequest request){
        CommunicationReply reply= new CommunicationReply();
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        Communication comm = communicationRepository.findCommunicationByCommunicationId(communicationId);


        users user = comm.getUserId();

        int moderatorid = usersRepository.findByUsername(username).getId();
        System.out.println(moderatorid);
        reply.setCommunicationId(comm);
        reply.setUserId(user);
        System.out.println(communicationId);
        reply.setContent(responseContent);
        System.out.println(responseContent);
        reply.setModeratorId(moderatorid);
        reply.setStatus("Odpowiedziano");
        System.out.println(reply.getStatus());
        communicationReplyRepository.save(reply);
        return "redirect:/api/communication/reports";
    }
}