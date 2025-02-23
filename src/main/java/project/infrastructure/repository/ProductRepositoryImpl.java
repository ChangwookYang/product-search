package project.infrastructure.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import project.infrastructure.entity.ProductEntity;

import java.util.List;
import java.util.Optional;

import static project.infrastructure.entity.QProductEntity.productEntity;


public class ProductRepositoryImpl implements ProductRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public ProductRepositoryImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public List<ProductEntity> findProducts(Long categoryId, Long brandId) {

        BooleanBuilder builder = new BooleanBuilder();
        if (categoryId != null) {
            builder.and(productEntity.category.id.eq(categoryId));
        }
        if (brandId != null) {
            builder.and(productEntity.brand.id.eq(brandId));
        }

        return queryFactory
                .selectFrom(productEntity)
                .join(productEntity.brand).fetchJoin()
                .join(productEntity.category).fetchJoin()
                .where(builder)
                .fetch();
    }

    @Override
    public Optional<ProductEntity> findLowestPriceProductByCategory(Long categoryId) {
        return Optional.ofNullable(
                queryFactory
                        .selectFrom(productEntity)
                        .join(productEntity.brand).fetchJoin()
                        .join(productEntity.category).fetchJoin()
                        .where(
                                productEntity.category.id.eq(categoryId),
                                productEntity.price.eq(getLowestPriceProductByCategoryId(categoryId))
                        )
                        .fetchFirst()
        );
    }

    @Override
    public List<ProductEntity> findAllCategoryProductPriceByBrand(Long brandId) {
        return queryFactory
                .selectFrom(productEntity)
                .join(productEntity.category)
                .join(productEntity.brand)
                .where(productEntity.brand.id.eq(brandId))
                .fetch();
    }

    private Long getLowestPriceProductByCategoryId(Long categoryId) {
        return queryFactory
                .select(productEntity.price.min())
                .from(productEntity)
                .join(productEntity.category)
                .where(productEntity.category.id.eq(categoryId))
                .fetchOne();
    }


    @Override
    public Optional<ProductEntity> findCheapestBrandInCategory(Long categoryId) {
        return Optional.ofNullable(getBrandOrderByPrice(categoryId, productEntity.price.asc())
        );
    }

    @Override
    public Optional<ProductEntity> findPriciestBrandInCategory(Long categoryId) {
        return Optional.ofNullable(getBrandOrderByPrice(categoryId, productEntity.price.desc())
        );
    }

    private ProductEntity getBrandOrderByPrice(Long categoryId, OrderSpecifier<Long> price) {
        return queryFactory.selectFrom(productEntity)
                .join(productEntity.category)
                .join(productEntity.brand).fetchJoin()
                .where(productEntity.category.id.eq(categoryId))
                .orderBy(price)
                .fetchFirst();
    }
}
