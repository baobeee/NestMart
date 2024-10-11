package com.controllers;

import com.models.EmployeeResponse;
import com.models.Feedback;
import com.models.FeedbackDAO;
import com.models.Products;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/client")
public class FeedbackClientController {

    @Autowired
    FeedbackDAO feedbackDAO;

    @RequestMapping(value = "feedback", method = RequestMethod.GET)
    public String showFeedbackForm(@RequestParam("productID") String productID, ModelMap model) {
        Products product = feedbackDAO.findProductByID(productID);
        model.addAttribute("product", product);
        return "/client/feedback";
    }

    @RequestMapping(value = "feedbacks", method = RequestMethod.POST)
    public String submitFeedback(@ModelAttribute("feedback") Feedback feedback, RedirectAttributes redirectAttributes, HttpSession session) {
        if (session == null || session.getAttribute("email") == null) {
            return "redirect:/login.htm";
        }
        Integer customerID = (Integer) session.getAttribute("accountId");
        feedback.setCustomerID(customerID);
        feedbackDAO.add(feedback);
        redirectAttributes.addFlashAttribute("message", "Thank you for your feedback!");
        return "redirect:orderHistory.htm";
    }

    @RequestMapping(value = "viewFeedback", method = RequestMethod.GET)
    public String showFeedback(ModelMap model, @RequestParam("feedbackID") int feedbackID) {
        List<Feedback> feedbacks = feedbackDAO.getFeedbackByProductId(feedbackID);
        EmployeeResponse empResponse = feedbackDAO.getResponseByFeedbackId(feedbackID);
        model.addAttribute("feedbacks", feedbacks);
        model.addAttribute("productID", feedbackID);
        model.addAttribute("employeeReponses", empResponse);
        return "/client/feedbackView";
    }

}
