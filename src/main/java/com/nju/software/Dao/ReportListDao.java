package com.nju.software.Dao;

import com.nju.software.Bean.ReportList;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportListDao extends MongoRepository<ReportList,String> {

    public ReportList findReportListById(String  id);

}
