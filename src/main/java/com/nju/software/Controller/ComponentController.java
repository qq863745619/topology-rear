package com.nju.software.Controller;

import com.alibaba.fastjson.JSONObject;
import com.nju.software.Bean.Component;
import com.nju.software.Bean.ProductAnchors;
import com.nju.software.Bean.Product;
import com.nju.software.Model.Group;
import com.nju.software.Model.Response;
import com.nju.software.Model.retInfo;
import com.nju.software.Token.UserLoginToken;
import com.nju.software.service.ComponentService;
import com.nju.software.service.ProductAnchorsService;
import com.nju.software.service.ProductService;
import com.nju.software.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Controller
@RequestMapping("tools")
public class ComponentController {

    @Autowired
    private ComponentService componentService;

    @Autowired
    private ProductService productService;

    @Autowired
    UUID uuid;

    //获取所有组件信息，对应前端tools
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/get")
    public List<Component> getAll(){

        List<Component> list = componentService.findAll();
        for(Component component:list){
            component.setChildren(this.productService.getAllbyGroupId(component.getId()));

        }
        return list;
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/getAllGroup")
    public List<Component> getAllGroup(){

        List<Component> list = componentService.findAll();
        return list;
    }

    //获取所有分组信息
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/getGroup")
    public Response getGroup(){
        List<Group> listG = new ArrayList<Group>();
        List<Component> listC = componentService.findAll();
        for(Component c : listC){
            listG.add(new Group(c.getId(),c.getGroup()));
        }
        Response response = new Response();
        if(listG.size()>0){
            response.setCode(200).setData(listG);
        }

        return response;
    }


    //通过groupId获取分组信息
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/get/{id}")
    public Component getById(@PathVariable(value = "id")String id){

        return componentService.findById(id);
    }

    //保存分组信息
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/save")
    public Response saveComponent(@RequestBody JSONObject jsonParam, HttpServletRequest httpServletRequest){
        //不能存入重名的group
        String group = jsonParam.get("group").toString();
        Response r = new Response();
        if(componentService.findByGroup(group)!=null){
           r.setCode(-1);
           r.setData(new retInfo("error","该分组已经存在"));
           return r;

        }
        Component component = new Component();
        String id = uuid.getUUID6();
        component.setId(id);
        component.setGroup(group);
        componentService.save(component);
        System.out.println(component);
        r.setCode(200);
        r.setData(new retInfo("id",id));
        return r;
    }





}
