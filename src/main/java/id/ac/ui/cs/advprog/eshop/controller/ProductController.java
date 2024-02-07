package id.ac.ui.cs.advprog.eshop.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
  
  @Autowired
  private ProductService service;

  @GetMapping("/create")
  public String createProductPage(Model model) {
    Product product = new Product();
    model.addAttribute("product", product);
    return "createProduct";
  }

  @PostMapping("/create")
  public String createProductPost(@ModelAttribute Product product, Model model) {
    service.create(product);
    return "redirect:list";
  }

  @GetMapping("/list")
  public String productListPage(Model model) {
    List<Product> allProducts = service.findAll();
    model.addAttribute("products", allProducts);
    return "productList";
  }

  @GetMapping("/edit")
  public String editProductPage(Model model) {
    Product product = new Product();
    List<Product> allProducts = service.findAll();
    model.addAttribute("product", product);
    model.addAttribute("products", allProducts);
    return "editProduct";
  }

  @PutMapping("/edit")
  public String productEditPost(@ModelAttribute String productName, Product product, Model model) {
    Product targetProduct = service.edit(productName);
    BeanUtils.copyProperties(product ,targetProduct, "productId");
    return "redirect:list";
  }
}
