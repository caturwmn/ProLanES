package id.ac.ui.cs.advprog.eshop.repository;
import java.util.Iterator;

interface RepositoryCreate<E> {
  public E create(E e);
}

interface RepositoryFindAll<E> {
  public Iterator<E> findAll();
}

interface RepositoryDeleteById {
  public void delete(String id);
}


interface RepositoryDelete<E> {
  public boolean delete(E e);
}

interface RepositoryFind<E> {
  public E findById(String id);
}

interface RepositoryUpdate<E> {
  public E update(String id, E e);
}