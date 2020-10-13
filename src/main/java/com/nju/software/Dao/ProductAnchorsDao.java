package com.nju.software.Dao;

import com.nju.software.Bean.ProductAnchors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductAnchorsDao extends JpaRepository<ProductAnchors,String> {
    ProductAnchors findProductAnchorsById(String id);
}
