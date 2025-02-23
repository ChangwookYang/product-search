package project.web.mapper;

import org.mapstruct.Mapper;
import project.application.dto.command.brand.BrandUpsertCommand;
import project.web.dto.request.BrandRequest;

@Mapper(componentModel = "spring")
public abstract class BrandUpsertMapper implements WebRequestMapper<BrandRequest, BrandUpsertCommand> {

    public abstract BrandUpsertCommand toCommand(Long id, BrandRequest request);

}
