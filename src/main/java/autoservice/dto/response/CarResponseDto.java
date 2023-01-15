package autoservice.dto.response;

import lombok.Data;

@Data
public class CarResponseDto {
    private Long id;
    private String model;
    private String brand;
    private Long ownerId;
}
