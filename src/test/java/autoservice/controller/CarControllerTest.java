package autoservice.controller;

import java.util.List;
import autoservice.dto.request.CarRequestDto;
import autoservice.dto.request.OwnerRequestDto;
import autoservice.dto.response.CarResponseDto;
import autoservice.dto.response.OwnerResponseDto;
import autoservice.model.Car;
import autoservice.model.Owner;
import autoservice.service.CarService;
import autoservice.service.impl.CarServiceImpl;
import autoservice.service.mapper.CarMapper;
import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class CarControllerTest {
    @MockBean
    private CarServiceImpl carService;

    @MockBean
    private CarMapper carMapper;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        RestAssuredMockMvc.mockMvc(mockMvc);
    }

    @Test
    void returnCarById() {
        Car car = new Car();
        car.setId(1L);
        car.setModel("model");
        car.setBrand("brand");
        car.setOwner(null);

        CarResponseDto carResponseDto = new CarResponseDto();
        carResponseDto.setId(1L);
        carResponseDto.setModel("model");
        carResponseDto.setBrand("brand");
        carResponseDto.setOwnerId(null);

        Mockito.when(carService.getById(1L)).thenReturn(car);
        Mockito.when(carMapper.mapToDto(car)).thenReturn(carResponseDto);

        RestAssuredMockMvc.when()
                .get("/cars/1")
                .then()
                .statusCode(200)
                .body("id", Matchers.equalTo(1))
                .body("model", Matchers.equalTo("model"))
                .body("brand", Matchers.equalTo("brand"))
                .body("ownerId", Matchers.equalTo(null));
    }

    @Test
    void returnAllCars() {
        Car car = new Car();
        car.setId(1L);
        car.setModel("model");
        car.setBrand("brand");
        car.setOwner(null);

        CarResponseDto carResponseDto = new CarResponseDto();
        carResponseDto.setId(1L);
        carResponseDto.setModel("model");
        carResponseDto.setBrand("brand");
        carResponseDto.setOwnerId(null);

        Mockito.when(carService.getAll()).thenReturn(List.of(car));
        Mockito.when(carMapper.mapToDto(car)).thenReturn(carResponseDto);

        RestAssuredMockMvc.when()
                .get("/cars")
                .then()
                .statusCode(200)
                .body("[0].id", Matchers.equalTo(1))
                .body("[0].model", Matchers.equalTo("model"))
                .body("[0].brand", Matchers.equalTo("brand"))
                .body("[0].ownerId", Matchers.equalTo(null));
    }

    @Test
    void createCar() {
        Car car = new Car();
        car.setId(1L);
        car.setModel("model");
        car.setBrand("brand");
        car.setOwner(null);

        CarResponseDto carResponseDto = new CarResponseDto();
        carResponseDto.setId(1L);
        carResponseDto.setModel("model");
        carResponseDto.setBrand("brand");
        carResponseDto.setOwnerId(null);

        Car carWithoutId = new Car();
        carWithoutId.setModel("model");
        carWithoutId.setBrand("brand");
        carWithoutId.setOwner(null);

        CarRequestDto carRequestDto = new CarRequestDto();
        carRequestDto.setModel("model");
        carRequestDto.setBrand("brand");
        carRequestDto.setOwnerId(null);

        Mockito.when(carMapper.mapToModel(carRequestDto)).thenReturn(carWithoutId);
        Mockito.when(carService.save(carWithoutId)).thenReturn(car);
        Mockito.when(carMapper.mapToDto(car)).thenReturn(carResponseDto);

        RestAssuredMockMvc.given()
                .contentType(ContentType.JSON)
                .body(carRequestDto)
                .when()
                .post("/cars")
                .then()
                .statusCode(201)
                .body("id", Matchers.equalTo(1))
                .body("model", Matchers.equalTo("model"))
                .body("brand", Matchers.equalTo("brand"))
                .body("ownerId", Matchers.equalTo(null));
    }

    @Test
    void updateCar() {
        Car car = new Car();
        car.setId(1L);
        car.setModel("model");
        car.setBrand("brand");
        car.setOwner(null);

        CarResponseDto carResponseDto = new CarResponseDto();
        carResponseDto.setId(1L);
        carResponseDto.setModel("model");
        carResponseDto.setBrand("brand");
        carResponseDto.setOwnerId(null);

        Car carWithoutId = new Car();
        carWithoutId.setModel("model");
        carWithoutId.setBrand("brand");
        carWithoutId.setOwner(null);

        CarRequestDto carRequestDto = new CarRequestDto();
        carRequestDto.setModel("model");
        carRequestDto.setBrand("brand");
        carRequestDto.setOwnerId(null);

        Mockito.when(carMapper.mapToModel(carRequestDto)).thenReturn(carWithoutId);
        Mockito.when(carService.save(car)).thenReturn(car);
        Mockito.when(carMapper.mapToDto(car)).thenReturn(carResponseDto);

        RestAssuredMockMvc.given()
                .contentType(ContentType.JSON)
                .body(carRequestDto)
                .when()
                .put("/cars/1")
                .then()
                .statusCode(200)
                .body("id", Matchers.equalTo(1))
                .body("model", Matchers.equalTo("model"))
                .body("brand", Matchers.equalTo("brand"))
                .body("ownerId", Matchers.equalTo(null));
    }

    @Test
    void deleteCar() {
        RestAssuredMockMvc.given().delete("/cars/1");
        Mockito.verify(carService).deleteById(1L);
    }
}