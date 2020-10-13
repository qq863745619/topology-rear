package com.nju.software.Dao;

import com.nju.software.Bean.Blobimage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: lixing
 * @Date: 2020/03/09/下午4:51
 * @Description:
 */

@Repository
public interface BlobimageDao extends JpaRepository<Blobimage,String> {

    @Modifying
    @Transactional
    public void deleteBlobimageByPath(String id);

    public Blobimage findBlobimageByPath(String path);

    @Modifying
    @Transactional
    @Query("update Blobimage b set b.ispublic = :ispublic where b.path = :path")
    public int updateBlob(@Param(value = "path")String path, @Param(value = "ispublic") boolean ispublic);
}
