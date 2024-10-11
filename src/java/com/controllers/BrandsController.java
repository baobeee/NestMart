package com.controllers;

import com.models.Brands;
import com.models.BrandsDAO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class BrandsController {

    @Autowired
    BrandsDAO brandsDAO;

    @RequestMapping(value = "brand", method = RequestMethod.GET)
    public String showBrands(ModelMap model,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "12") int pageSize,
            @RequestParam(value = "keyword", required = false) String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            keyword = "";
        }

        List<Brands> listBrands;
        String closestMatch = null;

        if (!keyword.isEmpty()) {
            listBrands = brandsDAO.searchByKeyword(keyword);

            if (listBrands.isEmpty()) {
                closestMatch = brandsDAO.findClosestMatch(keyword);
            }
        } else {
            listBrands = brandsDAO.findPaginated(page, pageSize);
        }

        int totalBrands;
        if (keyword.isEmpty()) {
            totalBrands = brandsDAO.getTotalBrands();
        } else {
            totalBrands = listBrands.size();
        }
        int totalPages = (int) Math.ceil((double) totalBrands / pageSize);

        model.addAttribute("listBrands", listBrands);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("keyword", keyword); // Pass the search keyword to the view
        model.addAttribute("closestMatch", closestMatch); // Pass the closest match suggestion

        return "admin/brand";
    }

    @RequestMapping(value = "brandCreate", method = RequestMethod.GET)
    public String showCreateBrandForm(Model model) {
        model.addAttribute("brand", new Brands());
        return "/admin/brandCreate";
    }

    @PostMapping(value = "brandCreate")
    public String createBrand(@ModelAttribute("brand") Brands brand) {
        brandsDAO.save(brand);
        return "redirect:/admin/brand.htm";
    }

    @RequestMapping(value = "brandUpdate", method = RequestMethod.GET)
    public String showEditBrandForm(@RequestParam("brandID") int id, Model model) {
        System.out.println("GET request received for brandUpdate.htm with ID: " + id);

        Brands brand = brandsDAO.findById(id);
        model.addAttribute("brand", brand);
        return "/admin/brandUpdate";
    }

    @RequestMapping(value = "brandUpdate", method = RequestMethod.POST)
    public String updateBrand(@ModelAttribute("brand") Brands brand) {
        System.out.println("Updating brand: " + brand.getBrandID() + ", " + brand.getBrandName());
        brandsDAO.update(brand);
        return "redirect:/admin/brand.htm";
    }

     @RequestMapping(value = "brandDelete", method = RequestMethod.GET)
    public String deleteBrand(@RequestParam("brandID") int id, RedirectAttributes redirectAttributes) {
        try {
            brandsDAO.deleteById(id);
            redirectAttributes.addFlashAttribute("successMessage", "Brand deleted successfully");
        } catch (IllegalStateException e) {
            // Xử lý trường hợp brand có sản phẩm liên kết
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/admin/brand.htm";
    }

}
