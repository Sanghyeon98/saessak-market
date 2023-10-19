package com.saessak.repository;

import com.saessak.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    @Query( value = "SELECT * FROM PRODUCT  ORDER BY RAND() LIMIT 10 ", nativeQuery = true)
    List<Product> gameRamdomdata();
}
