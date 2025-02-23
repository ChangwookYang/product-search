package project.web.mapper;

public interface WebRequestMapper<R, C> {
    C toCommand(R request);
}
