package id.ac.ui.cs.advprog.eshop.repository;
import java.util.Iterator;

public interface RepositoryBase<E> {
  public E create(E e);
  public Iterator<E> findAll();
}


