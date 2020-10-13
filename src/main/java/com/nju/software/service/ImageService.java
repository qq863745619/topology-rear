package com.nju.software.service;

import com.nju.software.Bean.Image;
import com.nju.software.Dao.ImageDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: lixing
 * @Date: 2020/03/08/下午2:49
 * @Description:
 */
@Service
public class ImageService {

    @Autowired
    ImageDao imageDao;

    public Image findImageById(String id){
        return imageDao.findImageById(id);
    }

    public int deleteImageById(String id){
       return imageDao.deleteImageById(id);
    }

    public String save(Image image){
        return (imageDao.save(image)).getId();
    }



}
