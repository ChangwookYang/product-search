package project.infrastructure.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import project.infrastructure.entity.CategoryEntity;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    Optional<CategoryEntity> findByName(String name);
}
