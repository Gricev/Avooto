package com.example.Avooto.service;

import com.example.Avooto.dto.ProductDto;
import com.example.Avooto.model.Image;
import com.example.Avooto.model.Product;
import com.example.Avooto.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> getProductsListByTitleCityCategoryPrice(String title, String city, String category, Integer minPrice,
                                                     Integer maxPrice);
    List<Product> getProductsListByCategory(String category);
    List<Product> getProductsListByCity(String city);
    void saveProductList(Principal principal, List<MultipartFile> multipartFileList, Product product);
    User getUserByPrincipal(Principal principal);
    void deleteProduct(Long id);
    Product getProductById(Long id);
    void changeProductListInfo(Long id, ProductDto productBeforeUpdate,
                               List<MultipartFile> multipartFileList) throws IOException;
    void deleteImageFromProductList(Long productId, Long imageId);
    Optional<Image> showImage(Long id);
    void showImageFromProductList(Long productId);
    void  choosePreviewImageFromProductList(Long productId, Long imageId);
}
