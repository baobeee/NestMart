package com.controllers;

import com.models.OrderDetailsDAO;
import com.models.Orders;
import com.models.OrdersDTODAO;
import com.models.OrdersDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/employee")
public class OrderEMPController {

    @Autowired
    private OrdersDTODAO ordersDTODAO;
    @Autowired
    private OrderDetailsDAO orderDetailsDAO;

    private static final int PAGE_SIZE = 10;
 @RequestMapping(value = "order", method = RequestMethod.GET)
    public String showOrders(
            ModelMap model,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(value = "keyword", required = false) String keyword) {

        if (keyword == null || keyword.trim().isEmpty()) {
            keyword = "";
        }

        List<OrdersDTO> listOrder;
        String closestMatch = null;

        if (!keyword.isEmpty()) {
            listOrder = ordersDTODAO.searchByKeyword(keyword, page, pageSize);

            if (listOrder.isEmpty()) {
                closestMatch = ordersDTODAO.findClosestMatch(keyword);
            }
        } else {
            listOrder = ordersDTODAO.findPaginated(page, pageSize);
        }

        int totalOrders;
        if (keyword.isEmpty()) {
            totalOrders = ordersDTODAO.getTotalOrders();
        } else {
            totalOrders = ordersDTODAO.countByKeyword(keyword);
        }
        int totalPages = (int) Math.ceil((double) totalOrders / pageSize);

        model.addAttribute("listOrder", listOrder);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("keyword", keyword); // Pass the search keyword to the view
        model.addAttribute("closestMatch", closestMatch); // Pass the closest match suggestion

        return "/employee/order";
    }
  
}