package project.infrastructure.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import project.application.domain.Brand;
import project.application.dto.command.brand.BrandPersistCommand;
import project.application.exception.ApiException;
import project.application.port.brand.out.BrandPort;
import project.infrastructure.entity.BrandEntity;
import project.infrastructure.mapper.BrandInfraMapper;
import project.infrastructure.repository.BrandRepository;

import java.util.List;
import java.util.Optional;

import static project.application.exception.BrandErrorType.BRAND_NOT_FOUND;

@Repository
@RequiredArgsConstructor
public class BrandPersistenceAdapter implements BrandPort {

    private final BrandRepository brandRepository;

    private final BrandInfraMapper mapper;

    @Override
    public Brand findById(Long brandId) {
        return mapper.toDomain(brandRepository.findById(brandId).orElseThrow(() -> new ApiException(BRAND_NOT_FOUND)));
    }

    @Override
    public List<Brand> findAll() {
        return mapper.toDomains(brandRepository.findAll());
    }

    @Override
    public Brand createOrUpdate(BrandPersistCommand command) {
        Brand brand = new Brand(command.id(), command.name());
        return mapper.toDomain(brandRepository.save(mapper.toEntity(brand)));
    }

    @Override
    public void deleteById(Long brandId) {
        BrandEntity brandEntity = brandRepository.findById(brandId)
                .orElseThrow(() -> new ApiException(BRAND_NOT_FOUND));

        brandRepository.delete(brandEntity);
    }

    @Override
    public Optional<Brand> findByName(String name) {
        return brandRepository.findByName(name).map(mapper::toDomain);
    }
}
