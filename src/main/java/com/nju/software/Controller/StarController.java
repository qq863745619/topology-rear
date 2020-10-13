package com.nju.software.Controller;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.nju.software.Bean.Star;
import com.nju.software.Bean.Topologie;
import com.nju.software.service.StarService;
import com.nju.software.service.TopologieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: lixing
 * @Date: 2020/03/17/下午2:49
 * @Description:
 */
@Controller
@RequestMapping("star")
public class StarController {
    @Autowired
    StarService starService;

    @Autowired
    TopologieService topologieService;

    @CrossOrigin
    @ResponseBody
    @RequestMapping(value = "/getStar")
    public List<String> getStarByUserid(@RequestParam("pageIndex")int pageIndex, @RequestParam("pageCount")int pageCount){
        Pageable pageable =  new QPageRequest(pageIndex,pageCount);
        String userId = null;
        Page<String> page = starService.findStarsByUserId(userId,pageable);
        List<String> list  = page.getContent();
        return list;
    }

    @ResponseBody
    @RequestMapping(value = "/saveStar")
    public Map<String,String> saveStar(@RequestBody JSONObject jsonParam, HttpServletRequest httpServletRequest){
        String topologyId = jsonParam.get("id").toString();
        String token = httpServletRequest.getHeader("Authorization");// 从 http 请求头中取出 token
        // 获取 token 中的 user id
        String userId = null;
        try {
            userId = JWT.decode(token).getAudience().get(0);
        } catch (JWTDecodeException j) {
            throw new RuntimeException("401");
        }
        Star star = new Star();
        star.setTopologyId(topologyId);
        star.setUserId(userId);
        Map<String,String> map = new HashMap<>();
        try {
            starService.saveStar(star);
            //用来更新topologie中的star点赞数，代码待优化
            synchronized (topologieService){
                Topologie topologie = topologieService.findTopologieById(topologyId);
                Object star_num = topologie.getStar();
                if(star_num==null){
                    star_num = 1;
                }else{
                    star_num = (int)star_num+1;
                }
                topologie.setStar((int)star_num);
                topologieService.save(topologie);
            }
        }catch (Exception e){
            map.put("error","已经点过赞了");
        }finally {
            return map;
        }

    }

}
