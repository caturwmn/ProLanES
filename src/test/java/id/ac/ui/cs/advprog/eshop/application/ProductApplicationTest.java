package id.ac.ui.cs.advprog.eshop.application;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import id.ac.ui.cs.advprog.eshop.controller.ProductController;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ProductApplicationTest {
  
  @Autowired
  private ProductController productController;

  @Test
  void contextLoads() throws Exception {
    assertThat(productController).isNotNull();
  }
}
