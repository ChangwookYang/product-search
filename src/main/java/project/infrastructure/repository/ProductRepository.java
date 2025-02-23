package project.infrastructure.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import project.infrastructure.entity.BrandEntity;
import project.infrastructure.entity.ProductEntity;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Long>, ProductRepositoryCustom {
    List<ProductEntity> findByBrand(BrandEntity brand);
}
