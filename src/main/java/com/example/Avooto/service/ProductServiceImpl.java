package com.example.Avooto.service;

import com.example.Avooto.dto.ProductDto;
import com.example.Avooto.exception.EntityNotFoundException;
import com.example.Avooto.model.Image;
import com.example.Avooto.model.Product;
import com.example.Avooto.model.User;
import com.example.Avooto.repository.ImageRepository;
import com.example.Avooto.repository.ProductRepository;
import com.example.Avooto.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final ImageRepository imageRepository;

    public List<Product> getProductsListByTitleCityCategoryPrice(String title, String city, String category,
                                                                 Integer minPrice, Integer maxPrice) {
        if (title != null && city != null && category != null && minPrice == null && maxPrice == null) {
            return productRepository.findByTitleContainsAndCityAndCategoryOrderByDateOfCreatedDesc(
                    title, city, category);
        } else if (title == null && city != null && category != null && minPrice == null && maxPrice == null) {
            return productRepository.findByCityAndCategory(city, category);
        } else if (title != null && city != null && category != null && minPrice != null && maxPrice != null) {
            return productRepository.findByTitleContainsAndCityAndCategoryAndPriceBetweenOrderByDateOfCreatedDesc(
                    title, city, category,
                    minPrice, maxPrice);
        } else if (title == null && city != null && category != null && minPrice != null && maxPrice != null) {
            return productRepository.findByCityAndCategoryAndPriceBetween(city, category, minPrice, maxPrice);
        } else {
            return productRepository.findAllByOrderByDateOfCreatedDesc();
        }
    }

    public List<Product> getProductsListByCategory(String category) {
        if (category != null) {
            return productRepository.findByCategory(category);
        } else {
            return productRepository.findAll();
        }
    }

    public List<Product> getProductsListByCity(String city) {
        if (city != null) {
            return productRepository.findByCity(city);
        } else {
            return productRepository.findAll();
        }
    }

    @Override
    public void saveProductList(Principal principal, List<MultipartFile> files, Product product) {
        if (files.size() <= 10) {
            product.setUser(getUserByPrincipal(principal));
            List<Image> imageList;
            if (!files.isEmpty()) {
                imageList = toImageEntityToList(files);
                imageList.get(0).setPreviewImage(true);
                product.addImageListToProduct(imageList);
            }
            log.info("Saving new Product. Title: {}; Author email: {}", product.getTitle(), product.getUser().getEmail());
            Product productFromDb = productRepository.save(product);
            productFromDb.setPreviewImageId(productFromDb.getImages().get(0).getId());
            productRepository.save(product);
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public User getUserByPrincipal(Principal principal) {
        if (principal == null) {
            return new User();
        } else {
            return userRepository.findByEmail(principal.getName());
        }
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Товар не найден с id "
                + id));

    }

    @Override
    public void changeProductListInfo(Long id, ProductDto productBeforeUpdate,
                                  List<MultipartFile> multipartFileList) {
        Product productAfterUpdate = productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(
                "Товар не найден с id " + id));
        List<Image> imageList;
        if (!multipartFileList.isEmpty()) {
            imageList = toImageEntityToList(multipartFileList);
            productAfterUpdate.addImageListToProduct(imageList);
        }
        log.info("User edited exist product. Title: {}; Author email: {}", productAfterUpdate.getTitle(),
                productAfterUpdate.getUser().getEmail());
        productAfterUpdate.setTitle(productBeforeUpdate.getTitle());
        productAfterUpdate.setPrice(productBeforeUpdate.getPrice());
        productAfterUpdate.setCity(productBeforeUpdate.getCity());
        productAfterUpdate.setDescription(productBeforeUpdate.getDescription());
        productAfterUpdate.setCategory(productBeforeUpdate.getCategory());
        productAfterUpdate.setPreviewImageId(productAfterUpdate.getImages().get(0).getId());
        if (productAfterUpdate.getImages().size() <= 10) {
            productRepository.save(productAfterUpdate);
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public void changeProductInfo(Long id, ProductDto productBeforeUpdate,
                                  MultipartFile file1, MultipartFile file2,
                                  MultipartFile file3, MultipartFile file4,
                                  MultipartFile file5) throws IOException {
        Product productAfterUpdate = productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(
                "Товар не найден с id " + id));
        Image image1;
        Image image2;
        Image image3;
        Image image4;
        Image image5;
        if (file1.getSize() != 0) {
            image1 = toImageEntity(file1);
            image1.setPreviewImage(true);
            productAfterUpdate.addImageToProduct(image1);
        }
        if (file1.getSize() != 0) {
            image2 = toImageEntity(file2);
            productAfterUpdate.addImageToProduct(image2);
        }
        if (file1.getSize() != 0) {
            image3 = toImageEntity(file3);
            productAfterUpdate.addImageToProduct(image3);
        }
        if (file1.getSize() != 0) {
            image4 = toImageEntity(file4);
            productAfterUpdate.addImageToProduct(image4);
        }
        if (file1.getSize() != 0) {
            image5 = toImageEntity(file5);
            productAfterUpdate.addImageToProduct(image5);
        }
        log.info("User edited exist product. Title: {}; Author email: {}", productAfterUpdate.getTitle(),
                productAfterUpdate.getUser().getEmail());
        productAfterUpdate.setTitle(productBeforeUpdate.getTitle());
        productAfterUpdate.setPrice(productBeforeUpdate.getPrice());
        productAfterUpdate.setCity(productBeforeUpdate.getCity());
        productAfterUpdate.setDescription(productBeforeUpdate.getDescription());
        productAfterUpdate.setCategory(productBeforeUpdate.getCategory());
        productAfterUpdate.setPreviewImageId(productAfterUpdate.getImages().get(0).getId());
        productRepository.save(productAfterUpdate);
    }

    private Image toImageEntity(MultipartFile file) {
        Image image = new Image();
        image.setName(file.getName());
        image.setOriginalFileName(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        try {
            image.setBytes(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    private List<Image> toImageEntityToList(List<MultipartFile> files) {
        return files.stream().map(this::toImageEntity).collect(Collectors.toList());
    }

    @Override
    public void deleteImageFromProductList(Long productId, Long imageId) {
        productRepository.findById(productId);
        imageRepository.deleteById(imageId);
    }

    @Override
    public Optional<Image> showImage(Long id) {
        return imageRepository.findById(id);
    }

    @Override
    public void showImageFromProductList(Long productId) {
        productRepository.findById(productId);
    }
}



