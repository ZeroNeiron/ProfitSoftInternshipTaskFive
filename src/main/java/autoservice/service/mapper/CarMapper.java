package autoservice.service.mapper;

import autoservice.dto.request.CarRequestDto;
import autoservice.dto.response.CarResponseDto;
import autoservice.model.Car;
import autoservice.service.OwnerService;
import org.springframework.stereotype.Component;

@Component
public class CarMapper {
    private final OwnerService ownerService;

    public CarMapper(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    public Car mapToModel(CarRequestDto carRequestDto) {
        Car car = new Car();
        car.setBrand(carRequestDto.getBrand());
        car.setModel(carRequestDto.getModel());
        car.setOwner(ownerService.getById(carRequestDto.getOwnerId()));
        return car;
    }

    public CarResponseDto mapToDto(Car car) {
        CarResponseDto carResponseDto = new CarResponseDto();
        carResponseDto.setId(car.getId());
        carResponseDto.setBrand(car.getBrand());
        carResponseDto.setModel(car.getModel());
        carResponseDto.setOwnerId(car.getOwner().getId());
        return carResponseDto;
    }
}
