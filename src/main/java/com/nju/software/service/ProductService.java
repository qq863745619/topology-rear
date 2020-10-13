package com.nju.software.service;

import com.nju.software.Bean.Product;
import com.nju.software.Dao.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductDao productDao;

//    @Cacheable(value = "productList",key = "\"product_list_\" + #groupId")
    public List<Product> getAllbyGroupId(String groupId){
        return this.productDao.getProductsByGroupId(groupId);
    }
//    @CachePut(value = "productList",key = "\"product_list_\" + #product.groupId")
    public String saveProduct(Product product){
        return this.productDao.save(product).getId();
    }

    public int deleteProduct(String id){
        return productDao.deleteProductById(id);
    }

}
