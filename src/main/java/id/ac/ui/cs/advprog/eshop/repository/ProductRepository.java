package id.ac.ui.cs.advprog.eshop.repository;

import org.springframework.stereotype.Repository;
import id.ac.ui.cs.advprog.eshop.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

@Repository
public class ProductRepository {
  private List<Product> productData = new ArrayList<>();
  
  public Product create(Product product) {
    productData.add(product);
    return product;
  }

  public Iterator<Product> findAll() {
    return productData.iterator();
  }

  public boolean delete(Product product) {
    return productData.remove(product);
  }
}
