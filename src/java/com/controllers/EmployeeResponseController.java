package com.controllers;
import com.models.EmployeeResponse;
import com.models.EmployeeResponseDAO;
import java.time.LocalDateTime;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/employee")
public class EmployeeResponseController {

    @Autowired
    private EmployeeResponseDAO employeeResponseDAO;

//    @GetMapping("/addEmployeeResponse")
//    public String addEmployeeResponse(@RequestParam("employeeID") int employeeID,
//                                      @RequestParam("feedbackID") int feedbackID,
//                                      @RequestParam("responseContent") String responseContent) {
//        employeeResponseDAO.addEmployeeResponse(employeeID, feedbackID, responseContent);
//        return "redirect:/feedbackDetails?feedbackID=" + feedbackID;
//    }

    @GetMapping("/feedbackResponses")
    public String viewResponses(@RequestParam("feedbackID") int feedbackID, Model model) {
        model.addAttribute("responses", employeeResponseDAO.getResponsesByFeedbackID(feedbackID));
        return "/employee/feedbackResponses";
    }
    
    
        
    
}

