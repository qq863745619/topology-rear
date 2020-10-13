package com.nju.software.service;

import com.nju.software.Bean.Star;
import com.nju.software.Dao.StarDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: lixing
 * @Date: 2020/03/17/下午2:46
 * @Description:
 */
@Service
public class StarService {
    @Autowired
    StarDao starDao;

    public Page<String> findStarsByUserId(String userId, Pageable pageable){
        return starDao.findStarsByUserId(userId,pageable);
    }

//    @Transactional
    public void saveStar(Star star){

//        String id = starDao.save(star).getTopologyId();
       starDao.saveStar(star.getTopologyId(),star.getUserId());
    }
    public void deleteStar(Star star){
        starDao.deleteStar(star.getTopologyId(),star.getUserId());
    }
}
