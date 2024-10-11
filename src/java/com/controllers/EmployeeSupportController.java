    package com.controllers;

    import com.models.Accounts;
    import com.models.Support;
    import com.models.SupportDAO;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Controller;
    import org.springframework.ui.Model;
    import org.springframework.web.bind.annotation.*;
    import javax.servlet.http.HttpSession;
    import java.sql.Timestamp;
    import java.util.ArrayList;
    import java.util.Collections;
    import java.util.HashMap;
    import java.util.List;
    import java.util.Map;
    import java.util.stream.Collectors;
    import javax.annotation.PostConstruct;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.MediaType;
    import org.springframework.http.ResponseEntity;

    @Controller
    @RequestMapping("/employee")
    public class EmployeeSupportController {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeSupportController.class);

        @Autowired
        private SupportDAO supportDAO;
        @GetMapping("/supportmessage")
        public String showSupportMessagePage(Model model, HttpSession session) {

            Integer employeeId = (Integer) session.getAttribute("accountId");
            if (employeeId == null) {
                return "redirect:/login.htm";
            }

            List<Support> customersWithMessages = supportDAO.getCustomersWithNewMessages();
            model.addAttribute("customers", customersWithMessages);
            model.addAttribute("employeeId", employeeId);

            System.out.println("Found " + customersWithMessages.size() + " customers with messages");

            return "/employee/supportmessage";
        }

@GetMapping(value = "/getCustomerMessages", produces = {MediaType.APPLICATION_XML_VALUE + ";charset=UTF-8", MediaType.TEXT_HTML_VALUE + ";charset=UTF-8"})
@ResponseBody
public ResponseEntity<String> getCustomerMessages(@RequestParam(required = false) Integer customerID, HttpSession session) {
    Integer employeeId = (Integer) session.getAttribute("accountId");

    if (employeeId == null) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                             .body("<response><error>Employee not logged in</error></response>");
    }
    if (customerID == null) {
        return ResponseEntity.badRequest()
                             .body("<response><error>Customer ID is required</error></response>");
    }

    try {
        List<Support> messages = supportDAO.getMessagesByCustomerIdAndEmployeeId(customerID, employeeId);
        
        StringBuilder xmlResponse = new StringBuilder();
        xmlResponse.append("<response>");

        if (messages == null || messages.isEmpty()) {
            xmlResponse.append("<message>No messages found for this customer.</message>");
        } else {
            xmlResponse.append("<messages>");
            for (Support m : messages) {
                xmlResponse.append("<message>")
                           .append("<supportID>").append(m.getSupportID()).append("</supportID>")
                           .append("<customerID>").append(m.getCustomerID()).append("</customerID>")
                           .append("<employeeID>").append(m.getEmployeeID() != null ? m.getEmployeeID() : "null").append("</employeeID>")
                           .append("<messageContent>").append(m.getMessage()).append("</messageContent>")
                           .append("<status>").append(m.getStatus()).append("</status>")
                           .append("<sendDate>").append(m.getSendDate()).append("</sendDate>")
                           .append("<sender>").append(m.getEmployeeID() != null ? "employee" : "customer").append("</sender>") // Thêm thông tin người gửi
                           .append("</message>");
            }
            xmlResponse.append("</messages>");
        }
        xmlResponse.append("</response>");

     return ResponseEntity.ok()
                     .contentType(MediaType.APPLICATION_XML)
                     .header("Content-Type", "application/xml; charset=UTF-8")
                     .body(xmlResponse.toString());

    } catch (Exception e) {
        logger.error("Error occurred while fetching messages: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                             .body("<response><error>An error occurred while processing your request: " + e.getMessage() + "</error></response>");
    }
}
    @PostMapping(value = "/sendMessage", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.TEXT_HTML_VALUE})
@ResponseBody
public String sendMessage(@RequestParam("message") String message, 
                          @RequestParam("customerID") Integer customerID, 
                          HttpSession session) {
    Integer employeeId = (Integer) session.getAttribute("accountId");
    if (employeeId == null) {
        return "error: Employee not logged in";
    }

    try {
        Support support = new Support();
        support.setCustomerID(customerID);
        support.setEmployeeID(employeeId); // Set the employeeID
        support.setMessage(message);
        support.setStatus("Sent");
        support.setSendDate(new Timestamp(System.currentTimeMillis()));
        supportDAO.saveMessage(support);
        return "success";
    } catch (Exception e) {
        e.printStackTrace();
        return "error: " + e.getMessage();
    }
}

    @PostMapping("/selectMessage")
    @ResponseBody
    public String selectMessage(@RequestParam("messageId") Integer messageId, HttpSession session) {
        Integer employeeId = (Integer) session.getAttribute("accountId");
        if (employeeId == null) {
            return "error";
        }

        Support message = supportDAO.getMessageById(messageId);
        if (message == null || !message.getStatus().equals("New")) {
            return "error";
        }

        // Cập nhật tin nhắn được chọn với employeeID
        message.setEmployeeID(employeeId);
        message.setStatus("Processing");
        supportDAO.updateMessage(message);

        // Cập nhật tất cả các tin nhắn trong cùng cuộc trò chuyện
        List<Support> messages = supportDAO.getMessagesByCustomerIdAndEmployeeId(message.getCustomerID(), employeeId);
        for (Support msg : messages) {
            if (msg.getEmployeeID() == null) { // Chỉ cập nhật những tin nhắn chưa có employeeID
                msg.setEmployeeID(employeeId);
                msg.setStatus("Processing");
                supportDAO.updateMessage(msg);
            }
        }

        session.setAttribute("selectedMessage", messageId);
        return "success";
    }

@PostMapping("/completeConversation")
@ResponseBody
public ResponseEntity<String> completeConversation(@RequestParam("customerID") Integer customerID, HttpSession session) {
    Integer employeeId = (Integer) session.getAttribute("accountId");
    
    if (employeeId == null || customerID == null) {
        return ResponseEntity.badRequest().body("error: Invalid employee or customer ID");
    }

    try {
        // Send a final message
        Support finalMessage = new Support();
        finalMessage.setCustomerID(customerID);
        finalMessage.setEmployeeID(employeeId);
        finalMessage.setMessage("Complete conversation by employee");
        finalMessage.setStatus("Closed");
        finalMessage.setSendDate(new Timestamp(System.currentTimeMillis()));
        supportDAO.saveMessage(finalMessage);

        // Wait a short time to ensure the message is saved
        Thread.sleep(500);

        // Delete the conversation
        int deletedCount = supportDAO.deleteMessagesByCustomerIdAndEmployeeId(customerID, employeeId);
        
        if (deletedCount > 0) {
            return ResponseEntity.ok("success");
        } else {
            return ResponseEntity.ok("error: No messages found to delete");
        }
    } catch (Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                             .body("error: " + e.getClass().getName() + " - " + e.getMessage());
    }
}


 @GetMapping(value = "/pollMessages", produces = {MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8"})
@ResponseBody
public ResponseEntity<List<Support>> pollMessages(@RequestParam(value = "customerID", required = false) Integer customerID,
                                                  @RequestParam("lastMessageId") Integer lastMessageId,
                                                  HttpSession session) {
    Integer employeeId = (Integer) session.getAttribute("accountId");
    List<Support> messages;

    if (customerID != null) {
        messages = supportDAO.getMessagesByCustomerIdAndEmployeeId(customerID, employeeId);
    } else {
        messages = supportDAO.getNewMessages();
    }

    messages = messages.stream()
                       .filter(m -> m.getSupportID() > lastMessageId)
                       .collect(Collectors.toList());

    return ResponseEntity.ok()
                         .header("Content-Type", "application/json; charset=UTF-8") // Đảm bảo UTF-8 cho JSON
                         .body(messages);
}

}
