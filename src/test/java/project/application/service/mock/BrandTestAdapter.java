package project.application.service.mock;

import project.application.domain.Brand;
import project.application.dto.command.brand.BrandPersistCommand;
import project.application.exception.ApiException;
import project.application.port.brand.out.BrandPort;

import java.util.List;
import java.util.Optional;

import static project.application.exception.BrandErrorType.BRAND_NOT_FOUND;

public class BrandTestAdapter implements BrandPort {

    private final List<Brand> brandList = TestDataFactory.getBrands();

    @Override
    public List<Brand> findAll() {
        return brandList;
    }

    @Override
    public Brand createOrUpdate(BrandPersistCommand command) {
        Long id = command.id();
        if (id == null) {
            id = brandList.size() + 1L;
        }

        Brand brand = new Brand(id, command.name());
        brandList.add(brand);

        return brand;
    }

    @Override
    public void deleteById(Long brandId) {
        brandList.stream()
                .filter(brand -> brand.id().equals(brandId))
                .findFirst()
                .ifPresentOrElse(brandList::remove, () -> {
                    throw new ApiException(BRAND_NOT_FOUND);
                });
    }

    @Override
    public Optional<Brand> findByName(String name) {
        return brandList.stream().filter(brand -> brand.name().equals(name)).findFirst();
    }

    @Override
    public Brand findById(Long brandId) {
        return brandList.stream().filter(brand -> brand.id().equals(brandId)).findFirst()
                .orElseThrow(() -> new ApiException(BRAND_NOT_FOUND));
    }
}
