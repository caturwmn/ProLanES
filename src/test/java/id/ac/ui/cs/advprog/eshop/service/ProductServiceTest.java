package id.ac.ui.cs.advprog.eshop.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.ArrayList;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

  @Mock
  private ProductRepository productRepository;

  @InjectMocks
  private ProductServiceImpl productService;

  @BeforeEach
  void setUP() {
  }

  @Test
  void testCreateAndFindAll() {
    Product product = new Product();
    product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
    product.setProductName("Sampo Cap Bambang");
    product.setProductQuantity(100);
    List<Product> products = new ArrayList<Product>();
    products.add(product);
    Mockito.when(productRepository.findAll()).thenReturn(products.iterator());
    
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
    List<Product> products = new ArrayList<Product>();
    products.add(product);
    products.add(product2);
    Mockito.when(productRepository.findAll()).thenReturn(products.iterator());

    Product foundProduct = productService.find("Sampo Cap Bambang");

    Product savedProduct = products.getFirst();
    assertEquals(foundProduct.getProductId(), savedProduct.getProductId());
    assertEquals(foundProduct.getProductName(), savedProduct.getProductName());
    assertEquals(foundProduct.getProductQuantity(), savedProduct.getProductQuantity());

    foundProduct = productService.find("nonexistent");
    assertFalse(foundProduct.equals(savedProduct));
  }
}
