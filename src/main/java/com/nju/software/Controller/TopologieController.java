package com.nju.software.Controller;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.nju.software.Bean.Topologie;
import com.nju.software.Bean.TopologieData;
import com.nju.software.Model.Config;
import com.nju.software.Token.UserLoginToken;
import com.nju.software.service.TopologieDataService;
import com.nju.software.service.TopologieService;
import com.nju.software.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.*;
import java.util.concurrent.CountDownLatch;

@Controller
@RequestMapping("topology")
public class TopologieController {

    @Autowired
    public TopologieService topologieService;

    @Autowired
    public TopologieDataService topologieDataService;

    @Autowired
    private UUID uuid;

    private String fileurl = Config.devfile;

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/get/{id}")
    public Topologie findTopologieById(@PathVariable(name = "id") String id) throws InterruptedException {
        Topologie topologie = topologieService.findTopologieById(id);
        TopologieData topologieData = topologieDataService.findTopologieById(id);
        //这里要等getData之后再赋值
        CountDownLatch countDownLatch=new CountDownLatch(1);
        Object data = topologieData.getData();
        if(data!=null){
            countDownLatch.countDown();
            topologie.setData(data);
        }
        countDownLatch.await();
        return topologie;
    }


    @CrossOrigin
    @UserLoginToken
    @ResponseBody
    @RequestMapping(value = "/save")
    public Map<String,Object> save(@RequestBody Topologie topologie, HttpServletRequest httpServletRequest){

        String userId = topologie.getUserId();//第一次保存时userId会为空，
        String username;
        if(userId==""){
            String token = httpServletRequest.getHeader("Authorization");// 从 http 请求头中取出 token
            // 获取 token 中的 user id
            try {
                userId = JWT.decode(token).getAudience().get(0);
                username = JWT.decode(token).getSubject();
                topologie.setUserId(userId);
                topologie.setUsername(username);
            } catch (JWTDecodeException j) {
                throw new RuntimeException("401");
            }
        }

        String topologie_id = topologie.getId();

        if(topologie_id==""){
            topologie_id = uuid.getUUID();
            topologie.setId(topologie_id);
            topologie.setCreatedAt(new Date().getTime());
        }else {
            topologie.setUpdatedAt(new Date().getTime());
        }
        TopologieData topologieData = new TopologieData();
        Object data = topologie.getData();
        topologieData.setData(data);
        topologieData.setId(topologie_id);
        topologieDataService.save(topologieData);


        topologie.setData(null);
        topologieService.save(topologie);
        Map<String,Object> sMap = new HashMap<>();
        sMap.put("id",topologie_id);
        return sMap;

    }
    @CrossOrigin
    @ResponseBody
    @PatchMapping(path = "/update")
    public void updateTopologie(@RequestBody JSONObject jsonParam){

        String id = jsonParam.get("id").toString();
        Topologie t = topologieService.findTopologieById(id);
        if(jsonParam.containsKey("shared")){
            boolean shared = (boolean) jsonParam.get("shared");
            t.setShared(shared);
        }
        if(jsonParam.containsKey("name")){
            String name = (String) jsonParam.get("name");
            t.setName(name);
        }
        if(jsonParam.containsKey("desc")){
            String desc  = (String) jsonParam.get("desc");
            t.setDesc(desc);
        }

        topologieService.save(t);

    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(path = "/getShared")
    public Map<String,Object> getShared(@RequestParam("pageIndex")int pageIndex,@RequestParam("pageCount")int pageCount){


        Map<String,Object> sMap = topologieService.findTopologiesByShared(true,pageIndex,pageCount);
        return sMap;
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping(path = "/getByUserId")
    public Map<String,Object> getTopologyByUserId(HttpServletRequest httpServletRequest, @RequestParam("pageIndex")int pageIndex,@RequestParam("pageCount")int pageCount){

        String userId = "";
        String token = httpServletRequest.getHeader("Authorization");// 从 http 请求头中取出 token
        // 获取 token 中的 user id
        try {
            userId = JWT.decode(token).getAudience().get(0);
        } catch (JWTDecodeException j) {
            throw new RuntimeException("401");
        }
        Map<String,Object> sMap = topologieService.findTopologiesByUserId(userId,pageIndex,pageCount);
        return sMap;
    }

    @CrossOrigin
    @ResponseBody
    @DeleteMapping(path = "/deleteTopology/{id}")
    public int deleteTopologyByIdAndUserId(HttpServletRequest httpServletRequest,@PathVariable(name = "id") String id){

        String userId = "";
        String token = httpServletRequest.getHeader("Authorization");// 从 http 请求头中取出 token
        // 获取 token 中的 user id
        try {
            userId = JWT.decode(token).getAudience().get(0);
        } catch (JWTDecodeException j) {
            throw new RuntimeException("401");
        }
        //删除对应的图片
        Topologie t = topologieService.findTopologieById(id);
        String imageName = t.getImage();
        System.out.println(imageName);
        File file = new File(fileurl+imageName);
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                System.out.println("删除单个文件" + fileurl+imageName + "成功！");

            } else {
                System.out.println("删除单个文件" + fileurl+imageName + "失败！");
            }
        }
        int ret = topologieService.deleteTopologieByIdAndUserId(id,userId);
        topologieDataService.deleteTopologieDataById(id);

        return ret;
    }
}
