package com.nju.software.Dao;

import com.nju.software.Bean.Star;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: lixing
 * @Date: 2020/03/17/下午2:35
 * @Description:
 */
@Repository
public interface StarDao extends JpaRepository<Star,String> {

    @Query(value = "select topologyId from Star where userId=:userId")
    public Page<String> findStarsByUserId(@Param("userId") String userId, Pageable pageable);

    @Modifying
    @Query(value = "insert into star(topology_id,user_id) value (?1,?2)",nativeQuery = true)
    public void saveStar(String topology_id,String user_id);

    @Modifying
    @Query(value = "delete from star where topology_id =?1 and user_id = ?2)",nativeQuery = true)
    public void deleteStar(String topology_id,String user_id);
}
