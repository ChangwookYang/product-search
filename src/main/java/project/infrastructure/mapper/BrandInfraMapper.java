package project.infrastructure.mapper;

import org.mapstruct.Mapper;
import project.application.domain.Brand;
import project.infrastructure.entity.BrandEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class BrandInfraMapper implements InfraMapper<Brand, BrandEntity> {
    public abstract List<Brand> toDomains(List<BrandEntity> entities);
}
