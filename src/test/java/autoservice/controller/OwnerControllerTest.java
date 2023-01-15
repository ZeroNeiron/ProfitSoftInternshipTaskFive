package autoservice.controller;

import java.util.List;
import autoservice.dto.request.OwnerRequestDto;
import autoservice.dto.response.OwnerResponseDto;
import autoservice.model.Owner;
import autoservice.service.impl.OwnerServiceImpl;
import autoservice.service.mapper.OwnerMapper;
import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
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
class OwnerControllerTest {
    @MockBean
    private OwnerServiceImpl ownerService;

    @MockBean
    private OwnerMapper ownerMapper;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        RestAssuredMockMvc.mockMvc(mockMvc);
    }

    @Test
    void returnOwnerById() {
        Owner owner = new Owner();
        owner.setId(1L);
        owner.setName("Anton");
        owner.setPhoneNumber("123122");

        OwnerResponseDto ownerResponseDto = new OwnerResponseDto();
        ownerResponseDto.setId(1L);
        ownerResponseDto.setName("Anton");
        ownerResponseDto.setPhoneNumber("123122");

        Mockito.when(ownerService.getById(1L)).thenReturn(owner);
        Mockito.when(ownerMapper.mapToDto(owner)).thenReturn(ownerResponseDto);

        RestAssuredMockMvc.when()
                .get("/owners/1")
                .then()
                .statusCode(200)
                .body("id", Matchers.equalTo(1))
                .body("name", Matchers.equalTo("Anton"))
                .body("phoneNumber", Matchers.equalTo("123122"));
    }

    @Test
    void returnAllOwners() {
        Owner owner = new Owner();
        owner.setId(1L);
        owner.setName("Anton");
        owner.setPhoneNumber("123122");

        OwnerResponseDto ownerResponseDto = new OwnerResponseDto();
        ownerResponseDto.setId(1L);
        ownerResponseDto.setName("Anton");
        ownerResponseDto.setPhoneNumber("123122");

        Mockito.when(ownerService.getAll()).thenReturn(List.of(owner));
        Mockito.when(ownerMapper.mapToDto(owner)).thenReturn(ownerResponseDto);

        RestAssuredMockMvc.when()
                .get("/owners")
                .then()
                .statusCode(200)
                .body("[0].id", Matchers.equalTo(1))
                .body("[0].name", Matchers.equalTo("Anton"))
                .body("[0].phoneNumber", Matchers.equalTo("123122"));
    }

    @Test
    void createOwner() {
        Owner owner = new Owner();
        owner.setId(1L);
        owner.setName("Anton");
        owner.setPhoneNumber("123122");

        Owner ownerWithoutId = new Owner();
        ownerWithoutId.setName("Anton");
        ownerWithoutId.setPhoneNumber("123122");

        OwnerRequestDto ownerRequestDto = new OwnerRequestDto();
        ownerRequestDto.setName("Anton");
        ownerRequestDto.setPhoneNumber("123122");

        OwnerResponseDto ownerResponseDto = new OwnerResponseDto();
        ownerResponseDto.setId(1L);
        ownerResponseDto.setName("Anton");
        ownerResponseDto.setPhoneNumber("123122");

        Mockito.when(ownerMapper.mapToModel(ownerRequestDto)).thenReturn(ownerWithoutId);
        Mockito.when(ownerService.save(ownerWithoutId)).thenReturn(owner);
        Mockito.when(ownerMapper.mapToDto(owner)).thenReturn(ownerResponseDto);

        RestAssuredMockMvc.given()
                .contentType(ContentType.JSON)
                .body(ownerRequestDto)
                .when()
                .post("/owners")
                .then()
                .statusCode(201)
                .body("id", Matchers.equalTo(1))
                .body("name", Matchers.equalTo("Anton"))
                .body("phoneNumber", Matchers.equalTo("123122"));
    }

    @Test
    void updateOwner() {
        Owner owner = new Owner();
        owner.setId(2L);
        owner.setName("Anton");
        owner.setPhoneNumber("123122");

        Owner ownerWithoutId = new Owner();
        ownerWithoutId.setName("Anton");
        ownerWithoutId.setPhoneNumber("123122");

        OwnerRequestDto ownerRequestDto = new OwnerRequestDto();
        ownerRequestDto.setName("Anton");
        ownerRequestDto.setPhoneNumber("123122");

        OwnerResponseDto ownerResponseDto = new OwnerResponseDto();
        ownerResponseDto.setId(2L);
        ownerResponseDto.setName("Anton");
        ownerResponseDto.setPhoneNumber("123122");

        Mockito.when(ownerMapper.mapToModel(ownerRequestDto)).thenReturn(ownerWithoutId);
        Mockito.when(ownerService.save(owner)).thenReturn(owner);
        Mockito.when(ownerMapper.mapToDto(owner)).thenReturn(ownerResponseDto);

        RestAssuredMockMvc.given()
                .contentType(ContentType.JSON)
                .body(ownerRequestDto)
                .when()
                .put("/owners/2")
                .then()
                .statusCode(200)
                .body("id", Matchers.equalTo(2))
                .body("name", Matchers.equalTo("Anton"))
                .body("phoneNumber", Matchers.equalTo("123122"));
    }

    @Test
    void deleteOwner() {
        RestAssuredMockMvc.given().delete("/owners/1");
        Mockito.verify(ownerService).deleteById(1L);
    }
}