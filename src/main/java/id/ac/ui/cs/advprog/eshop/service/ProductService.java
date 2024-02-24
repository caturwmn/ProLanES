package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;

public interface ProductService {
  public Product find(String productName);
  public Product delete(String productName);
}
