package id.ac.ui.cs.advprog.eshop.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import id.ac.ui.cs.advprog.eshop.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

@Repository
@Qualifier("productRepository")
public class ProductRepositoryImpl implements 
  RepositoryBase<Product>, ProductRepository {
    
  private List<Product> productData = new ArrayList<>();
  
  @Override
  public Product create(Product product) {
    productData.add(product);
    return product;
  }

  @Override
  public Iterator<Product> findAll() {
    return productData.iterator();
  }

  @Override
  public boolean delete(Product product) {
    return productData.remove(product);
  }
}
