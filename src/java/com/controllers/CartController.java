package com.controllers;

import com.models.CartItem;
import com.models.OrderDetails;
import com.models.OrderDetailsDAO;
import com.models.Orders;
import com.models.OrdersDAO;
import java.math.BigDecimal;
import java.util.ArrayList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/client")
@SessionAttributes("cart")
public class CartController {
@Autowired
    private OrdersDAO ordersDAO;
    @Autowired
    private OrderDetailsDAO orderDetailsDAO;
    @SuppressWarnings("unchecked")
    @ModelAttribute("cart")
    public Map<String, CartItem> getCart(HttpSession session) {
        Map<String, CartItem> cart = (Map<String, CartItem>) session.getAttribute("cart");
        if (cart == null) {
            cart = new HashMap<>();
            session.setAttribute("cart", cart);
        }
        return cart;
    }

    @ModelAttribute("cartTotal")
    public int getCartTotal(@ModelAttribute("cart") Map<String, CartItem> cart) {
        return cart.size(); // Số lượng sản phẩm riêng biệt
    }

    @GetMapping("/cart")
    public String viewCart(@ModelAttribute("cart") Map<String, CartItem> cart, Model model) {
        model.addAttribute("cart", cart);
        return "/client/cart";
    }

    @PostMapping("/updateCart")
    public String updateCart(
            @RequestParam("productId") String productId,
            @RequestParam("quantity") int quantity,
            @ModelAttribute("cart") Map<String, CartItem> cart) {

        CartItem item = cart.get(productId);
        if (item != null) {
            if (quantity <= 0) {
                cart.remove(productId);
            } else {
                item.setQuantity(quantity);
            }
        }
        return "redirect:/client/cart.htm";
    }

    @PostMapping("/removeFromCart")
    public String removeFromCart(
            @RequestParam("productId") String productId,
            @ModelAttribute("cart") Map<String, CartItem> cart) {

        cart.remove(productId);
        return "redirect:/client/cart.htm";
    }

    @PostMapping("/addToCart")
    public String addToCart(
            @RequestParam("productId") String productId,
            @RequestParam("productName") String productName,
            @RequestParam("productPrice") BigDecimal productPrice,
            @RequestParam("productImage") String productImage,
            @RequestParam("quantity") int quantity,
            @ModelAttribute("cart") Map<String, CartItem> cart,
            Model model) {

        CartItem item = cart.get(productId);
        if (item == null) {
            item = new CartItem(productId, productName, productPrice, productImage, quantity);
            cart.put(productId, item);
        } else {
            item.setQuantity(item.getQuantity() + quantity);
        }

        model.addAttribute("cart", cart);
        model.addAttribute("successMessage", "Product added to cart successfully!");

        return "redirect:/client/productDetails.htm?productID=" + productId + "&success=true";
    }
   @PostMapping(value = "createOrder")
    public String createOrder(
            @ModelAttribute("order") Orders order,
            @ModelAttribute("cart") Map<String, CartItem> cart,
            @RequestParam("note") String note,
            RedirectAttributes redirectAttributes) {  

        if (cart.isEmpty()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Your cart is empty. Please add items before placing an order.");
            return "redirect:/client/cart.htm";
        }

        BigDecimal totalAmount = BigDecimal.ZERO;
        List<OrderDetails> orderDetails = new ArrayList<>();

        for (CartItem item : cart.values()) {
            BigDecimal itemTotal = item.getProductPrice().multiply(BigDecimal.valueOf(item.getQuantity()));
            totalAmount = totalAmount.add(itemTotal);

            OrderDetails detail = new OrderDetails();
            detail.setProductID(item.getProductId());
            detail.setQuantity(item.getQuantity());
            detail.setUnitPrice(item.getProductPrice());
            detail.setTotalPrice(itemTotal);
            orderDetails.add(detail);
        }

        order.setNotes(note);
        order.setTotalAmount(totalAmount);

        try {
            int orderId = ordersDAO.saveOrder(order.getCustomerID(), order.getShippingAddress(),
                    order.getNotes(), order.getPaymentMethod(),
                    order.getName(), order.getPhone(), totalAmount);

            for (OrderDetails detail : orderDetails) {
                orderDetailsDAO.saveOrderDetail(orderId, detail.getProductID(), detail.getQuantity(),
                        detail.getUnitPrice(), detail.getTotalPrice());
            }

            // Clear the cart after successful order
            cart.clear();

            redirectAttributes.addFlashAttribute("successMessage", "Order placed successfully!");
            return "redirect:/client/purchase.htm";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to place order. Please try again.");
            return "redirect:/client/cart.htm";
        }
    }

}
