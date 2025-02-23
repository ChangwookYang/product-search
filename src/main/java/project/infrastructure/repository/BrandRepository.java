package project.infrastructure.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import project.infrastructure.entity.BrandEntity;

import java.util.Optional;

public interface BrandRepository extends JpaRepository<BrandEntity, Long> {
    Optional<BrandEntity> findByName(String name);
}
