package autoservice.service;

import autoservice.model.Owner;
import java.util.List;

public interface OwnerService {
    Owner getById(Long id);

    List<Owner> getAll();

    Owner save(Owner owner);

    void deleteById(Long id);
}
