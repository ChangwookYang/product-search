package project.application.mapper;

import org.mapstruct.Mapper;
import project.application.domain.Product;
import project.application.dto.command.product.ProductPersistCommand;
import project.application.dto.command.product.ProductCommand;
import project.application.dto.result.ProductResult;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class ProductAppMapper
        implements AppCommandMapper<ProductCommand, ProductPersistCommand>, AppResultMapper<Product, ProductResult> {
    public abstract List<ProductResult> toResults(List<Product> domains);
}
