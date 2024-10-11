    package com.controllers;

    import com.models.Support;
    import com.models.SupportDAO;
    import java.sql.Date;
    import java.sql.Timestamp;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Controller;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;
    import java.util.stream.Collectors;
    import javax.servlet.http.HttpSession;
import org.springframework.http.MediaType;
    import org.springframework.ui.Model;
    @Controller
    @RequestMapping("/client")
    public class ClientChatController {

        @Autowired
        private SupportDAO supportDAO;

        @GetMapping("/livechat")
    public String showContactPage(Model model, HttpSession session) {
        Integer accountId = (Integer) session.getAttribute("accountId");
        if (accountId == null) {
            return "redirect:/login";
        }
        List<Support> messages = supportDAO.getMessagesByCustomerId(accountId);
        model.addAttribute("messages", messages);
        return "/client/livechat";
    }

    @PostMapping(value = "/sendMessage", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.TEXT_HTML_VALUE})
    @ResponseBody
    public String sendMessage(@RequestParam("message") String message, HttpSession session) {
        Integer accountId = (Integer) session.getAttribute("accountId");
        if (accountId == null) {
            return "<response><status>error</status><message>Session expired</message></response>";
        }
        Support supportMessage = new Support();
        supportMessage.setCustomerID(accountId);
        supportMessage.setMessage(message);
        supportMessage.setStatus("New");
        supportMessage.setSendDate(new Timestamp(System.currentTimeMillis()));
        supportDAO.saveMessage(supportMessage);
        return "<response><status>success</status></response>";
    }

@GetMapping(value = "/pollMessages", produces = {MediaType.APPLICATION_XML_VALUE + ";charset=UTF-8", MediaType.TEXT_HTML_VALUE + ";charset=UTF-8"})
@ResponseBody
public String pollMessages(@RequestParam("lastMessageId") int lastMessageId, HttpSession session) {
    Integer accountId = (Integer) session.getAttribute("accountId");
    if (accountId == null) {
        return "<response><status>error</status><message>Session expired</message></response>";
    }
    List<Support> newMessages = supportDAO.getMessagesByCustomerIdAfterId(accountId, lastMessageId);
    StringBuilder xmlResponse = new StringBuilder("<response>");
    for (Support message : newMessages) {
        xmlResponse.append("<message>")
                   .append("<supportID>").append(message.getSupportID()).append("</supportID>")
                   .append("<customerID>").append(message.getCustomerID()).append("</customerID>")
                   .append("<employeeID>").append(message.getEmployeeID() != null ? message.getEmployeeID() : "null").append("</employeeID>")
                   .append("<message>").append(escapeXml(message.getMessage())).append("</message>")
                   .append("<sendDate>").append(message.getSendDate()).append("</sendDate>")
                   .append("<status>").append(message.getStatus()).append("</status>")
                   .append("</message>");
    }
    xmlResponse.append("</response>");
    return xmlResponse.toString();
}
  private String escapeXml(String input) {
    if (input == null) {
        return "";
    }
    return input.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&apos;");
}
@GetMapping("/unreadMessageCount")
@ResponseBody
public String getUnreadMessageCount(HttpSession session) {
    Integer accountId = (Integer) session.getAttribute("accountId");
    if (accountId == null) {
        return "0";
    }
    
    // Assuming you have a method in your DAO to get unread message count
    int unreadCount = supportDAO.getUnreadMessageCount(accountId);
    return String.valueOf(unreadCount);
}

    }

