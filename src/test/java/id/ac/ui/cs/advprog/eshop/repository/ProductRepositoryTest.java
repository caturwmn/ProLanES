package id.ac.ui.cs.advprog.eshop.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import id.ac.ui.cs.advprog.eshop.model.Product;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Iterator;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {
  
  //6 usages
  @InjectMocks
  ProductRepositoryImpl productRepository;
  
  @BeforeEach
  void setUP() {
  }

  @Test
  void testCreateAndFind() {
    Product product = new Product();
    product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
    product.setProductName("Sampo Cap Bambang");
    product.setProductQuantity(100);
    productRepository.create(product);

    Iterator<Product> productIterator = productRepository.findAll();
    assertTrue(productIterator.hasNext());
    Product savedProduct = productIterator.next();
    assertEquals(product.getProductId(), savedProduct.getProductId());
    assertEquals(product.getProductName(), savedProduct.getProductName());
    assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
  }

  @Test
  void testFindAllIfEmpty() {
    Iterator<Product> producIterator = productRepository.findAll();
    assertFalse(producIterator.hasNext());
  }

  @Test
  void testFindAllIfMoreThanOneProduct() {
    Product product1 = new Product();
    product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
    product1.setProductName("Sampo Cap Bambang");
    product1.setProductQuantity(100);
    productRepository.create(product1);

    Product product2 = new Product();
    product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
    product2.setProductName("Sampo Cap Usep");
    product2.setProductQuantity(50);
    productRepository.create(product2);

    Iterator<Product> productIterator = productRepository.findAll();
    assertTrue(productIterator.hasNext());
    Product savedProduct = productIterator.next();
    assertEquals(product1.getProductId(), savedProduct.getProductId());
    savedProduct = productIterator.next();
    assertEquals(product2.getProductId(), savedProduct.getProductId());
    assertFalse(productIterator.hasNext());
  }

  @Test
  void testDeleteIfMoreThanOne() {
    Product product = new Product();
    product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
    product.setProductName("Sampo Cap Bambang");
    product.setProductQuantity(100);

    Product product2 = new Product();
    product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
    product2.setProductName("Sampo Cap Usep");
    product2.setProductQuantity(50);

    productRepository.create(product);
    productRepository.create(product2);
    productRepository.delete(product);

    Iterator<Product> productIterator = productRepository.findAll();
    assertTrue(productIterator.hasNext());
    Product savedProduct = productIterator.next();
    assertEquals(product2.getProductId(), savedProduct.getProductId());
    assertEquals(product2.getProductName(), savedProduct.getProductName());
    assertEquals(product2.getProductQuantity(), savedProduct.getProductQuantity());
  }
}
