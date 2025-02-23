package project.web.mapper;

public interface WebResponseMapper<S, R> {
    R toResponse(S source);
}
