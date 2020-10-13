package com.nju.software.Dao;

import com.nju.software.Bean.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User,String> {

    public User findUserById(String id);

    public User findUserByPhone(String phone);


}
