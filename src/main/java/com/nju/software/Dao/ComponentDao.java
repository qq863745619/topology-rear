package com.nju.software.Dao;

import com.nju.software.Bean.Component;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ComponentDao  extends MongoRepository<Component,Integer> {

    public Component findComponentById(String id);

    public Component findComponentByGroup(String group);




}
