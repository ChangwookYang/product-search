package project.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.application.dto.command.brand.BrandUpsertCommand;
import project.application.dto.result.BrandResult;
import project.application.exception.ApiException;
import project.application.mapper.BrandAppMapper;
import project.application.port.brand.in.BrandUseCase;
import project.application.port.brand.out.BrandPort;

import static project.application.exception.BrandErrorType.BRAND_NAME_DUPLICATED;

@Service
@RequiredArgsConstructor
public class BrandService implements BrandUseCase {

    private final BrandPort brandPort;
    private final BrandAppMapper mapper;

    @Override
    public BrandResult findById(Long brandId) {
        return mapper.toResult(brandPort.findById(brandId));
    }

    @Override
    @Transactional
    public BrandResult createOrUpdate(BrandUpsertCommand command) {
        brandPort.findByName(command.name()).ifPresent(brand -> {
            if (!brand.id().equals(command.id())) {
                throw new ApiException(BRAND_NAME_DUPLICATED);
            }
        });

        if (command.id() != null) {
            brandPort.findById(command.id());
        }

        return mapper.toResult(brandPort.createOrUpdate(mapper.toPersistCommand(command)));
    }

    @Override
    @Transactional
    public void delete(Long brandId) {
        brandPort.deleteById(brandId);
    }
}
