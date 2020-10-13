package com.nju.software.service;

import com.nju.software.Bean.Component;
import com.nju.software.Dao.ComponentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComponentService {
    @Autowired
    ComponentDao componentDao;

    public List<Component> findAll(){
        return componentDao.findAll();
    }

    public String save(Component component){
        return componentDao.save(component).getId();
    }

    public Component findByGroup(String group){
        return componentDao.findComponentByGroup(group);
    }

    public Component findById(String id){
        return componentDao.findComponentById(id);
    }
}
