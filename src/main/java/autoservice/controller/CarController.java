package autoservice.controller;

import autoservice.dto.request.CarRequestDto;
import autoservice.dto.response.CarResponseDto;
import autoservice.model.Car;
import autoservice.service.impl.CarServiceImpl;
import autoservice.service.mapper.CarMapper;
import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cars")
public class CarController {
    private final CarServiceImpl carService;
    private final CarMapper carMapper;

    public CarController(CarServiceImpl carService,
                         CarMapper carMapper) {
        this.carService = carService;
        this.carMapper = carMapper;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public CarResponseDto getCarById(@PathVariable @Valid Long id) {
        return carMapper.mapToDto(carService.getById(id));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{brand}/{model}")
    public List<CarResponseDto> getCar(@PathVariable @Valid String brand,
                                       @PathVariable @Valid String model) {
        return carService.getByBrandAndModel(brand, model).stream()
                .map(carMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<CarResponseDto> getAllCars() {
        return carService.getAll().stream()
                .map(carMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CarResponseDto createCar(@RequestBody @Valid CarRequestDto carRequestDto) {
        return carMapper.mapToDto(
                carService.save(
                        carMapper.mapToModel(carRequestDto)));
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public CarResponseDto updateCar(@RequestBody @Valid CarRequestDto carRequestDto,
                                    @PathVariable Long id) {
        Car car = carMapper.mapToModel(carRequestDto);
        car.setId(id);
        return carMapper.mapToDto(carService.save(car));
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable @Valid Long id) {
        carService.deleteById(id);
    }
}
