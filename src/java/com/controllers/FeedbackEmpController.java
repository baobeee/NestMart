package com.controllers;

import com.models.EmployeeResponse;
import com.models.EmployeeResponseDAO;
import com.models.Feedback;
import com.models.FeedbackDAO;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/employee")
public class FeedbackEmpController {
    @Autowired
    private FeedbackDAO feedbackDAO;
    @Autowired
    private EmployeeResponseDAO employeeResponseDAO;

    @RequestMapping(value = "viewFeedbackEmp", method = RequestMethod.GET)
    public String showAllFeedback(ModelMap model) {
        List<Feedback> feedbacks = feedbackDAO.findAllFeedback();
        Map<Integer, EmployeeResponse> feedbackResponses = new HashMap<>();

        System.out.println("Number of feedbacks: " + feedbacks.size());
        for (Feedback feedback : feedbacks) {
            System.out.println("Feedback ID: " + feedback.getFeedbackID());
            EmployeeResponse response = feedbackDAO.getResponseByFeedbackId(feedback.getFeedbackID());
            feedbackResponses.put(feedback.getFeedbackID(), response);
        }
        model.addAttribute("feedbacks", feedbacks);
        model.addAttribute("feedbackResponses", feedbackResponses);
        model.addAttribute("newResponse", new EmployeeResponse());
        return "/employee/feedbackEmp";
    }
    
    @RequestMapping(value = "feedbackWithoutResponse", method = RequestMethod.GET)
    public String showFeedbackWithoutResponse(ModelMap model) {
        List<Feedback> feedbacks = feedbackDAO.getFeedbackWithoutResponse();
        model.addAttribute("feedbacks", feedbacks);
        return "/employee/feedbackWithoutResponse";
    }

    @RequestMapping(value = "feedbackWithResponse", method = RequestMethod.GET)
    public String showFeedbackWithResponse(ModelMap model) {
        List<Feedback> feedbacks = feedbackDAO.getFeedbackWithResponse();
        model.addAttribute("feedbacks", feedbacks);
        return "/employee/feedbackWithResponse"; 
    }
    
    @ModelAttribute("responseForm")
    public Feedback responseForm() {
        return new Feedback();
    }

    @RequestMapping(value = "responseFeedback", method = RequestMethod.GET)
    public String showResponseForm(@RequestParam("feedbackID") int feedbackID, ModelMap model) {
        Feedback fb = feedbackDAO.get(feedbackID);
        model.addAttribute("feedbackdetails", fb);
        return "/employee/responseFeedback";
    }
    
    @RequestMapping(value = "saveFeedbackResponse", method = RequestMethod.POST)
    public String saveFeedbackResponse(@ModelAttribute("newResponse") @Valid EmployeeResponse employeeResponse, BindingResult br, ModelMap model) {
        if (br.hasErrors()) {
            List<Feedback> feedbacks = feedbackDAO.findAllFeedback();
            Map<Integer, EmployeeResponse> feedbackResponses = new HashMap<>();
            for (Feedback feedback : feedbacks) {
                EmployeeResponse response = feedbackDAO.getResponseByFeedbackId(feedback.getFeedbackID());
                feedbackResponses.put(feedback.getFeedbackID(), response);
            }
            model.addAttribute("feedbacks", feedbacks);
            model.addAttribute("feedbackResponses", feedbackResponses);
            return "/employee/feedbackEmp";
        }
        if (employeeResponse.getResponseDate()== null) {
            LocalDateTime now = LocalDateTime.now();
            employeeResponse.setResponseDate(now);
        }
        employeeResponseDAO.addEmployeeResponse(employeeResponse);
        System.out.println("employeeID" + employeeResponse.getEmployeeID());
        System.out.println("feedbackID" + employeeResponse.getEmployeeID());
        System.out.println("content" + employeeResponse.getResponseContent());
        System.out.println("date" + employeeResponse.getResponseDate());
        return "redirect:feedbackWithResponse.htm";
    }
}
