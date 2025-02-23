package project.application.port.brand.in;

import project.application.dto.command.brand.BrandUpsertCommand;
import project.application.dto.result.BrandResult;

public interface BrandUseCase {
    BrandResult findById(Long brandId);

    BrandResult createOrUpdate(BrandUpsertCommand command);

    void delete(Long brandId);
}
