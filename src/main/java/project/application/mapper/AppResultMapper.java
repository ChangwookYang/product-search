package project.application.mapper;

public interface AppResultMapper<D, R> {
    R toResult(D domain);
}
