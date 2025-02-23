package project.application.mapper;

public interface AppCommandMapper<C, P> {
    P toPersistCommand(C command);
}
