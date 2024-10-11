package com.models;

import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import org.springframework.web.multipart.MultipartFile;

/**
 * Data Access Object interface for handling Product operations. Provides
 * methods for CRUD operations, pagination, searching, and other product-related
 * functionalities.
 *
 * @author Administrator
 */
public interface ProductsClientDAO {

    List<ProductsClient> findAll();

    void save(ProductsClient product, List<MultipartFile> imageFiles, ServletContext servletContext);

    ProductsClient findById(String id);

    void update(ProductsClient product, List<MultipartFile> imageFiles, List<String> imagesToDelete, ServletContext servletContext);

    void deleteById(String id);

    int getTotalProducts();

    List<ProductsClient> findPaginated(int page, int pageSize);

    List<ProductsClient> findByCategoryId(int categoryId);

    Map<String, String> getProductNames(List<String> productIds);

    boolean isProductSold(String productId);

    List<ProductsClient> searchByKeyword(String keyword);

    String findClosestMatch(String keyword);

    int levenshteinDistance(String a, String b);

    public List<ProductsClient> searchByProductName(String productName, int page, int pageSize);

    public int countByKeyword(String keyword);
    public List<ProductsClient> findByCategory(int categoryID, int page, int pageSize);
    public int countByCategory(int categoryID);
      public List<ProductsClient> select8random();
}
