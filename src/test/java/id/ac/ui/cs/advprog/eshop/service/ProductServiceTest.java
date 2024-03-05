package id.ac.ui.cs.advprog.eshop.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepositoryImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

  @Spy
  private ProductRepositoryImpl productRepository = new ProductRepositoryImpl(); 

  @InjectMocks
  private ProductServiceImpl productService = new ProductServiceImpl();

  @BeforeEach
  void setUP() {
  }

  @Test
  void testCreateAndFindAll() {
    Product product = new Product();
    product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
    product.setProductName("Sampo Cap Bambang");
    product.setProductQuantity(100);
    productService.create(product);
    List<Product> productList = productService.findAll();

    assertFalse(productList.isEmpty());
    Product savedProduct = productList.getFirst();
    assertEquals(product.getProductId(), savedProduct.getProductId());
    assertEquals(product.getProductName(), savedProduct.getProductName());
    assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
  }

  @Test
  void testFind() {
    Product product = new Product();
    product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
    product.setProductName("Sampo Cap Bambang");
    product.setProductQuantity(100);
    Product product2 = new Product();
    product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
    product2.setProductName("Sampo Cap Usep");
    product2.setProductQuantity(50);
    productService.create(product);
    productService.create(product2);

    Product foundProduct = productService.find("Sampo Cap Bambang");

    List<Product> products = productService.findAll();
    Product savedProduct = products.getFirst();
    assertEquals(foundProduct.getProductId(), savedProduct.getProductId());
    assertEquals(foundProduct.getProductName(), savedProduct.getProductName());
    assertEquals(foundProduct.getProductQuantity(), savedProduct.getProductQuantity());

    foundProduct = productService.find("nonexistent");
    assertFalse(foundProduct.equals(savedProduct));
  }
}
