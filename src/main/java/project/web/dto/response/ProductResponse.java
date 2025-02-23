package project.web.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonInclude(NON_NULL)
public record ProductResponse(
        Long id,
        String brandName,
        String categoryName,
        Long price
) {
}
