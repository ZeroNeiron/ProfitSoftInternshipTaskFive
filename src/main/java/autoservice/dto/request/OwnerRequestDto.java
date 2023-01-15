package autoservice.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OwnerRequestDto {
    @NotNull
    private String name;
    @NotNull
    private String phoneNumber;
}
