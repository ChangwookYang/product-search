package project.web.mapper;

import org.mapstruct.Mapper;
import project.application.dto.result.BrandResult;
import project.web.dto.response.BrandResponse;

@Mapper(componentModel = "spring")
public abstract class BrandResponseMapper implements WebResponseMapper<BrandResult, BrandResponse>{
}
