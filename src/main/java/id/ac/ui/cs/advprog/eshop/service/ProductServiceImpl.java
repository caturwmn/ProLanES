package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

@Service
public class ProductServiceImpl implements ProductService {
  
  @Autowired
  private ProductRepository productRepository;

  @Override
  public Product create(Product product) {
    productRepository.create(product);
    return product;
  }

  @Override
  public List<Product> findAll() {
    Iterator<Product> productIterator = productRepository.findAll();
    List<Product> allProduct = new ArrayList<>();
    productIterator.forEachRemaining(allProduct::add);
    return allProduct;
  }

  @Override
  //Function to find spesific product using Id
  public Product find(String productName) {
    Product targetProduct = null;
    Iterator<Product> productIterator = productRepository.findAll();
    
    while (productIterator.hasNext()) {
      targetProduct = productIterator.next();
      if (targetProduct.getProductName().equals(productName)) {
        return targetProduct;
      }
    }

    //When reaching this point, 
    //it means that the target Product isn't found
    assert false : "Product not found";
    return targetProduct;
  }
  
  @Override
  public Product delete(String productName) {
    Product targetProduct = find(productName);
    productRepository.delete(targetProduct);
    return targetProduct;
  }
}