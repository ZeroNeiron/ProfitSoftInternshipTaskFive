package autoservice.service.mapper;

import autoservice.dto.request.OwnerRequestDto;
import autoservice.dto.response.OwnerResponseDto;
import autoservice.model.Owner;
import org.springframework.stereotype.Component;

@Component
public class OwnerMapper {
    public Owner mapToModel(OwnerRequestDto ownerRequestDto) {
        Owner owner = new Owner();
        owner.setName(ownerRequestDto.getName());
        owner.setPhoneNumber(ownerRequestDto.getPhoneNumber());
        return owner;
    }

    public OwnerResponseDto mapToDto(Owner owner) {
        OwnerResponseDto ownerResponseDto = new OwnerResponseDto();
        ownerResponseDto.setId(owner.getId());
        ownerResponseDto.setName(owner.getName());
        ownerResponseDto.setPhoneNumber(owner.getPhoneNumber());
        return ownerResponseDto;
    }
}
