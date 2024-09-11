package com.controllers;

import com.models.Brands;
import com.models.BrandsDAO;
import com.models.Categories;
import com.models.CategoriesDAO;
import com.models.Products;
import com.models.ProductsDAO;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Administrator
 */
@Controller
@RequestMapping("/admin")

public class ProductsController {
@Autowired
    private ServletContext servletContext;
    @Autowired
    private ProductsDAO productsDAO;

    @Autowired
    private CategoriesDAO categoriesDAO; // Add a DAO for Categories

    @Autowired
    private BrandsDAO brandsDAO; // Add a DAO for Brands
    @RequestMapping(value = "product", method = RequestMethod.GET)
    public String showProducts(ModelMap model) {
        List<Products> listProducts = productsDAO.findAll();
        Map<Integer, String> categoryNames = new HashMap<>();
        Map<Integer, String> brandNames = new HashMap<>();

        // Fetch category names
        for (Products product : listProducts) {
            int categoryId = product.getCategoryID();
            if (!categoryNames.containsKey(categoryId)) {
                String categoryName = categoriesDAO.getCategoryNameById(categoryId);
                categoryNames.put(categoryId, categoryName);
            }
        }

        // Fetch brand names
        for (Products product : listProducts) {
            int brandId = product.getBrandID();
            if (!brandNames.containsKey(brandId)) {
                String brandName = brandsDAO.getBrandNameById(brandId);
                brandNames.put(brandId, brandName);
            }
        }

        model.addAttribute("listProducts", listProducts);
        model.addAttribute("categoryNames", categoryNames);
        model.addAttribute("brandNames", brandNames);
        return "/admin/product"; 
    }


    // Display the form to create a new product
    @RequestMapping(value = "productCreate", method = RequestMethod.GET)
    public String showCreateProductForm(Model model) {
         // Fetch categories and brands for the dropdowns
                 model.addAttribute("product", new Products()); 

    List<Categories> listCategories = categoriesDAO.findAll();
    List<Brands> listBrands = brandsDAO.findAll();
    
    model.addAttribute("listCategories", listCategories);
    model.addAttribute("listBrands", listBrands);
        
        return "/admin/productCreate"; 
    }
@RequestMapping(value = "/productCreate", method = RequestMethod.POST)
public String createProduct(@ModelAttribute Products product,
                            @RequestParam("imageFile") MultipartFile imageFile,
                            Model model) {
    try {
        productsDAO.save(product, imageFile, servletContext);
        return "redirect:product.htm"; 
    } catch (Exception e) {
        // Thêm thông báo lỗi và dữ liệu sản phẩm đã nhập vào model
        model.addAttribute("errorMessage", e.getMessage());

        // Lấy danh sách các danh mục và thương hiệu để hiển thị lại trên dropdown
        List<Categories> listCategories = categoriesDAO.findAll();
        List<Brands> listBrands = brandsDAO.findAll();
        model.addAttribute("listCategories", listCategories);
        model.addAttribute("listBrands", listBrands);

        // Đảm bảo rằng thông tin sản phẩm đã nhập vẫn được giữ lại
        model.addAttribute("product", product);

        // Quay lại trang form với dữ liệu đã nhập và thông báo lỗi
        return "/admin/productCreate";
    }
}


 @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    // Display the form to edit an existing product
    @RequestMapping(value = "productUpdate", method = RequestMethod.GET)
    public String showEditProductForm(@RequestParam("productID") String id, Model model) {
        System.out.println("GET request received for productUpdate with ID: " + id);

        Products product = productsDAO.findById(id);
        List<Categories> listCategories = categoriesDAO.findAll();
    List<Brands> listBrands = brandsDAO.findAll();
    
    model.addAttribute("listCategories", listCategories);
    model.addAttribute("listBrands", listBrands);
        
        model.addAttribute("product", product);
        return "/admin/productUpdate"; 
    }

  @RequestMapping(value = "/productUpdate", method = RequestMethod.POST)
public String updateProduct(@ModelAttribute Products product,
                             @RequestParam("imageFile") MultipartFile imageFile,
                             HttpServletRequest request,
                             Model model) {
    try {
        productsDAO.update(product, imageFile, request.getServletContext());
    } catch (Exception e) {
        model.addAttribute("errorMessage", "Error updating product: " + e.getMessage());
        return "errorPage"; // Redirect to an error page if needed
    }
    return "redirect:/product.htm"; // Redirect to the products list or a success page
}

    // Handle request to delete a product
    @RequestMapping(value = "/productDelete", method = RequestMethod.GET)
    public String deleteProduct(@RequestParam("productID") String id) {
        productsDAO.deleteById(id);
        return "redirect:/product.htm"; 
    }
}
