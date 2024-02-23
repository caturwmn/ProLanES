package id.ac.ui.cs.advprog.eshop.service;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface BaseService<E> {
  public E create(E e);
  public List<E> findAll();
}
