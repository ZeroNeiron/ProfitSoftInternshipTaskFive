package autoservice.controller;

import autoservice.dto.request.OwnerRequestDto;
import autoservice.dto.response.OwnerResponseDto;
import autoservice.model.Owner;
import autoservice.service.impl.OwnerServiceImpl;
import autoservice.service.mapper.OwnerMapper;
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
@RequestMapping("/owners")
public class OwnerController {
    private final OwnerServiceImpl ownerService;
    private final OwnerMapper ownerMapper;

    public OwnerController(OwnerServiceImpl ownerService,
                           OwnerMapper ownerMapper) {
        this.ownerService = ownerService;
        this.ownerMapper = ownerMapper;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public OwnerResponseDto getOwner(@PathVariable @Valid Long id) {
        return ownerMapper.mapToDto(ownerService.getById(id));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<OwnerResponseDto> getAllOwners() {
        return ownerService.getAll().stream()
                .map(ownerMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public OwnerResponseDto createOwner(@RequestBody @Valid OwnerRequestDto ownerRequestDto) {
        return ownerMapper.mapToDto(
                ownerService.save(
                        ownerMapper.mapToModel(ownerRequestDto)));
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public OwnerResponseDto updateOwner(@RequestBody @Valid OwnerRequestDto ownerRequestDto,
                                        @PathVariable Long id) {
        Owner owner = ownerMapper.mapToModel(ownerRequestDto);
        owner.setId(id);
        return ownerMapper.mapToDto(ownerService.save(owner));
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public void deleteOwner(@PathVariable @Valid Long id) {
        ownerService.deleteById(id);
    }
}
