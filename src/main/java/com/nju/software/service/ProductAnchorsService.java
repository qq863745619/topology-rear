package com.nju.software.service;

import com.nju.software.Bean.ProductAnchors;
import com.nju.software.Dao.ProductAnchorsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductAnchorsService {

    @Autowired
    ProductAnchorsDao productAnchorsDao;

    public List<ProductAnchors> getAll(){
        return productAnchorsDao.findAll();
    }
    public String addProductAnchors(ProductAnchors productAnchors){
        return this.productAnchorsDao.save(productAnchors).getId();
    }
    public ProductAnchors getProductAnchorsByPid(String id){
        return this.productAnchorsDao.findProductAnchorsById(id);
    }
}
