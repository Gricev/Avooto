package com.example.Avooto.servicies;

import com.example.Avooto.dto.ProductDto;
import com.example.Avooto.models.Image;
import com.example.Avooto.models.Product;
import com.example.Avooto.models.User;
import com.example.Avooto.repositories.ProductRepository;
import com.example.Avooto.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public  List<Product> listProducts(String title) {
        if (title != null) {
            return productRepository.findByTitle(title);
        } else {
            return productRepository.findAll();
        }
    }


    public void saveProduct(Principal principal, Product product, MultipartFile file1,
                            MultipartFile file2, MultipartFile file3,
                            MultipartFile file4, MultipartFile file5) throws IOException {
        product.setUser(getUserByPrincipal(principal));
        Image image1;
        Image image2;
        Image image3;
        Image image4;
        Image image5;
        if(file1.getSize() !=0) {
            image1 = toImageEntity(file1);
            image1.setPreviewImage(true);
            product.addImageToProduct(image1);
        }
        if(file1.getSize() !=0) {
            image2 = toImageEntity(file2);
            product.addImageToProduct(image2);
        }
        if(file1.getSize() !=0) {
            image3 = toImageEntity(file3);
            product.addImageToProduct(image3);
        }
        if(file1.getSize() !=0) {
            image4 = toImageEntity(file4);
            product.addImageToProduct(image4);
        }
        if(file1.getSize() !=0) {
            image5 = toImageEntity(file5);
            product.addImageToProduct(image5);
        }

        log.info("Saving new Product. Title: {}; Author email: {}", product.getTitle(), product.getUser().getEmail());
        Product productFromDb = productRepository.save(product);
        productFromDb.setPreviewImageId(productFromDb.getImages().get(0).getId());
        productRepository.save(product);
    }

//    public void saveProduct(Principal principal, Product product, List<MultipartFile> files) throws IOException {
//        product.setUser(getUserByPrincipal(principal));
//        addImage(files, product);
//        product.getImages().get(0).setPreviewImage(true);
//
//        log.info("Saving new Product. Title: {}; Author email: {}", product.getTitle(), product.getUser().getEmail());
//        Product productFromDb = productRepository.save(product);
//        productFromDb.setPreviewImageId(productFromDb.getImages().get(0).getId());
//        productRepository.save(product);
//    }

//    private void addImage(List<MultipartFile> files, Product product) {
//        for (MultipartFile file: files) {
//            if(file.getSize() !=0) {
//                Image image = null;
//                try {
//                    image = toImageEntity(file);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                product.addImageToProduct(image);
//            }
//        }
//    }

    public User getUserByPrincipal(Principal principal) {
        if (principal == null) {
            return new User();
        } else {
            return userRepository.findByEmail(principal.getName());
        }
    }

    private Image toImageEntity(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setName(file.getName());
        image.setOriginalFileName(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        image.setBytes(file.getBytes());
        return image;
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public Product getProductById(Long id) {
      return  productRepository.findById(id).orElse(null);
    }

    public void changeProductInfo(Long id, ProductDto productBeforeUpdate,
                                  MultipartFile file1, MultipartFile file2,
                                  MultipartFile file3, MultipartFile file4,
                                  MultipartFile file5) throws IOException {
        Product productAfterUpdate = productRepository.findById(id).orElseThrow();
        Image image1;
        Image image2;
        Image image3;
        Image image4;
        Image image5;
        if(file1.getSize() !=0) {
            image1 = toImageEntity(file1);
            image1.setPreviewImage(true);
            productAfterUpdate.addImageToProduct(image1);
        }
        if(file1.getSize() !=0) {
            image2 = toImageEntity(file2);
            productAfterUpdate.addImageToProduct(image2);
        }
        if(file1.getSize() !=0) {
            image3 = toImageEntity(file3);
            productAfterUpdate.addImageToProduct(image3);
        }
        if(file1.getSize() !=0) {
            image4 = toImageEntity(file4);
            productAfterUpdate.addImageToProduct(image4);
        }
        if(file1.getSize() !=0) {
            image5 = toImageEntity(file5);
            productAfterUpdate.addImageToProduct(image5);
        }
        log.info("User edited exist product. Title: {}; Author email: {}", productAfterUpdate.getTitle(), productAfterUpdate.getUser().getEmail());
        productAfterUpdate.setTitle(productBeforeUpdate.getTitle());
        productAfterUpdate.setPrice(productBeforeUpdate.getPrice());
        productAfterUpdate.setCity(productBeforeUpdate.getCity());
        productAfterUpdate.setDescription(productBeforeUpdate.getDescription());
//        Product productFromDb = productRepository.save(productAfterUpdate);
//        productFromDb.setPreviewImageId(productFromDb.getImages().get(0).getId());
//        productRepository.save(productAfterUpdate);
        productAfterUpdate.setPreviewImageId(productAfterUpdate.getImages().get(0).getId());
        productRepository.save(productAfterUpdate);
    }
}
