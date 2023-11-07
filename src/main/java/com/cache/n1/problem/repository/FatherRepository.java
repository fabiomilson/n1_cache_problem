package com.cache.n1.problem.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cache.n1.problem.entity.Father;

import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;

@Repository
public interface FatherRepository extends JpaRepository<Father, Long>, JpaSpecificationExecutor<Father> {

    @Query(value = "SELECT f FROM Father f LEFT JOIN FETCH f.childrens",
        countQuery = "SELECT count(f) FROM Father f LEFT JOIN f.childrens" )
    List<Father> fetchAll(Pageable pageable);

    default List<Father> fetchWithSpecification(final int size) {
        return findAll((root, query, criteriaBuilder) -> {
            final List<Predicate> predicates = new ArrayList<>();
            root.join("childrens", JoinType.LEFT);
            //root.join("childrens", JoinType.INNER); // poderia ser aplicado LEFT também
            query.distinct(true);
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        }, Pageable.ofSize(size)).getContent();
    }

}
