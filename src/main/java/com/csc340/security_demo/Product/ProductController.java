package com.csc340.security_demo.Product;

import com.csc340.security_demo.subscription.Subscription;
import com.csc340.security_demo.subscription.SubscriptionService;
import com.csc340.security_demo.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    SubscriptionService subscriptionService;


    @Autowired
    UserService userService;

    @GetMapping("/product/all")
    public String getAllProducts(Model model) {
        model.addAttribute("productList",
                productService.getAllProducts());
        model.addAttribute("title", "All Products");
        return "product/product-list";
    }

    @GetMapping("/product/search")
    public String getProducts(Model model, @Param("keyword") String keyword) {
        model.addAttribute("productList",
                productService.getAllProducts(keyword));
        model.addAttribute("keyword", keyword);
        model.addAttribute("title", keyword + " Products");
        return "product/product-list";
    }

    @GetMapping("/product/{vendorId}")
    public String getProductsByVendor(@PathVariable long vendorId, Model model) {
        model.addAttribute("productList",
                productService.getProductsByVendor(vendorId));
        model.addAttribute("title", userService.getUser(vendorId).getUserName() + " Products");
        return "product/product-list";
    }

    @GetMapping("/USER/product/subscriptions")
    public String getUserSubscriptions(Model model) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("productList",
                subscriptionService.getSubscriptionsByUser(userService.getUserByUserName(name).getId()));
        model.addAttribute("title", "My Subscriptions");
        return "product/product-subscriptions";
    }

    @GetMapping("/USER/product/add/{productId}")
    public String addUserSubscription(@PathVariable long productId) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        long userId = userService.getUserByUserName(name).getId();
        Subscription subscription = subscriptionService.getSubscription(productId, userId);

        if (subscription != null) {
            subscription.setQuantity(subscription.getQuantity() + 1);
        } else {
            subscription = new Subscription(userId, productId, 1);
        }
        subscriptionService.addSubscription(subscription);

        return "redirect:/USER/product/subscriptions";
    }


    @GetMapping("/product/id={productId}")
    public String getProduct(@PathVariable long productId, Model model) {
        Product product = productService.getProduct(productId);
        model.addAttribute("product", product);
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("editProduct", userService.getUserByUserName(name).getId() == product.getVendorId());
        return "product/product-details";
    }

    @GetMapping("/VENDOR/product/delete/id={productId}")
    public String deleteProductLazy(@PathVariable long productId, Model model) {
        productService.deleteProduct(productId);
        return "redirect:/product/all";
    }
	
	@GetMapping("/VENDOR/product/delete-eager/id={productId}")
    public String deleteProductEager(@PathVariable long productId, Model model) {
		List<Subscription> subscriptions = subscriptionService.getSubscriptionsByProduct(productId);

		for (Subscription subscription : subscriptions) {
            subscriptionService.deleteSubscriptionById(subscription.getProductId());
        }
        productService.deleteProduct(productId);
        return "redirect:/product/all";
    }

    @PostMapping("/VENDOR/product/create")
    public String createProduct(Product product) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        product.setVendorId(userService.getUserByUserName(name).getId());
        productService.saveProduct(product);
        return "redirect:/product/all";
    }

    @PostMapping("/VENDOR/product/update")
    public String updateProduct(Product product) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        product.setVendorId(userService.getUserByUserName(name).getId());
        productService.saveProduct(product);
        return "redirect:/product/all";
    }

    @GetMapping("/VENDOR/product/new-product")
    public String newProductForm(Model model) {
        return "product/product-new-form";
    }

    @GetMapping("/VENDOR/product/update/id={productId}")
    public String updateProductForm(@PathVariable long productId, Model model) {
        model.addAttribute("product",
                productService.getProduct(productId));
        return "product/product-update-form";
    }
}