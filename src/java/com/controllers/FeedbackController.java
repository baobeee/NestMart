package com.controllers;

import com.models.EmployeeResponse;
import com.models.Feedback;
import com.models.FeedbackDAO;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin")
public class FeedbackController {

    @Autowired
    FeedbackDAO feedbackDAO;

//    @RequestMapping(value = "viewFeedbackAd", method = RequestMethod.GET)
//    public String showAllFeedback(ModelMap model) {
//        List<Feedback> feedbacks = feedbackDAO.findAllFeedback();
//        model.addAttribute("feedbacks", feedbacks);
//        return "/admin/feedback";
//    }
    @RequestMapping(value = "viewFeedbackAd", method = RequestMethod.GET)
    public String showAllFeedbackAdmin(ModelMap model) {
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
        return "/admin/feedback";
    }
}
