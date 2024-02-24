package id.ac.ui.cs.advprog.eshop.repository;
import id.ac.ui.cs.advprog.eshop.model.Car;

public interface CarRepository{
  public Car findById(String id);
  public Car update(String id, Car e);
  public void delete(String id);
}

