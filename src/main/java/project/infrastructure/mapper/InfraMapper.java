package project.infrastructure.mapper;

public interface InfraMapper<D, E> {
    E toEntity(D domain);

    D toDomain(E entity);
}
