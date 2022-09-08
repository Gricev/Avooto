package com.example.Avooto.service;

import com.example.Avooto.dto.ProductDto;
import com.example.Avooto.model.Product;
import com.example.Avooto.model.User;
import javassist.NotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

public interface ProductService {

    List<Product> getProductsListByTitle(String title);
    List<Product> getProductsListByCategory(String category) throws NotFoundException;
    List<Product> getProductsListByCity(String city);
    List<Product> getProductsListByPrice(int price);
    void saveProduct(Principal principal, Product product, MultipartFile file1,
                            MultipartFile file2, MultipartFile file3,
                            MultipartFile file4, MultipartFile file5) throws IOException;
    User getUserByPrincipal(Principal principal);
    void deleteProduct(Long id);
    Product getProductById(Long id);
    void changeProductInfo(Long id, ProductDto productBeforeUpdate,
                                  MultipartFile file1, MultipartFile file2,
                                  MultipartFile file3, MultipartFile file4,
                                  MultipartFile file5) throws IOException;
}