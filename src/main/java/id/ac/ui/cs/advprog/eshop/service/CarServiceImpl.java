package id.ac.ui.cs.advprog.eshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import id.ac.ui.cs.advprog.eshop.repository.CarRepository;
import id.ac.ui.cs.advprog.eshop.repository.RepositoryBase;
import id.ac.ui.cs.advprog.eshop.model.Car;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

@Service
public class CarServiceImpl implements CarService{

  @Autowired
  @Qualifier("carRepository")
  private RepositoryBase<Car> baseRepository;

  @Autowired
  private CarRepository carRepository;
  
  @Override
  public Car create(Car car) {
    baseRepository.create(car);
    return car;
  }

  @Override
  public List<Car> findAll() {
    Iterator<Car> carIterator = baseRepository.findAll();
    List<Car> allCar = new ArrayList<>();
    carIterator.forEachRemaining(allCar::add);
    return allCar;
  }

  @Override
  public Car findById(String carId) {
    Car car = carRepository.findById(carId);
    return car;
  }

  @Override
  public void update(String carId, Car car) {
    carRepository.update(carId, car);
  }

  @Override
  public void deleteCarById(String carId) {
    carRepository.delete(carId);
  }
}
