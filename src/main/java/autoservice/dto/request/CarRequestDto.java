package autoservice.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CarRequestDto {
    @NotNull
    private String model;
    @NotNull
    private String brand;
    private Long ownerId;
}
