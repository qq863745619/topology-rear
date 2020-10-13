package com.nju.software.Dao;

import com.nju.software.Bean.TopologieData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopologieDataDao extends MongoRepository<TopologieData,String> {

    TopologieData findTopologieDataById(String id);

//    int deleteTopologieByIdAndUserId(String id,String userId);
    int deleteTopologieDataById(String id);


}
