package com.nju.software.service;

import com.nju.software.Bean.TopologieData;
import com.nju.software.Dao.TopologieDataDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class TopologieDataService {

    @Autowired
    TopologieDataDao topologieDataDao;
    @Cacheable(value = "topology_data",key = "\"topology_data_\" + #id")
    public TopologieData findTopologieById(String id){
        return topologieDataDao.findTopologieDataById(id);
    }
    @CachePut(value = "topology_data",key = "\"topology_data_\" + #result.id")
    public TopologieData save(TopologieData topologieData){
        return topologieDataDao.save(topologieData);
    }
    @CacheEvict(value = "topology_data",key = "\"topology_data_\" + #id")
    public int deleteTopologieDataById(String id){
        return topologieDataDao.deleteTopologieDataById(id);
    }
}
