package project.infrastructure.mapper;

import org.mapstruct.Mapper;
import project.application.domain.Product;
import project.infrastructure.entity.ProductEntity;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class ProductInfraMapper implements InfraMapper<Product, ProductEntity> {

    public abstract List<Product> toDomains(List<ProductEntity> dtoList);
}
