package com.nju.software.Controller;

import com.nju.software.Bean.ProductAnchors;
import com.nju.software.Model.Response;
import com.nju.software.Model.retInfo;
import com.nju.software.service.ProductAnchorsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("productAnchors")
public class ProductAnchorsController {

    @Autowired
    private ProductAnchorsService productAnchorsService;

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/save")
    public Response save(@RequestBody ProductAnchors productAnchors){
        String id =  productAnchorsService.addProductAnchors(productAnchors);
        Response response = new Response();
        if(id!=null){
            response.setCode(200);
            response.setData(new retInfo("id",id));
        }
        return response;
    }

    //获取所有分组信息
    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/get")
    public Response getAll(){

        List<ProductAnchors> list = productAnchorsService.getAll();
        Response response = new Response();
        if(list!=null){
            response.setCode(200).setData(new retInfo("list",list));
        }else{
            response.setCode(-1).setData(new retInfo("list",null));
        }
        return response;
    }
}
