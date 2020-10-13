package com.nju.software.service;

import com.nju.software.Bean.Blobimage;
import com.nju.software.Dao.BlobimageDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: lixing
 * @Date: 2020/03/09/下午4:54
 * @Description:
 */
@Service
public class BlobimageService {

    @Autowired
    private BlobimageDao blobimageDao;

    public String save(Blobimage blobimage){
        return (blobimageDao.save(blobimage)).getPath();
    }

    public void delete(String id){
        blobimageDao.deleteBlobimageByPath(id);
    }

    public Blobimage findBlob(String path){
        return blobimageDao.findBlobimageByPath(path);
    }

    public int updateBlob(String path,boolean ispublic){
       return  blobimageDao.updateBlob(path,ispublic);
    }
}
