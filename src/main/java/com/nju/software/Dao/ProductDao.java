package com.nju.software.Dao;

import com.nju.software.Bean.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductDao  extends MongoRepository<Product,String> {
    Product getProductsByGroupIdAndId(String groupId,String id);
    List<Product> getProductsByGroupId(String groupId);
    int deleteProductById(String id);

}
