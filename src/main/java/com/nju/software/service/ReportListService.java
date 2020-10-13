package com.nju.software.service;

import com.nju.software.Bean.ReportList;
import com.nju.software.Dao.ReportListDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class ReportListService {

    @Autowired
    private ReportListDao reportListDao;

    @Cacheable(value = "report_list",key = "\"report_\" + #id")
    public ReportList findReportListById(String  id){
        return reportListDao.findReportListById(id);
    }
    @CachePut(value = "report_list",key = "\"report_\" + #result.id")
    public ReportList save(ReportList reportList){
        return reportListDao.save(reportList);
    }
}
