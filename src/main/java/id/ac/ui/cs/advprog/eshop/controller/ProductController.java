package id.ac.ui.cs.advprog.eshop.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.CarServiceImpl;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import id.ac.ui.cs.advprog.eshop.model.Car;
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
  public String editProductPage(Model model, @RequestParam(value = "editButton") String productName) {
    Product product = new Product();
    model.addAttribute("product", product);
    model.addAttribute("productName", productName);
    return "editProduct";
  }

  @SuppressWarnings("null")
  @PostMapping("/edit")
  public String productEditPost(@RequestParam(value = "editButton") String productName, @ModelAttribute Product product, Model model) {
    Product targetProduct = service.find(productName);
    BeanUtils.copyProperties(product ,targetProduct, "productId");
    return "redirect:list";
  }

  @PostMapping("/delete")
  public String productDelete(@RequestParam(value = "deleteButton") String productName, Model model) {
    service.delete(productName);
    return "redirect:list";
  }
}

@Controller
@RequestMapping("/car")
class CarController extends ProductController{
  @Autowired
  private CarServiceImpl carservice;

  @GetMapping("/createCar")
  public String createCarPage(Model model) {
    Car car = new Car();
    model.addAttribute("car", car);
    return "createCar";
  }

  @PostMapping("/createCar")
  public String createCarPost(@ModelAttribute Car car, Model model) {
    carservice.create(car);
    return "redirect:listCar";
  }

  @GetMapping("/listCar")
  public String carListPage(Model model) {
    List<Car> allCars = carservice.findAll();
    model.addAttribute("cars", allCars);
    return "carList";
  }

  @GetMapping("editCar/{carId}")
  public String editCarPost(@PathVariable String carId, Model model) {
    Car car = carservice.findById(carId);
    model.addAttribute("car", car);
    return "editCar";
  }

  @PostMapping("/editCar")
  public String editCarPost(@ModelAttribute Car car, Model model) {
    System.out.println(car.getCarId());
    carservice.update(car.getCarId(), car);
    return "redirect:listCar";
  }

  @PostMapping("/deleteCar")
  public String deleteCar(@RequestParam("carId") String carId) {
    carservice.deleteCarById(carId);
    return "redirect:listCar";
  }
}