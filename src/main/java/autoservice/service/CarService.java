package autoservice.service;

import autoservice.model.Car;
import java.util.List;

public interface CarService {
    Car getById(Long id);

    List<Car> getAll();

    List<Car> getByBrandAndModel(String brand, String model);

    Car save(Car car);

    void deleteById(Long id);
}

