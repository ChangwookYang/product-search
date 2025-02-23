package project.application.port.brand.out;

import project.application.domain.Brand;
import project.application.dto.command.brand.BrandPersistCommand;

import java.util.List;
import java.util.Optional;

public interface BrandPort {
    List<Brand> findAll();

    Brand createOrUpdate(BrandPersistCommand command);

    void deleteById(Long brandId);

    Optional<Brand> findByName(String name);

    Brand findById(Long brandId);
}
