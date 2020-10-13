package com.nju.software.service;

import com.nju.software.Bean.User;
import com.nju.software.Bean2.CmsUser;
import com.nju.software.Dao.UserDao;
import com.nju.software.Dao2.CmsUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {
    @Autowired
    UserDao userDao;

    @Autowired
    CmsUserDao cmsUserDao;
    @Cacheable(value = "user",key = "\"user_\" + #id")
    public User findUserById(String id){
        return userDao.findUserById(id);
    }

    public String save(User user){
       return (userDao.save(user)).getId();
    }

    public User findUserByPhone(String phone){
        return userDao.findUserByPhone(phone);
    }

    public CmsUser findCmsUserById(Long userId) {
        return  cmsUserDao.findById(userId).get();
    }
}
