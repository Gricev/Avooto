package com.example.Avooto.controllers;

import com.example.Avooto.dto.ProductDto;
import com.example.Avooto.models.Product;
import com.example.Avooto.models.User;
import com.example.Avooto.repositories.ProductJDBC;
import com.example.Avooto.servicies.ProductService;
import com.example.Avooto.servicies.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final UserService userService;
    private final RestTemplate restTemplate;

    @GetMapping
    public String products(@RequestParam(name = "title", required = false) String title,
                           @RequestParam(name = "city", required = false) String city,
                           @RequestParam(name = "category", required = false) String category,
                           Principal principal, User user, Model model) {
        model.addAttribute("products", productService.getProductsListByTitle(productService.getTitleFromSearch(title)));
        model.addAttribute("productsByCategory", productService.getProductsListByCategory(category));
        model.addAttribute("productsByCity", productService.getProductsListByCity(city));
        model.addAttribute("user", productService.getUserByPrincipal(principal));
        model.addAttribute("userAnyOne", user);
        return "products";
    }

    @GetMapping("/product/{id}/{user}")
    public String productInfo(@PathVariable("id") Long id,
                              @PathVariable("user") User user,
                              Model model, Principal principal) throws NullPointerException {
        Product product = productService.getProductById(id);
        model.addAttribute("userByPrincipal", userService.getUserByPrincipal(principal));
        model.addAttribute("product", product);
        model.addAttribute("user", user);
        model.addAttribute("images", product.getImages());
        model.addAttribute("authorProduct", product.getUser());
        model.addAttribute("phone", user.getPhoneNumber());
//        model.addAttribute("views", product.lookViews());
        return "product-info";
    }

    @GetMapping("/product/{id}")
    public String productInfoById(@PathVariable("id") Long id,
                              Model model, Principal principal) throws NullPointerException {
        Product product = productService.getProductById(id);
        model.addAttribute("userByPrincipal", userService.getUserByPrincipal(principal));
        model.addAttribute("product", product);
        model.addAttribute("images", product.getImages());
        model.addAttribute("authorProduct", product.getUser());
        return "product-info";
    }

    @PostMapping("/product/create")
    public String createProduct(@RequestParam("file1") MultipartFile file1,
                                @RequestParam("file2") MultipartFile file2,
                                @RequestParam("file3") MultipartFile file3,
                                @RequestParam("file4") MultipartFile file4,
                                @RequestParam("file5") MultipartFile file5,
                                Product product, Principal principal) throws IOException {
        productService.saveProduct(principal, product, file1, file2, file3, file4, file5);
        return "redirect:/my/products";
    }

    @PostMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/my/products";
    }

    @GetMapping("/my/products")
    public String userProducts(Principal principal, Model model) {
        User user = productService.getUserByPrincipal(principal);
        model.addAttribute("user", user);
        model.addAttribute("products", user.getProducts());
        return "my-products";
    }

    @GetMapping("/my/products/create")
    public String createUserProducts(Principal principal, Model model) {
        User user = productService.getUserByPrincipal(principal);
        model.addAttribute("user", user);
        model.addAttribute("products", user.getProducts());
        return "products-create";
    }

    @GetMapping("/my/products/edit/{id}")
    public String editProduct(@PathVariable("id") Long id,
                              Principal principal, Model model) {
        Product product = productService.getProductById(id);
        User user = userService.getUserByPrincipal(principal);
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        model.addAttribute("images", product.getImages());
        model.addAttribute("title", product.getTitle());
        model.addAttribute("city", product.getCity());
        model.addAttribute("description", product.getDescription());
        model.addAttribute("price", product.getPrice());
        model.addAttribute("products", user.getProducts());
        model.addAttribute("product", product);
        model.addAttribute("productId", product.getId());
        return "product-edit";
    }

    @PostMapping("/product/edit/{id}")
    public String editProductInformation(@PathVariable(value = "id") Long id, @RequestParam("file1") MultipartFile file1,
                                         @RequestParam("file2") MultipartFile file2,
                                         @RequestParam("file3") MultipartFile file3,
                                         @RequestParam("file4") MultipartFile file4,
                                         @RequestParam("file5") MultipartFile file5,
                                         ProductDto product) throws IOException {
        productService.changeProductInfo(id, product, file1, file2, file3, file4, file5);
        return "redirect:/my/products";
    }

    @GetMapping("/product/category/{category}")
    public String getProductsByCategory(@PathVariable(value = "category") String category,
                                        Principal principal, User user, Model model) {
        model.addAttribute("products", productService.getProductsListByCategory(category));
        model.addAttribute("user", productService.getUserByPrincipal(principal));
        model.addAttribute("userAnyOne", user);
        return "productsByCategory";
    }

    @GetMapping("/product/city/{city}")
    public String getProductsByCity(@PathVariable(value = "city") String city,
                                    Principal principal, User user, Model model) {
        model.addAttribute("products", productService.getProductsListByCity(city));
        model.addAttribute("user", productService.getUserByPrincipal(principal));
        model.addAttribute("userAnyOne", user);
        return "productsByCity";
    }


//    @GetMapping
//    public String productsAll(@RequestParam(name = "title", required = false) String title,
//                           @RequestParam(name = "city", required = false) String city,
//                           @RequestParam(name = "category", required = false) String category,
//                           Principal principal, User user, Model model) {
//        model.addAttribute("products", productService.ProductsListByTitle(productService.getTitleFromSearch(title)));
//        model.addAttribute("productsByCategory", productService.getProductsListByCategory(category));
//        model.addAttribute("productsByCity", productService.getProductsListByCity(city));
//        model.addAttribute("user", productService.getUserByPrincipal(principal));
//        model.addAttribute("userAnyOne", user);
//        return "products";
//    @GetMapping("/search")
//    public List<Product> getAllProductsBySearch(@RequestParam(name = "title", required = false) String title,
//                                                @RequestParam(name = "city", required = false) String city,
//                                                @RequestParam(name = "category", required = false) String category) {
//        return (List<Product>) restTemplate.getForObject("http://localhost:8080/product/title/city/category?title={title}&city={city}&category={category}",
//                Product.class, title, city, category);
//    }
}