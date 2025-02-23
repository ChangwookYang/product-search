package project.application.mapper;

import org.mapstruct.Mapper;
import project.application.domain.Brand;
import project.application.dto.command.brand.BrandPersistCommand;
import project.application.dto.command.brand.BrandUpsertCommand;
import project.application.dto.result.BrandResult;

@Mapper(componentModel = "spring")
public abstract class BrandAppMapper
        implements AppCommandMapper<BrandUpsertCommand, BrandPersistCommand>, AppResultMapper<Brand, BrandResult> {
}
