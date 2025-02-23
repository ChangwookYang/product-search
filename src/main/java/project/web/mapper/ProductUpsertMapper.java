package project.web.mapper;

import org.mapstruct.Mapper;
import project.application.dto.command.product.ProductCommand;
import project.web.dto.request.ProductCreateRequest;
import project.web.dto.request.ProductUpdateRequest;

@Mapper(componentModel = "spring")
public abstract class ProductUpsertMapper
        implements WebRequestMapper<ProductCreateRequest, ProductCommand> {
    public abstract ProductCommand toCommand(Long id, ProductUpdateRequest request);
}
