package com.nju.software.Dao;

import com.nju.software.Bean.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: lixing
 * @Date: 2020/03/08/下午2:47
 * @Description:
 */

@Repository
public interface ImageDao extends JpaRepository<Image,String> {

    public Image findImageById(String id);

    public int deleteImageById(String id);
}
