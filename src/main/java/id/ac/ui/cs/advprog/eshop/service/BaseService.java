package id.ac.ui.cs.advprog.eshop.service;
import java.util.List;

public interface BaseService<E> {
  public E create(E e);
  public List<E> findAll();
}
