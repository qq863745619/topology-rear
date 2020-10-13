package com.nju.software.Dao;

import com.nju.software.Bean.Topologie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TopologieDao  extends MongoRepository<Topologie,String> {

    public Topologie findTopologieById(String  id);

//    public Page<Topologie> findTopologiesByShared(boolean shared, Pageable pageable);

    public int deleteTopologieByIdAndUserId(String id,String userId);
}
