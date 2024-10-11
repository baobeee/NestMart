package com.controllers;

import com.models.Brands;
import com.models.BrandsDAO;
import com.models.Categories;
import com.models.CategoriesDAO;
import com.models.Products;
import com.models.ProductsDAO;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
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
    private CategoriesDAO categoriesDAO;

    @Autowired
    private BrandsDAO brandsDAO;
   @RequestMapping(value = "products", method = RequestMethod.GET)
    public String showProducts(ModelMap model, 
                               @RequestParam(defaultValue = "1") int page, 
                               @RequestParam(defaultValue = "12") int pageSize, 
                               @RequestParam(value = "keyword", required = false) String keyword) {

        List<Products> listProducts;
        int totalProducts;

        if (keyword != null && !keyword.isEmpty()) {
            // Perform search by keyword
            listProducts = productsDAO.searchByKeyword(keyword);
            totalProducts = listProducts.size();
            
            String closestMatch = productsDAO.findClosestMatch(keyword);
            model.addAttribute("closestMatch", closestMatch);

            int fromIndex = (page - 1) * pageSize;
            int toIndex = Math.min(fromIndex + pageSize, totalProducts);
            listProducts = listProducts.subList(fromIndex, toIndex);
            
            
        } else {
            totalProducts = productsDAO.getTotalProducts();
            listProducts = productsDAO.findPaginated(page, pageSize);
            
        }

        Map<Integer, String> categoryNames = new HashMap<>();
        Map<Integer, String> brandNames = new HashMap<>();
        Map<String, Boolean> canDeleteMap = new HashMap<>();

        for (Products product : listProducts) {
            int categoryId = product.getCategoryID();
            if (!categoryNames.containsKey(categoryId)) {
                String categoryName = categoriesDAO.getCategoryNameById(categoryId);
                categoryNames.put(categoryId, categoryName);
            }
            
            int brandId = product.getBrandID();
            if (!brandNames.containsKey(brandId)) {
                String brandName = brandsDAO.getBrandNameById(brandId);
                brandNames.put(brandId, brandName);
            }

            // Check if the product can be deleted
            boolean canDelete = !productsDAO.isProductSold(product.getProductID());
            canDeleteMap.put(product.getProductID(), canDelete);
            
            
        }
        
        int totalPages = (int) Math.ceil((double) totalProducts / pageSize);

        model.addAttribute("listProducts", listProducts);
        model.addAttribute("categoryNames", categoryNames);
        model.addAttribute("brandNames", brandNames);
        model.addAttribute("canDeleteMap", canDeleteMap);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("keyword", keyword);
        
        return "/admin/product";
    }


    @RequestMapping(value = "productCreate", method = RequestMethod.GET)
    public String showCreateProductForm(Model model) {
                 model.addAttribute("product", new Products()); 

    List<Categories> listCategories = categoriesDAO.findAll();
    List<Brands> listBrands = brandsDAO.findAll();
    
    model.addAttribute("listCategories", listCategories);
    model.addAttribute("listBrands", listBrands);
        
        return "/admin/productCreate"; 
    }
 @RequestMapping(value = "/productCreate", method = RequestMethod.POST)
    public String createProduct(@ModelAttribute Products product,
                                @RequestParam("imageFiles") List<MultipartFile> imageFiles,
                                Model model) {
        try {
            // Save the product along with the image files
            productsDAO.save(product, imageFiles, servletContext);
            
            // Redirect to the product list page
            return "redirect:/admin/products.htm";
        } catch (Exception e) {
            // Handle any exceptions that occur during the save operation
            model.addAttribute("errorMessage", "Lỗi khi tạo sản phẩm: " + e.getMessage());

            // Repopulate categories and brands for the form
            List<Categories> listCategories = categoriesDAO.findAll();
            List<Brands> listBrands = brandsDAO.findAll();
            model.addAttribute("listCategories", listCategories);
            model.addAttribute("listBrands", listBrands);

            // Add the product object back to the model to retain form data
            model.addAttribute("product", product);

            // Format the unit price if needed
            NumberFormat formatter = NumberFormat.getNumberInstance(Locale.US);
            formatter.setGroupingUsed(true);
            formatter.setMaximumFractionDigits(2);
            String formattedUnitPrice = formatter.format(product.getUnitPrice());
            model.addAttribute("formattedUnitPrice", formattedUnitPrice);

            // Return the view name for the product creation form
            return "/admin/productCreate";
        }
    }

 @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(BigDecimal.class, new CustomBigDecimalEditor());

        // Các đăng ký khác nếu có
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @RequestMapping(value = "/productUpdate", method = RequestMethod.GET)
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
                            @RequestParam(value = "imageFiles", required = false) List<MultipartFile> imageFiles,
                            @RequestParam(value = "imagesToDelete", required = false) List<String> imagesToDelete,
                            HttpServletRequest request, 
                            Model model) {
    try {
        // Chuyển đổi giá trị unitPrice về định dạng số
        if (product.getUnitPrice() != null) {
            BigDecimal unitPrice = new BigDecimal(product.getUnitPrice().toString().replace(",", ""));
            product.setUnitPrice(unitPrice);
        }

        // Lấy ServletContext để truyền vào cho phương thức update
        ServletContext context = request.getServletContext();
        
        // Gọi phương thức update từ ProductsDAO
        productsDAO.update(product, imageFiles, imagesToDelete, context);

        return "redirect:/admin/products.htm";

    } catch (Exception e) {
        model.addAttribute("errorMessage", "Lỗi khi cập nhật sản phẩm: " + e.getMessage());

        List<Categories> listCategories = categoriesDAO.findAll();
        List<Brands> listBrands = brandsDAO.findAll();
        model.addAttribute("listCategories", listCategories);
        model.addAttribute("listBrands", listBrands);

        Products existingProduct = productsDAO.findById(product.getProductID());

        // Định dạng giá sản phẩm để hiển thị
        NumberFormat formatter = NumberFormat.getNumberInstance(Locale.US);
        formatter.setGroupingUsed(true);
        formatter.setMaximumFractionDigits(2);
        String formattedUnitPrice = formatter.format(existingProduct.getUnitPrice());

        model.addAttribute("product", existingProduct);
        model.addAttribute("formattedUnitPrice", formattedUnitPrice);

        return "/admin/productUpdate";
    }
}

    @RequestMapping(value = "/productDelete", method = RequestMethod.GET)
    public String deleteProduct(@RequestParam("productID") String id) {
        productsDAO.deleteById(id);
        return "redirect:/admin/products.htm"; 
    }
}
